package AST;

public class AST_TYPE extends AST_Node {

	public int t;
	public String id;

	public AST_TYPE(int t) {
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
		
		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		switch(t) {
		case 1: id = "TYPE_INT"; break;
		case 2: id = "TYPE_STRING"; break;
		case 3: id = "TYPE_VOID"; break;
		}
		System.out.format("====================== type -> %s \n", id);
		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.t = t;
		this.id = id;
	}
	
	public AST_TYPE(String id) {
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
		
		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.format("====================== type -> ID( %s )\n", id);
		
		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.t = 4;
		this.id = null;
	}
	
	public void PrintMe()
	{
		/************************************/
		/* AST NODE TYPE = TYPE NODE */
		/************************************/
		System.out.format("AST NODE TYPE ( %s )\n", id);
		
		/*********************************/
		/* Print to AST GRAPHIZ DOT file */
		/*********************************/
		AST_GRAPHVIZ.getInstance().logNode(
			SerialNumber,
			"TYPE\n");
	}
}
