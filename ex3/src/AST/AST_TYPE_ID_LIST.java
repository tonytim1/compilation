package AST;
import TYPES.*;
import SYMBOL_TABLE.*;

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
			System.out.print("====================== typeID -> type ID( )\n");
		} else {
			System.out.print("====================== typeID -> type ID( ) COMMA typeID\n");
		}

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.head = head;
		this.tail = tail;
	}
	
	public TYPE_LIST SemantMe()
	{
		if (tail == null)
		{
			return new TYPE_LIST(
				head.SemantMe(),
				null);
		}
		else
		{
			return new TYPE_LIST(
				head.SemantMe(),
				tail.SemantMe());
		}
	}
}
