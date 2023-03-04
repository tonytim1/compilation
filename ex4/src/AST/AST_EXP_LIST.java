package AST;
import TYPES.*;
import SYMBOL_TABLE.*;
import IR.*;
import TEMP.*;

public class AST_EXP_LIST extends AST_Node 
{
	/****************/
	/* DATA MEMBERS */
	/****************/
	public AST_EXP head;
	public AST_EXP_LIST tail;
	
	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_EXP_LIST(int lineNumber, AST_EXP head, AST_EXP_LIST tail)
	{
		super(lineNumber);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		if (tail != null) System.out.print("====================== multiExp	-> exp COMMA multiExp\n");
		if (tail == null) System.out.print("====================== multiExp -> exp      \n");

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.head = head;
		this.tail = tail;
	}

	public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{
		TYPE t1 = null;
		TYPE t2 = null;

		if (head != null) t1 = head.SemantMe();
		if (tail != null) t2 = tail.SemantMe();

		return new TYPE_LIST(t1, (TYPE_LIST) t2);
	}

	public TEMP_LIST IRme() {
		// Returns a linked list of IR expressions according to each element on list.
		TEMP t_head = head.IRme();
		TEMP_LIST t_tail = null;
		if (tail != null) t_tail = tail.IRme();
		return new TEMP_LIST (t_head, t_tail);
	}
}
