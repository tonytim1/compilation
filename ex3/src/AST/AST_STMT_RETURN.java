package AST;
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_STMT_RETURN extends AST_STMT {
	public AST_EXP exp;

	/*******************/
	/*  CONSTRUCTOR(S) */

	/*******************/
	public AST_STMT_RETURN(int lineNumber, AST_EXP exp) {
		super(lineNumber);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		if (exp != null) System.out.print("====================== stmt -> RETURN exp SEMICOLON\n");
		if (exp == null) System.out.print("====================== stmt -> RETURN SEMICOLON\n");

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.exp = exp;
	}

	public TYPE SemantMe() throws SEMANTIC_EXCEPTION {
		// Check that exp's type agrees with function return type
		TYPE expType;
		if (exp != null) {
			if (SYMBOL_TABLE.getInstance().required_return_type.typeName == "void") {
				System.out.format(">> ERROR [%d] return type is not empty and expected return type is void\n", lineNumber);
				throw new SEMANTIC_EXCEPTION(lineNumber);
			}
			expType = exp.SemantMe();
			if (SYMBOL_TABLE.getInstance().required_return_type.canAssign(expType) == false) {
				System.out.format(">> ERROR [%d] returned type %s and expected return type %s don't match\n", lineNumber, expType.typeName, SYMBOL_TABLE.getInstance().required_return_type.typeName);
				throw new SEMANTIC_EXCEPTION(lineNumber);
			}
		} else {
			if (SYMBOL_TABLE.getInstance().required_return_type.typeName != "void") {
				System.out.format(">> ERROR [%d] returned type %s and expected return type %s don't match\n", lineNumber, "void", SYMBOL_TABLE.getInstance().required_return_type.typeName);
				throw new SEMANTIC_EXCEPTION(lineNumber);
			}
		}
		return null;
	}
}


	    /*
	    if (expType.typeName != SYMBOL_TABLE.getInstance().required_return_type.typeName) {
	        // Error required return type is wrong
	        if (expType.typeName == "nil") {
	            if (SYMBOL_TABLE.getInstance().required_return_type.typeName != "array" && SYMBOL_TABLE.getInstance().required_return_type.typeName != "class") {
	                System.out.format(">> ERROR [%d] required return type is : %s but our return type is : %s - class AST_STMT_RETURN\n",lineNumber, SYMBOL_TABLE.getInstance().required_return_type, expType.typeName);
	                throw new SEMANTIC_EXCEPTION(lineNumber);
	            }
	        }
	        else {
	            System.out.format(">> ERROR [%d] required return type is : %s but our return type is : %s - class AST_STMT_RETURN\n",lineNumber, SYMBOL_TABLE.getInstance().required_return_type, expType.typeName);
                throw new SEMANTIC_EXCEPTION(lineNumber);
	        }
	    }
	    else {
	        if(expType.typeName == "array") {
	            TYPE_ARRAY expTypeArray = (TYPE_ARRAY) expType;
	            TYPE_ARRAY requiredReturnTypeArray = (TYPE_ARRAY) SYMBOL_TABLE.getInstance().required_return_type;
	            if (expTypeArray.arrayName != requiredReturnTypeArray.arrayName) {
	                System.out.format(">> ERROR [%d] both array but different arrayName, we returned one is %s but required is %s", expTypeArray.arrayName, requiredReturnTypeArray.arrayName);
	                throw new SEMANTIC_EXCEPTION(lineNumber);
	            }
	        }
	        */

