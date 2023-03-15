package AST;

import TYPES.*;
import TEMP.*;

public class AST_C_FIELD_FUNC_DEC extends AST_C_FIELD {
	public AST_FUNC_DEC func;

	/*******************/
	/* CONSTRUCTOR(S) */
	/*******************/
	public AST_C_FIELD_FUNC_DEC(AST_FUNC_DEC func) {
		this.func = func;

		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
	}

	/*************************************************/
	/* The printing message for a XXX node */
	/*************************************************/
	

	public TYPE SemantMe() {
		System.out.println("CFEILD FUNCDEC semant me");
		return func.SemantMe();
	}

	public TEMP IRme() {
		System.out.println("CFEILD_FUNCDEC - IRme");
		func.IRme();
		return null;
	}
}
