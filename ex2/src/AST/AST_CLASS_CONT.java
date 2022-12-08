package AST;

public class AST_CLASS_CONT extends AST_Node {

	public AST_C_FIELD head;
	public AST_CLASS_CONT tail;
	
	public AST_CLASS_CONT(AST_C_FIELD head, AST_CLASS_CONT tail)
	{
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		if (tail != null) System.out.print("====================== classCont -> cField classCont\n");
		if (tail == null) System.out.print("====================== classCont -> cField      \n");

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.head = head;
		this.tail = tail;
	}

	public void PrintMe()
	{
		/**************************************/
		/* AST NODE TYPE = AST CLASS */
		/**************************************/
		System.out.print("AST NODE CLASS CONT\n");

		/*************************************/
		/* RECURSIVELY PRINT HEAD + TAIL ... */
		/*************************************/
		if (head != null) head.PrintMe();
		if (tail != null) tail.PrintMe();

		/**********************************/
		/* PRINT to AST GRAPHVIZ DOT file */
		/**********************************/
		AST_GRAPHVIZ.getInstance().logNode(
			SerialNumber,
			"CLASS CONT\n");
		
		/****************************************/
		/* PRINT Edges to AST GRAPHVIZ DOT file */
		/****************************************/
		if (head != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber,head.SerialNumber);
		if (tail != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber,tail.SerialNumber);
	}
	
}
