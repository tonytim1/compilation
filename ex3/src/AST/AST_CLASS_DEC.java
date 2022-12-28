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
		TYPE_LIST fatherDataMembers = null;

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

		TYPE_CLASS classType = new TYPE_CLASS((TYPE_CLASS) father, id1, null);
		System.out.format("creating class type %s\n",id1);
		s.getInstance().enter(id1, classType);

		/*************************/
		/* [2] Begin Class Scope */
		/*************************/
		s.beginScope();
		s.curr_class = classType;
		TYPE_LIST dataMembers = (TYPE_LIST) cFieldList.SemantMe();

//		dataMembers.printList();
		System.out.format("print list %s\n", "data members");
		TYPE_LIST currList = dataMembers;
		while (currList.head != null) {
			System.out.format(" - %s, %s: %s\n", currList.head.varName, currList.head.typeName, currList.head);
			if (currList.tail == null) {
				break;
			}
			currList = currList.tail;
		}

//		fatherDataMembers.printList();
		if (fatherDataMembers != null) {
			System.out.format("print list %s\n", "fathers' members");
			currList = fatherDataMembers;
			while (currList.head != null) {
				System.out.format(" - %s, %s: %s\n", currList.head.varName, currList.head.typeName, currList.head);
				if (currList.tail == null) {
					break;
				}
				currList = currList.tail;
			}
		}

		/*************************/
		/* [3] Add fathers' data members */
		/*************************/
		if (fatherDataMembers != null) {
			TYPE_LIST currDataMembers = dataMembers;
			while (currDataMembers.tail != null) {
				currDataMembers = currDataMembers.tail;
			}
			System.out.format("INFO[%d] added to class the fathers' data members: %s", lineNumber, fatherDataMembers);
			currDataMembers.tail = fatherDataMembers;
		}

		//update class
		classType.data_members = dataMembers;
//		dataMembers.printList();
		System.out.format("print list %s\n", "all");
		currList = dataMembers;
		while (currList.head != null) {
			System.out.format(" - %s, %s: %s\n", currList.head.varName, currList.head.typeName, currList.head);
			if (currList.tail == null) {
				break;
			}
			currList = currList.tail;
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
