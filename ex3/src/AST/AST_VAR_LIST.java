package AST;

public class AST_VAR_LIST extends AST_Node {
	/****************/
	/* DATA MEMBERS */
	/****************/
	public AST_TYPE type;
	public String head;
	public AST_VAR_LIST tail;
	
	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_VAR_LIST(String head, AST_VAR_LIST tail, AST_TYPE type)
	{
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		if (tail != null) System.out.format("====================== multiVar -> type ID( %s ) COMMA multiVar\n", head);
		if (tail == null) System.out.format("====================== multiVar -> type ID( %s )     \n", head);

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.head = head;
		this.tail = tail;
		this.type = type;
	}
	
	/******************************************************/
	/* The printing message for an expression list AST node */
	/******************************************************/
	public void PrintMe()
	{
		/**************************************/
		/* AST NODE TYPE = AST VAR LIST */
		/**************************************/
		System.out.print("AST NODE VAR LIST\n");

		/*************************************/
		/* RECURSIVELY PRINT HEAD + TAIL ... */
		/*************************************/
		if (type != null) type.PrintMe();
		if (head != null) System.out.format("ID: %s\n", head); 
		if (tail != null) tail.PrintMe();

		/**********************************/
		/* PRINT to AST GRAPHVIZ DOT file */
		/**********************************/
		AST_GRAPHVIZ.getInstance().logNode(
			SerialNumber,
			"VAR\nLIST\n");
		
		/****************************************/
		/* PRINT Edges to AST GRAPHVIZ DOT file */
		/****************************************/
		if (type != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber,type.SerialNumber);
		if (tail != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber,tail.SerialNumber);
	}
}
