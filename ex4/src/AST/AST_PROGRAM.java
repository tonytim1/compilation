package AST;

import TYPES.*;
import SYMBOL_TABLE.*;
import TEMP.*;

public class AST_PROGRAM extends AST_Node {
	/****************/
	/* DATA MEMBERS */
	/****************/
	public AST_DEC_LIST list;

	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_PROGRAM(AST_DEC_LIST list, String file) {
		this.list = list;
		SerialNumber = AST_Node_Serial_Number.getFresh();

		getFile(file);

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.print("====================== program -> decs \n");
	}

	@Override
	

	public TYPE SemantMe() {
		System.out.println("\nPROGRAM" + "- semantme");

		list.SemantMe();

		/*********************************************************/
		/* [1] Return value is irrelevant for the program itself */
		/*********************************************************/
		return null;
	}

	public TEMP IRme() {
		System.out.println("\nPROGRAM" + "- IRme");

		list.IRme();

		return null;
	}
}
