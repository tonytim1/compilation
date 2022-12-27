package AST;
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_CLASS_DEC extends AST_Node {

	public String id1;
	public String id2;
	public AST_C_FIELD_LIST cFieldList;

	
	public AST_CLASS_DEC(int lineNumber, String id1, String id2, AST_C_FIELD_LIST cFieldList)
	{
		super(lineNumber);
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

	public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{	
		SYMBOL_TABLE s = SYMBOL_TABLE.getInstance();

		//check if depth != 0 or class name already declared in scope
		if (!(s.isGlobalScope()) || s.find(id1) != null) {
		    System.out.format(">> ERROR [%d] not in global scope or %s is not found - class AST_CLASS_DEC\n",lineNumber, id1);
			throw new SEMANTIC_EXCEPTION(lineNumber);
        }

		/*************************/
		/* [1] Begin Class Scope */
		/*************************/
		s.beginScope();

		TYPE father = null;

		if ( id2 != null)
		{
			father = s.find(id2);
			if (father == null || father.typeName != "class") {
			    System.out.format(">> ERROR [%d] can't find father %s or father's type isn't class - class AST_CLASS_DEC\n",lineNumber, id2);
				throw new SEMANTIC_EXCEPTION(lineNumber);
			}
		}

		/***************************/
		/* [2] Semant Data Members */
		/***************************/
		TYPE_CLASS classType = new TYPE_CLASS((TYPE_CLASS) father, id1, (TYPE_LIST) cFieldList.SemantMe());

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
		return classType;
	}
}
