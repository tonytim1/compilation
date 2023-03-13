/***********/
/* PACKAGE */
/***********/
package SYMBOL_TABLE;

/*******************/
/* GENERAL IMPORTS */
/*******************/
import java.io.PrintWriter;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;

/****************/
/* SYMBOL TABLE */
/****************/
public class SYMBOL_TABLE
{
	private int hashArraySize = 13;
	public String required_return_type = ""; // New field
	public TYPE_CLASS curr_class = null;
	
	/**********************************************/
	/* The actual symbol table data structure ... */
	/**********************************************/
	private SYMBOL_TABLE_ENTRY[] table = new SYMBOL_TABLE_ENTRY[hashArraySize];
	private SYMBOL_TABLE_ENTRY top;
	private int top_index = 0;
	private int curr_depth = 0;

	//New for IR part
	public int func_local_index; // The local index of a var inside a function
	private int param_local_index; // The local index of a param inside a function
	
	/**************************************************************/
	/* A very primitive hash function for exposition purposes ... */
	/**************************************************************/
    private int hash(String s)
	{
	    if (s == null) {return 0;}
		return Math.abs(s.hashCode()) % hashArraySize;
	}

	public void enter(String name,TYPE t) {
	    if (this.curr_depth == 0) {
	        this.enter(name, t, "global");
	    }
	    else {
	        this.enter(name, t, "local");
	    }
	}

	/****************************************************************************/
	/* Enter a variable, function, class type or array type to the symbol table */
	/****************************************************************************/
	public void enter(String name,TYPE t, String var_scope)
	{
		/*************************************************/
		/* [1] Compute the hash value for this new entry */
		/*************************************************/
		int hashValue = hash(name);

		/******************************************************************************/
		/* [2] Extract what will eventually be the next entry in the hashed position  */
		/*     NOTE: this entry can very well be null, but the behaviour is identical */
		/******************************************************************************/
		SYMBOL_TABLE_ENTRY next = table[hashValue];
	
		/**************************************************************************/
		/* [3] Prepare a new symbol table entry with name, type, next and prevtop */
		/**************************************************************************/

		//NEW IR PART
		int local_index = 0;
		if (var_scope.equals("param"))
		{
			local_index = param_local_index++;
		}
		else if (var_scope.equals("global"))
		{
			local_index = 0; // the index doesn't matter if this is global var
		}
		else if (var_scope.equals("local"))
		{
			if (required_return_type.equals("") == false)
			{
				var_scope = "local_func";
				if (!(t instanceof TYPE_FUNCTION) && !(name.equals("SCOPE-BOUNDARY")))
				{
					local_index = func_local_index++;
				}
			} 
			else 
			{
				var_scope = "local_class";
			}
		}

		System.out.format("variable %s is in scope %s, top index %d, local index %d\n", name, var_scope, top_index, local_index);
		SYMBOL_TABLE_ENTRY e = new SYMBOL_TABLE_ENTRY(name,t,hashValue,next,top,top_index++,curr_depth, local_index, var_scope);

		/**********************************************/
		/* [4] Update the top of the symbol table ... */
		/**********************************************/
		top = e;
		
		/****************************************/
		/* [5] Enter the new entry to the table */
		/****************************************/
		table[hashValue] = e;
		
		/**************************/
		/* [6] Print Symbol Table */
		/**************************/
		PrintMe();
	}

    /*****************************************/
	/* return true if the variable is global */
	/*****************************************/
	public String getVarScope(String name){
		for (SYMBOL_TABLE_ENTRY e = table[hash(name)]; e != null; e = e.next)
		{
			if (name.equals(e.name))
			{
				return e.var_scope;
			}
		}
		if (curr_class != null){
			if (curr_class.existInFatherScope(name))
			{
				return "local_class";
			}
		}
		return null;
	}

	/***************************************/
	/* get the local index of the variable */
	/***************************************/
	public int getLocalIndex(String name){
		for (SYMBOL_TABLE_ENTRY e = table[hash(name)]; e != null; e = e.next)
		{
			if (name.equals(e.name))
			{	
				return e.local_index;
			}
		}
		return -1;
	}

	/**************************************/
	/* get the attribute index in a class */
	/**************************************/
	public int getFieldIndex(String name){
		return curr_class.getFieldIndex(name);
	}


	/***********************************************/
	/* Find the inner-most scope element with name */
	/***********************************************/
	public TYPE find(String name)
	{
		SYMBOL_TABLE_ENTRY e;
		TYPE result;

		System.out.format("SYMBOL TABLE FIND looking for %s\n", name);
		for (e = table[hash(name)]; e != null; e = e.next)
		{
			System.out.format("   ENTRY name=%s type={name=%s typeName=%s}\n", e.name, e.type.name, e.type.typeName);
		}

		for (e = table[hash(name)]; e != null; e = e.next)
		{
			//System.out.format("   ENTRY name:%s type: name:%s typeName:%s\n", e.name, e.type.name, e.type.typeName);

			if (name.equals(e.name))
			{
				if ((curr_class != null) && (e.depth == 0)){
					break;
				}
				return e.type;
			}
		}
		
		if (curr_class != null)
		{
			result = curr_class.findinClass(name);
			if (result != null)
			{
				return result;
			}
		}

		if (e != null)
		{
			return e.type;
		}
		
		return null;
	}

	public TYPE findInScope(String name)
	{
		SYMBOL_TABLE_ENTRY e;
				
		for (e = table[hash(name)]; e != null; e = e.next)
		{
			if (name.equals(e.name) && e.depth == curr_depth)
			{
				return e.type;
			}
		}
		
		return null;
	}

	/***************************************************************************/
	/* begine scope = Enter the <SCOPE-BOUNDARY> element to the data structure */
	/***************************************************************************/
	public void beginScope()
	{
		/************************************************************************/
		/* Though <SCOPE-BOUNDARY> entries are present inside the symbol table, */
		/* they are not really types. In order to be ablt to debug print them,  */
		/* a special TYPE_FOR_SCOPE_BOUNDARIES was developed for them. This     */
		/* class only contain their type name which is the bottom sign: _|_     */
		/************************************************************************/
		enter(
			"SCOPE-BOUNDARY",
			new TYPE_FOR_SCOPE_BOUNDARIES("NONE"));

		/*********************************************/
		/* Print the symbol table after every change */
		/*********************************************/
		PrintMe();
		curr_depth++;
	}

	/********************************************************************************/
	/* end scope = Keep popping elements out of the data structure,                 */
	/* from most recent element entered, until a <NEW-SCOPE> element is encountered */
	/********************************************************************************/
	public void endScope()
	{
		/**************************************************************************/
		/* Pop elements from the symbol table stack until a SCOPE-BOUNDARY is hit */		
		/**************************************************************************/
		while (top.name != "SCOPE-BOUNDARY")
		{
			table[top.index] = top.next;
			top_index = top_index-1;
			top = top.prevtop;
		}
		/**************************************/
		/* Pop the SCOPE-BOUNDARY sign itself */		
		/**************************************/
		table[top.index] = top.next;
		top_index = top_index-1;
		top = top.prevtop;

		/*********************************************/
		/* Print the symbol table after every change */		
		/*********************************************/
		PrintMe();
		curr_depth--;
	}

	public boolean isGlobalScope()
	{
		return curr_depth == 0;
	}

	public boolean isClassDecleration()
	{
		return curr_class != null && curr_depth == 1;
	}
	
	public static int n=0;
	
	public void PrintMe()
	{
		int i=0;
		int j=0;
		String dirname="./output/";
		String filename=String.format("SYMBOL_TABLE_%d_IN_GRAPHVIZ_DOT_FORMAT.txt",n++);

		try
		{
			/*******************************************/
			/* [1] Open Graphviz text file for writing */
			/*******************************************/
			PrintWriter fileWriter = new PrintWriter(dirname+filename);

			/*********************************/
			/* [2] Write Graphviz dot prolog */
			/*********************************/
			fileWriter.print("digraph structs {\n");
			fileWriter.print("rankdir = LR\n");
			fileWriter.print("node [shape=record];\n");

			/*******************************/
			/* [3] Write Hash Table Itself */
			/*******************************/
			fileWriter.print("hashTable [label=\"");
			for (i=0;i<hashArraySize-1;i++) { fileWriter.format("<f%d>\n%d\n|",i,i); }
			fileWriter.format("<f%d>\n%d\n\"];\n",hashArraySize-1,hashArraySize-1);
		
			/****************************************************************************/
			/* [4] Loop over hash table array and print all linked lists per array cell */
			/****************************************************************************/
			for (i=0;i<hashArraySize;i++)
			{
				if (table[i] != null)
				{
					/*****************************************************/
					/* [4a] Print hash table array[i] -> entry(i,0) edge */
					/*****************************************************/
					fileWriter.format("hashTable:f%d -> node_%d_0:f0;\n",i,i);
				}
				j=0;
				for (SYMBOL_TABLE_ENTRY it=table[i];it!=null;it=it.next)
				{
					/*******************************/
					/* [4b] Print entry(i,it) node */
					/*******************************/
					fileWriter.format("node_%d_%d ",i,j);
					fileWriter.format("[label=\"<f0>%s|<f1>%s|<f2>prevtop=%d|<f3>next\"];\n",
						it.name,
						it.type.name,
						it.prevtop_index);

					if (it.next != null)
					{
						/***************************************************/
						/* [4c] Print entry(i,it) -> entry(i,it.next) edge */
						/***************************************************/
						fileWriter.format(
							"node_%d_%d -> node_%d_%d [style=invis,weight=10];\n",
							i,j,i,j+1);
						fileWriter.format(
							"node_%d_%d:f3 -> node_%d_%d:f0;\n",
							i,j,i,j+1);
					}
					j++;
				}
			}
			fileWriter.print("}\n");
			fileWriter.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}		
	}
	
	/**************************************/
	/* USUAL SINGLETON IMPLEMENTATION ... */
	/**************************************/
	private static SYMBOL_TABLE instance = null;

	/*****************************/
	/* PREVENT INSTANTIATION ... */
	/*****************************/
	protected SYMBOL_TABLE() {}

	/******************************/
	/* GET SINGLETON INSTANCE ... */
	/******************************/
	public static SYMBOL_TABLE getInstance()
	{
		if (instance == null)
		{
			/*******************************/
			/* [0] The instance itself ... */
			/*******************************/
			instance = new SYMBOL_TABLE();

			/*****************************************/
			/* [1] Enter primitive types int, string */
			/*****************************************/
			instance.enter("int",   TYPE_INT.getInstance());
			instance.enter("string",TYPE_STRING.getInstance());

			/*************************************/
			/* [2] How should we handle void ??? */
			/*************************************/

			/***************************************/
			/* [3] Enter library function PrintInt */
			/***************************************/
			instance.enter(
				"PrintInt",
				new TYPE_FUNCTION(
					TYPE_VOID.getInstance(),
					"PrintInt",
					new TYPE_LIST(
						TYPE_INT.getInstance(),
						null)));

			/***************************************/
			/* [3] Enter library function PrintString */
			/***************************************/
			instance.enter(
					"PrintString",
					new TYPE_FUNCTION(
							TYPE_VOID.getInstance(),
							"PrintString",
							new TYPE_LIST(
								TYPE_STRING.getInstance(),
								null)));

			/***************************************/
			/* [3] Enter library function PrintTrace */
			/***************************************/
			instance.enter(
					"PrintTrace",
					new TYPE_FUNCTION(
							TYPE_VOID.getInstance(),
							"PrintTrace",
							null));
			
		}
		return instance;
	}
}
