package AST;
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_VAR_DEC extends AST_Node {

	public AST_TYPE type;
	public String id;
	public AST_EXP exp;
	public AST_NEW_EXP newExp;

	public AST_VAR_DEC(int lineNumber, AST_TYPE type, String id, AST_EXP exp, AST_NEW_EXP newExp) {
		super(lineNumber);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
		
		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		if (exp == null) {
		    if (newExp == null) {
		        System.out.format("====================== varDec -> type ID( %s ) SEMICOLON\n", id);
		    } else {
		        System.out.format("====================== varDec -> type ID( %s ) ASSIGN new_exp SEMICOLON\n", id);
		    }
		} else {
			System.out.format("====================== varDec -> type ID( %s ) ASSIGN exp SEMICOLON\n", id);
		}

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.type = type;
		this.id = id;
		this.exp = exp;
		this.newExp = newExp;
	}

	public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{
		TYPE t;
	
		/****************************/
		/* [1] Check If Type exists */
		/****************************/
		t = type.SemantMe();
		if (t == null)
		{
			System.out.format(">> ERROR [%d] non existing type %s - class AST_VAR_DEC\n",lineNumber, type.id);
			throw new SEMANTIC_EXCEPTION(lineNumber);
		}
		
		/**************************************/
		/* [2] Check That Name does NOT exist */
		/**************************************/
		if (SYMBOL_TABLE.getInstance().findInScope(id) != null)
		{
			System.out.format(">> ERROR [%d] variable %s already exists in scope - class AST_VAR_DEC\n",lineNumber,id);
			throw new SEMANTIC_EXCEPTION(lineNumber);
		}

		/***************************************************/
		/* [3] Enter the Function Type to the Symbol Table */
		/***************************************************/
		SYMBOL_TABLE.getInstance().enter(id,t);

		/*********************************************************/
		/* [4] Return value is irrelevant for class declarations */
		/*********************************************************/
		return t;
	}
}
