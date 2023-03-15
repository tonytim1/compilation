package AST;

public class AST_FUNC_LIST extends AST_Node {
	/****************/
	/* DATA MEMBERS */
	/****************/
	public AST_FUNC head;
	public AST_FUNC_LIST tail;

	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_FUNC_LIST(AST_FUNC head, AST_FUNC_LIST tail) {
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		this.head = head;
		this.tail = tail;
	}

	/******************************************************/
	/* The printing message for a type name list AST node */
	/******************************************************/
	public void PrintMe() {
		/**************************************/
		/* AST NODE TYPE = AST TYPE NAME LIST */
		/**************************************/
		System.out.print("AST TYPE NAME LIST\n");

		/*************************************/
		/* RECURSIVELY PRINT HEAD + TAIL ... */
		/*************************************/
		if (head != null)
			head.PrintMe();
		if (tail != null)
			tail.PrintMe();

		/**********************************/
		/* PRINT to AST GRAPHVIZ DOT file */
		/**********************************/
		AST_GRAPHVIZ.getInstance().logNode(
				SerialNumber,
				"TYPE-NAME\nLIST\n");

		/****************************************/
		/* PRINT Edges to AST GRAPHVIZ DOT file */
		/****************************************/
		if (head != null)
			AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, head.SerialNumber);
		if (tail != null)
			AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, tail.SerialNumber);
	}

}
