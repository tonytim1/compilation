package AST;

import TYPES.*;
import TEMP.*;

public class AST_EXP_BINOP extends AST_EXP {
	public AST_BINOP binop;

	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_EXP_BINOP(AST_BINOP binop) {
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.print("====================== exp -> exp BINOP exp\n");

		/*******************************/
		/* COPY INPUT DATA NENBERS ... */
		/*******************************/
		this.binop = binop;
	}

	/*************************************************/
	/* The printing message for a binop exp AST node */
	/*************************************************/
	

	public TYPE SemantMe() {
		System.out.println("EXP BINOP - semant me");
		return binop.SemantMe();
	}

	public TEMP IRme() {
		System.out.println("EXP BINOP - IRme");

		return binop.IRme();
	}
}