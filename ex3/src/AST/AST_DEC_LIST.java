package AST;
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_DEC_LIST extends AST_Node
{
	/****************/
	/* DATA MEMBERS */
	/****************/
	public AST_DEC head;
	public AST_DEC_LIST tail;

	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_DEC_LIST(int lineNumber, AST_DEC head,AST_DEC_LIST tail)
	{
		super(lineNumber);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		if (tail != null) System.out.print("====================== multiDec -> dec multiDec \n");
		if (tail == null) System.out.print("====================== Dec -> dec      \n");

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.head = head;
		this.tail = tail;
	}
	
	public TYPE SemantMe()
	{
		if (head != null) head.SemantMe();
		if (tail != null) tail.SemantMe();
		
		return null;
	}
}
