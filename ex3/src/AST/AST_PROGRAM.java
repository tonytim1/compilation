package AST;

public class AST_PROGRAM extends AST_Node {
	/****************/
	/* DATA MEMBERS */
	/****************/
	public AST_DEC_LIST decList;

	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_PROGRAM(AST_DEC_LIST decList)
	{
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.print("====================== Program -> decList\n");

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.decList = decList;
	}
	public TYPE SemantMe()
	{
		decList.SemantMe();		
		return null;
	}
}
