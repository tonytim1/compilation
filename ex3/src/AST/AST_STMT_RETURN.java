package AST;
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_STMT_RETURN extends AST_STMT {
	public AST_EXP exp;
	
	/*******************/
	/*  CONSTRUCTOR(S) */
	/*******************/
	public AST_STMT_RETURN(AST_EXP exp)
	{
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		if(exp != null) System.out.print("====================== stmt -> RETURN exp SEMICOLON\n");
		if(exp == null) System.out.print("====================== stmt -> RETURN SEMICOLON\n");

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.exp = exp;
	}
	public TYPE SemantMe() {
	// Check that exp's type agrees with function return type
	TYPE expType;
	if (exp != null) {
	    expType = exp.SemantMe();
	    if (expType.typeName != SYMBOL_TABLE.required_return_type.typeName) {
	        // Error required return type is wrong
	        System.exit(0);
	    }
	}
	 return null;
	}
}
