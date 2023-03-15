package AST;

import TYPES.*;
import TEMP.*;
import IR.*;

public class AST_C_FIELD_LIST extends AST_Node {
	/****************/
	/* DATA MEMBERS */
	/****************/
	public AST_C_FIELD head;
	public AST_C_FIELD_LIST tail;

	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_C_FIELD_LIST(AST_C_FIELD head, AST_C_FIELD_LIST tail) {
		this.head = head;
		this.tail = tail;
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		if (tail != null)
			System.out.print("====================== cfeilds -> cfeild cfeilds\n");
		if (tail == null)
			System.out.print("====================== cfeilds -> cfeild      \n");

		/*******************************/
		/* COPY INPUT DATA NENBERS ... */
		/*******************************/
	}

	/*************************************************/
	/* The printing message for a binop exp AST node */
	/*************************************************/
	

	public TYPE_LIST SemantMe(int ignore) {
		System.out.println("CFEILD LIST - semant me");

		if (tail == null) {
			return new TYPE_LIST(head.SemantMe(), null);
		} else {
			return new TYPE_LIST(head.SemantMe(), tail.SemantMe(0));
		}
	}

	public TEMP_LIST IRme(int ignore) {
		System.out.println("CFEILD_LIST - IRme");

		if ((head == null) && (tail == null)) {
			return null;
		} else if ((head != null) && (tail == null)) {
			return new TEMP_LIST(head.IRme(), null);
		} else {
			return new TEMP_LIST(head.IRme(), tail.IRme(0));
		}
	}
}
