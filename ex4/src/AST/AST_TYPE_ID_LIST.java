package AST;
import TYPES.*;
import SYMBOL_TABLE.*;
import IR.*;
import TEMP.*;
public class AST_TYPE_ID_LIST extends AST_Node {

	public AST_TYPE_ID head;
	public AST_TYPE_ID_LIST tail;

	public AST_TYPE_ID_LIST(int lineNumber, AST_TYPE_ID head, AST_TYPE_ID_LIST tail)
	{
		super(lineNumber);
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
	
	public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{
		// currently not used, checked from func dec 
		TYPE t1 = null;
		TYPE t2 = null;

		if (head != null) t1 = head.SemantMe();
		if (tail != null) t2 = tail.SemantMe();

		return new TYPE_LIST(t1, (TYPE_LIST) t2);
	}
}
