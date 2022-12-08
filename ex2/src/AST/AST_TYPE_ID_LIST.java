package AST;

public class AST_TYPE_ID_LIST extends AST_Node {

	public AST_TYPE_ID head;
	public AST_TYPE_ID_LIST tail;

	public AST_TYPE_ID_LIST(AST_TYPE_ID head, AST_TYPE_ID_LIST tail)
	{
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
		
		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		if (tail==null) {
			System.out.format("====================== typeID -> type ID( %s )\n", id);
		} else {
			System.out.format("====================== typeID -> type ID( %s ) COMMA typeID\n", id);
		}

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.head = head;
		this.tail = tail;
	}
}
