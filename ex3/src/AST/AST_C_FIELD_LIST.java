package AST;
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_C_FIELD_LIST extends AST_Node {

	public AST_C_FIELD cField;
	public AST_C_FIELD_LIST cFieldList;
	
	
	public AST_C_FIELD_LIST(AST_C_FIELD cField, AST_C_FIELD_LIST cFieldList)
	{
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
	
	public TYPE SemantMe()
	{
		if (cField == null) {
		    return new TYPE_LIST(null, null);
		}
		if (cFieldList == null) {
			return new TYPE_LIST(cField.SemantMe(), null);
		}
		return new TYPE_LIST(cField.SemantMe(), (TYPE_LIST) cFieldList.SemantMe());
	}
}
