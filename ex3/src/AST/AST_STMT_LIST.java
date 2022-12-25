package AST;

public class AST_STMT_LIST extends AST_Node
{
	/****************/
	/* DATA MEMBERS */
	/****************/
	public AST_STMT head;
	public AST_STMT_LIST tail;

	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_STMT_LIST(AST_STMT head,AST_STMT_LIST tail)
	{
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		if (tail != null) System.out.print("====================== multiStmt -> stmt multiStmt\n");
		if (tail == null) System.out.print("====================== multiStmt -> stmt      \n");

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
