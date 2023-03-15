package AST;

import TYPES.*;
import TEMP.*;

public class AST_DEC_VAR_DEC extends AST_DEC {
	/****************/
	/* DATA MEMBERS */
	/****************/
	public AST_VAR_DEC v;

	/*******************/
	/* CONSTRUCTOR(S) */
	/*******************/
	public AST_DEC_VAR_DEC(AST_VAR_DEC v) {
		this.v = v;

		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		if (v != null)
			System.out.print("====================== dec -> varDec\n");
	}

	

	public TYPE SemantMe() {
		System.out.println("DEC VARDEC" + "- semantme");
		if (v != null) {
			return v.SemantMe();
		}
		
		return null;
	}
	public TEMP IRme() {
		System.out.println("DEC_VARDEC" + "- IRme");
		if (v != null) {
			return v.IRme();
		}	
		return null;
	}

}