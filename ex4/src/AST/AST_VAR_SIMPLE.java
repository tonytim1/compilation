package AST;
import TYPES.*;
import SYMBOL_TABLE.*;
import IR.*;
import TEMP.*;
public class AST_VAR_SIMPLE extends AST_VAR
{
	/************************/
	/* simple variable name */
	/************************/
	public String name;
	// For IRME
	Boolean is_string = false;
	TYPE varType;
	public boolean cfgVar = false;

	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_VAR_SIMPLE(int lineNumber, String name)
	{
		super(lineNumber);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.format("====================== var -> ID( %s )\n",name);

		/*******************************/
		/* COPY INPUT DATA NENBERS ... */
		/*******************************/
		this.name = name;
	}
	public void PrintMe()
	{
		System.out.format("AST VAR SIMPLE %s\n", name);
	}

	public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{
		SYMBOL_TABLE s = SYMBOL_TABLE.getInstance();
	    TYPE t = s.find(name);
	    if (t == null) { // In case we use undefined variable
	        System.out.format(">> ERROR [%d] field %s was not found - AST_VAR_SIMPLE\n",lineNumber,name);
		    throw new SEMANTIC_EXCEPTION(lineNumber);
	    }
		
		// For IRME
		this.scope_type = s.getVarScope(name);
		if (this.scope_type.equals("local_class"))
		{
			this.index = s.getFieldIndex(name);
		}
		else
		{
			this.index = s.getLocalIndex(name);
		}
		if (t == TYPE_STRING.getInstance()) 
		{
			this.is_string = true;
		}
	
		this.varType = t;
	    return t;
    }

	public TEMP IRme() {
		System.out.format("VAR SIMPLE - IRme (%s)\n", name);

		TEMP t = TEMP_FACTORY.getInstance().getFreshTEMP();

		if(this.scope_type.equals("global")) {
			IR.getInstance().Add_IRcommand(new IRcommand_Load_Global(t, name));
		}
		else {
			String irName = name;
			boolean c = false;
			IRcommand command;
			if (className != null && offsets.get(name) == null) { // var is class field
				c = true;
				irName = className + "_" + name;
			}
			if (c == true && (!(this.varType instanceof TYPE_CLASS) || !(((TYPE_CLASS) this.varType).name.equals(className)))) // var is
			{																// field and																																																			// itself
				command = new IRcommand_ThisDotField(irName, t);
				((IRcommand_ThisDotField)command).cfg = cfgVar;
			}
			else
				command = new IRcommand_Load_Local(irName, t);

			command.offset = GetOffset(irName);
			IR.getInstance().Add_IRcommand(command);
		}
		return t;
	}
}
