package AST;
import TYPES.*;
import SYMBOL_TABLE.*;
import IR.*;
import TEMP.*;

public class AST_ARRAY_TYPE_DEF extends AST_Node {
	public String id;
	public AST_TYPE type;
	
	
	public AST_ARRAY_TYPE_DEF(int lineNumber, String id, AST_TYPE type) {
		super(lineNumber);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
		
		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.format("====================== arrayTypedef -> ARRAY ID( %s ) EQ type(%s) LBRACK RBRACK SEMICOLON\n", id, type.id);

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.id = id;
		this.type = type;
	}

	public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{
		SYMBOL_TABLE s = SYMBOL_TABLE.getInstance();
		TYPE t;
		
		// check if if in global scope
		if (!(s.isGlobalScope()))
		{
			System.out.format(">> ERROR [%d] Array defined not in global scope - class AST_ARRAY_TYPE_DEF\n",lineNumber);
			throw new SEMANTIC_EXCEPTION(lineNumber);
		}
		// check if array name already declared
		if (s.find(id) != null)
		{
			System.out.format(">> ERROR [%d] Array defined with name %s that already exists - class AST_ARRAY_TYPE_DEF\n",lineNumber,id);
			throw new SEMANTIC_EXCEPTION(lineNumber);
		}

		t = type.SemantMe();

		// Check that Type exists
		if (t == null)
		{
			System.out.format(">> ERROR [%d] non existing type - class AST_ARRAY_TYPE_DEF\n",lineNumber);
			throw new SEMANTIC_EXCEPTION(lineNumber);
		}
		// check that type is not void
		if (t.typeName == "void") {
		    System.out.format(">> ERROR [%d] type %s is void - class AST_ARRAY_TYPE_DEF\n",lineNumber,t.typeName);
			throw new SEMANTIC_EXCEPTION(lineNumber);
		}

		/***************************************************/
		/* [3] Enter the Function Type to the Symbol Table */
		/***************************************************/
		SYMBOL_TABLE.getInstance().enter(id, new TYPE_ARRAY(t));

		/*********************************************************/
		/* [4] Return value is irrelevant for class declarations */
		/*********************************************************/
		return new TYPE_ARRAY(t);
	}

	public TEMP IRme() {
	    return null;
	}

}
