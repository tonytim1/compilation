package AST;

import TYPES.*;
import TEMP.*;

public class AST_STMT_LIST extends AST_Node {
	/****************/
	/* DATA MEMBERS */
	/****************/
	public AST_STMT head;
	public AST_STMT_LIST tail;

	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_STMT_LIST(AST_STMT head, AST_STMT_LIST tail) {
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
			System.out.print("====================== stmts -> stmt stmts\n");
		if (tail == null)
			System.out.print("====================== stmts -> stmt      \n");

		/*******************************/
		/* COPY INPUT DATA NENBERS ... */
		/*******************************/
	}

	/******************************************************/
	/* The printing message for a statement list AST node */
	/******************************************************/
	

	public TYPE SemantMe() {
		System.out.println("STMT LIST - semant me");
		if (head != null)
			head.SemantMe();
		if (tail != null)
			tail.SemantMe();

		return null;
	}
	public TEMP IRme()
	{
		if (head != null) head.IRme();
		if (tail != null) tail.IRme();
		
		return null;
	}

}
