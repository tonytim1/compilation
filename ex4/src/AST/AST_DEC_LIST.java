package AST;

import TYPES.*;
import TEMP.*;

public class AST_DEC_LIST extends AST_Node {
	/****************/
	/* DATA MEMBERS */
	/****************/
	public AST_DEC head;
	public AST_DEC_LIST tail;

	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_DEC_LIST(AST_DEC head, AST_DEC_LIST tail) {
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
			System.out.print("====================== decs -> dec decs\n");
		if (tail == null)
			System.out.print("====================== decs -> dec      \n");

		/*******************************/
		/* COPY INPUT DATA NENBERS ... */
		/*******************************/

	}

	/*************************************************/
	/* The printing message for a node */
	/*************************************************/
	

	public TYPE SemantMe() {
		System.out.println("DEC_LIST" + "- semantme");
		if (head != null) {
			head.SemantMe();
		}
		if (tail != null) {
			tail.SemantMe();
		}
		return null;
	}

	public TEMP IRme() {
		System.out.println("DEC_LIST" + "- IRme");

		if (head != null)
			head.IRme();
		if (tail != null)
			tail.IRme();

		return null;
	}
}
