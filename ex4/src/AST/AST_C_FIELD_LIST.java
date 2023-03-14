package AST;
import TYPES.*;
import SYMBOL_TABLE.*;
import IR.*;
import TEMP.*;


public class AST_C_FIELD_LIST extends AST_Node {

	public AST_C_FIELD cField;
	public AST_C_FIELD_LIST cFieldList;
	
	
	public AST_C_FIELD_LIST(int lineNumber, AST_C_FIELD cField, AST_C_FIELD_LIST cFieldList)
	{
		super(lineNumber);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		if (cFieldList != null) {
			System.out.format("====================== cFieldList -> cField cFieldList\n");
		}
		else {
			System.out.format("====================== cFieldList -> cField\n");
		}
		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.cField = cField;
		this.cFieldList = cFieldList;
	}
	
	public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{
		TYPE t1 = null;
		TYPE t2 = null;
		System.out.format("[%d] semanting cFieldList\n", lineNumber);

		if (cField != null) t1 = cField.SemantMe();
		if (cFieldList != null) t2 = cFieldList.SemantMe();

		return new TYPE_LIST(t1, (TYPE_LIST) t2);
	}

	public TEMP_LIST IRme(int ignore) {
	    if ((cField == null) && (cFieldList == null)) {
			return null;
		}
		else if ((cField != null) && (cFieldList == null)) {
			return new TEMP_LIST(cField.IRme(), null);
		}
		else {
			return new TEMP_LIST(cField.IRme(), cFieldList.IRme(0));
		}
	}
}
