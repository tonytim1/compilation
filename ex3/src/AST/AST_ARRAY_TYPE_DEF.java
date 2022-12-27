package AST;
import TYPES.*;
import SYMBOL_TABLE.*;

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
		System.out.format("====================== arrayTypedef -> ARRAY ID( %s ) EQ type LBRACK RBRACK SEMICOLON", id);

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.id = id;
		this.type = type;
	}

	public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{
		SYMBOL_TABLE s = SYMBOL_TABLE.getInstance();
		
		//check if depth != 0 or class name already declared in scope
		if (!(s.isGlobalScope()) || s.find(id) != null)
			throw new SEMANTIC_EXCEPTION(lineNumber);

		TYPE t;

		/****************************/
		/* [1] Check If Type exists */
		/****************************/
		t = type.SemantMe();
		if (t == null)
		{
			System.out.format(">> ERROR [%d] non existing type %s\n",lineNumber,type);
			throw new SEMANTIC_EXCEPTION(lineNumber);
		}
		// check that type is not void
		if (t == TYPE_VOID.getInstance())
			throw new SEMANTIC_EXCEPTION(lineNumber);

		/**************************************/
		/* [2] Check That Name does NOT exist */
		/**************************************/
		if (SYMBOL_TABLE.getInstance().find(id) != null)
		{
			System.out.format(">> ERROR [%d] variable %s already exists in scope\n",lineNumber,id);
		}

		/***************************************************/
		/* [3] Enter the Function Type to the Symbol Table */
		/***************************************************/
		SYMBOL_TABLE.getInstance().enter(id, new TYPE_ARRAY(t));

		/*********************************************************/
		/* [4] Return value is irrelevant for class declarations */
		/*********************************************************/
		return null;
	}
}
