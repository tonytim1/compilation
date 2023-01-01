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

		/************************************************/
		/* [1] Enter the Class Type to the Symbol Table */
		/************************************************/
		TYPE father = null;
		TYPE_CLASS fatherClass;
		TYPE_LIST fatherDataMembers = new TYPE_LIST(null, null);
		TYPE_LIST currList;

		if ( id2 != null)
		{
			father = s.find(id2);
			if (father == null || father.typeName != "class") {
				System.out.format(">> ERROR [%d] can't find father %s or father's type isn't class - class AST_CLASS_DEC\n",lineNumber, id2);
				throw new SEMANTIC_EXCEPTION(lineNumber);
			}
			fatherClass = (TYPE_CLASS) father;
			fatherDataMembers = (TYPE_LIST) fatherClass.data_members;
		}

		TYPE_LIST allDataMembersHolder = fatherDataMembers;
		TYPE_CLASS classType = new TYPE_CLASS((TYPE_CLASS) father, id1, fatherDataMembers); // starts with father's data members
		System.out.format("creating class type %s\n",id1);
		s.getInstance().enter(id1, classType);

		/*************************/
		/* [2] Begin Class Scope */
		/*************************/
		s.beginScope();
		s.curr_class = classType;

		// insert father members to current scope
		// currList = allDataMembersHolder;
		// while (currList.head != null) {
		// 	System.out.format(" - %s, %s: %s\n", currList.head.varName, currList.head.typeName, currList.head);
		// 	if (currList.head.typeName == "function")
		// 	{
		// 		s.enter(currList.head.name, currList.head);
		// 	}
		// 	else
		// 	{
		// 		s.enter(currList.head.varName, currList.head);
		// 	}
		// 	if (currList.tail == null) {
		// 		break;
		// 	}
		// 	currList = currList.tail;
		// }

		/*************************/
		/* [3] Add the class data members one by one */
		/*************************/
		TYPE newClassAttr = null;
		while ((cFieldList != null) && (cFieldList.cField != null)) {
			newClassAttr = cFieldList.cField.SemantMe();
			// add to datamembers
			if (fatherDataMembers.head == null) {
				fatherDataMembers.head = newClassAttr;
			} else {
				while (fatherDataMembers.tail != null) {
					fatherDataMembers = fatherDataMembers.tail;
				}
				fatherDataMembers.tail = new TYPE_LIST(newClassAttr, null);
			}
			// next variable
			cFieldList = cFieldList.cFieldList;

			// print
			System.out.format("print list %s\n", "all");
			currList = allDataMembersHolder;
			while (currList.head != null) {
				System.out.format(" - %s, %s: %s\n", currList.head.varName, currList.head.typeName, currList.head);
				if (currList.tail == null) {
					break;
				}
				currList = currList.tail;
			}
		}

        /*****************/
        /* [4] End Scope */
        /*****************/
		s.curr_class = null;
        s.getInstance().endScope();

		/*********************************************************/
		/* [5] Return value is irrelevant for class declarations */
		/*********************************************************/
		return classType;
	}
}
