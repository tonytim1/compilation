package AST;

import TEMP.TEMP;
import TYPES.*;

public class AST_EXP_SIMPLE extends AST_EXP {
	public AST_EXP exp;

	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_EXP_SIMPLE(AST_EXP exp) {
		this.exp = exp;
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.print("====================== exp -> (exp)\n");

		/*******************************/
		/* COPY INPUT DATA NENBERS ... */
		/*******************************/
	}

	/***********************************************/
	/* The default message for an exp var AST node */
	/***********************************************/
	

	public TYPE SemantMe() {
		System.out.println("EXP SIMPLE - semant me");
		return exp.SemantMe();
	}

	public TEMP IRme() {
		System.out.println("EXP SIMPLE - IRme");

		return exp.IRme();
	}
}
