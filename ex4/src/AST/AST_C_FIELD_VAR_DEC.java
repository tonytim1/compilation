package AST;

import TYPES.*;
import TEMP.*;

public class AST_C_FIELD_VAR_DEC extends AST_C_FIELD {
	public AST_VAR_DEC vd;

	/*******************/
	/* CONSTRUCTOR(S) */
	/*******************/
	public AST_C_FIELD_VAR_DEC(AST_VAR_DEC vd) {
		this.vd = vd;
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		if (vd != null)
			System.out.print("====================== cfield -> varDec\n");
	}

	/****************** outside CONSTRUCTOR code *******************/

	/*************************************************/
	/* The printing message for a XXX node */
	/*************************************************/
	

	public TYPE SemantMe() {
		System.out.println("CFEILD VARDEC - semant me");
		return vd.SemantMe();

	}
	public TEMP IRme(){
		System.out.println("CFEILD_VARDEC" + "- IRme");
		vd.IRme();
		return null;
	}

}
