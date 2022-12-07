package AST;

public class AST_TYPE_ID extends AST_Node {

	public AST_TYPE type;
	public String id;
	public AST_TYPE_ID typeID;
	
	public AST_TYPE_ID(AST_TYPE type, String id, AST_TYPE_ID typeID)
	{
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
		
		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		if (typeID==null) {
			System.out.format("====================== typeID -> type ID( %s )\n", id);
		else {
			System.out.format("====================== typeID -> type ID( %s ) COMMA typeID\n", id);
		}

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.type = type;
		this.id = id;
		this.typeID = typeID;
	}
}
