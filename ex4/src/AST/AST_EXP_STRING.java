package AST;

import TYPES.*;
import TEMP.*;
import IR.*;
import SYMBOL_TABLE.*;

public class AST_EXP_STRING extends AST_EXP {
	public String s;
	public String scope;
	public String label; // holds the label of this string, for IRme

	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_EXP_STRING(Object s) {
		this.s = ((String) s).split("\"")[1];
		
		SerialNumber = AST_Node_Serial_Number.getFresh();
		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.print("====================== exp -> STRING\n");
	}

	/*************************************************/
	/* The printing message for a XXX node */
	/*************************************************/
	

	public TYPE SemantMe() {
		System.out.println("EXP STRING (recoginzed string)- semant me");
		scope = SYMBOL_TABLE.getInstance().getScope();
		return TYPE_STRING.getInstance();
	}

	public TEMP IRme() {
		System.out.println("EXP STRING (recoginzed string)- IRme");

		TEMP t = TEMP_FACTORY.getInstance().getFreshTEMP();

		this.label = IRcommand.getFreshLabel("const_string");

		IR.getInstance().Add_IRcommand(new IRcommand_Const_String(t, label, s));

		return t;
	}

}