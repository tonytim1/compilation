package AST;
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_CLASS_DEC extends AST_Node {

	public String id1;
	public String id2;
	public AST_C_FIELD_LIST cFieldList;

	
	public AST_CLASS_DEC(String id1, String id2, AST_C_FIELD_LIST cFieldList)
	{
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
		
		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		if ( id2 != null)
		    System.out.format("====================== classDec -> CLASS ID( %s ) EXTENDS ID( %s ) LBRACE classCont RBRACE", id1, id2);
		else
		    System.out.format("====================== classDec -> CLASS ID( %s ) LBRACE classCont RBRACE", id1);

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.id1 = id1;
		this.id2 = id2;
		this.cFieldList = cFieldList;
	}

	public TYPE SemantMe()
	{	
		/*************************/
		/* [1] Begin Class Scope */
		/*************************/
		SYMBOL_TABLE.getInstance().beginScope();

		/***************************/
		/* [2] Semant Data Members */
		/***************************/
        TYPE_CLASS t = new TYPE_CLASS(id2,id1,cFieldList.SemantMe());
        /*****************/
        /* [3] End Scope */
        /*****************/
        SYMBOL_TABLE.getInstance().endScope();

        /************************************************/
        /* [4] Enter the Class Type to the Symbol Table */
        /************************************************/
        SYMBOL_TABLE.getInstance().enter(id1,t);

		/*********************************************************/
		/* [5] Return value is irrelevant for class declarations */
		/*********************************************************/
		return null;		
	}
}
