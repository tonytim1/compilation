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
		        System.out.format("====================== varDec -> type:%s ID( %s ) SEMICOLON\n", type.id, id);
		    } else {
		        System.out.format("====================== varDec -> type:%s ID( %s ) ASSIGN new_exp SEMICOLON\n", type.id, id);
		    }
		} else {
			System.out.format("====================== varDec -> type:%s ID( %s ) ASSIGN exp SEMICOLON\n", type.id, id);
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
		TYPE t, t2 = null, t3 = null;
		TYPE expType;
	
		/****************************/
		/* [1] Check If Type exists */
		/****************************/
		t = type.SemantMe();
		if (t == null)
		{
			System.out.format(">> ERROR [%d] non existing type %s - class AST_VAR_DEC\n",lineNumber, type.id);
			throw new SEMANTIC_EXCEPTION(lineNumber);
		}
		if (t.typeName == "void")
		{
			System.out.format(">> ERROR [%d] the type void can be used only as a return type in a declaration of a func: %s - class AST_VAR_DEC\n",lineNumber, type.id);
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

		/**************************************/
		/* [3] Check That it can be assign to exp */
		/**************************************/
		if (exp != null) {
			t3 = exp.SemantMe();
			System.out.format(">> INFO [%d] trying to assign exp %s to var %s - class AST_VAR_DEC\n", lineNumber, t3.typeName, t.typeName);
			if (!t.canAssign(t3)) {
				System.out.format(">> ERROR [%d] can't assign exp %s to var %s - class AST_VAR_DEC\n", lineNumber, t3.typeName, t.typeName);
				throw new SEMANTIC_EXCEPTION(lineNumber);
			}
		}

		/**************************************/
		/* [3] Check That it can be assign to newExp */
		/**************************************/
		if (newExp != null) {
			t2 = newExp.SemantMe();
			System.out.format(">> INFO [%d] trying to assign new exp %s to var %s - class AST_VAR_DEC\n",lineNumber,t2.typeName, t.typeName);
			if (!t.canAssign(t2)) {
				System.out.format(">> ERROR [%d] can't assign new exp %s to var %s - class AST_VAR_DEC\n",lineNumber,t2.typeName, t.typeName);
				throw new SEMANTIC_EXCEPTION(lineNumber);
			}
		}
		
		/***************************************************/
		/* [4] If in class scope, declared members can only be initialized with a constant value  */
		/***************************************************/
		if (SYMBOL_TABLE.getInstance().curr_class != null) {
			if (newExp != null || (exp != null && t3.name != "int" && t3.name != "nil" && t3.name != "string")) {
				System.out.format(">> INFO [%d] In class members can only be assigned with consts for member %s - class AST_VAR_DEC\n",lineNumber,id);
				throw new SEMANTIC_EXCEPTION(lineNumber);
			}
		}



		/***************************************************/
		/* [5] Enter the Var Type to the Symbol Table */
		/***************************************************/
		SYMBOL_TABLE.getInstance().enter(id,t);

		/*********************************************************/
		/* [6] Return value is irrelevant for class declarations */
		/*********************************************************/
		System.out.format("INFO [%d] ast var dec got var:%s with type name:%s typeName:%s - class AST_VAR_DEC\n", lineNumber, id, t.name, t.typeName);
		return t;
	}
}
