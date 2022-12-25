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
		SYMBOL_TABLE s = SYMBOL_TABLE.getInstance();

		//check if depth != 0 or class name already declared in scope
		if (!(s.isGlobalScope()) || s.find(id1) != null)
			System.exit(0);

		/*************************/
		/* [1] Begin Class Scope */
		/*************************/
		s.beginScope();

		TYPE father = null;

		if ( id2 != null)
		{
			father = s.find(id2);
			if (father == null || !(father.isClass()))
				System.exit(0);
		}

		/***************************/
		/* [2] Semant Data Members */
		/***************************/	
		TYPE_CLASS classType = new TYPE_CLASS((TYPE_CLASS) father, id1, (TYPE_CLASS_VAR_DEC_LIST) cFieldList.SemantMe());
        /*****************/
        /* [3] End Scope */
        /*****************/
        SYMBOL_TABLE.getInstance().endScope();

        /************************************************/
        /* [4] Enter the Class Type to the Symbol Table */
        /************************************************/
        SYMBOL_TABLE.getInstance().enter(id1, classType);

		/*********************************************************/
		/* [5] Return value is irrelevant for class declarations */
		/*********************************************************/
		return null;		
	}
}
