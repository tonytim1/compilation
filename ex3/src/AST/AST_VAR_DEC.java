package AST;
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_VAR_DEC extends AST_Node {

	public AST_TYPE type;
	public String id;
	public AST_EXP exp;
	public AST_NEW_EXP newExp;

	public AST_VAR_DEC(int lineNumber, AST_TYPE type, String id, AST_EXP exp, AST_NEW_EXP newExp) {
		super(lineNumber);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
		
		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		if (exp == null) {
		    if (newExp == null) {
		        System.out.format("====================== varDec -> type:%s ID( %s ) SEMICOLON\n", type.id, id);
		    } else {
		        System.out.format("====================== varDec -> type:%s ID( %s ) ASSIGN new_exp SEMICOLON\n", type.id, id);
		    }
		} else {
			System.out.format("====================== varDec -> type:%s ID( %s ) ASSIGN exp SEMICOLON\n", type.id, id);
		}

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.type = type;
		this.id = id;
		this.exp = exp;
		this.newExp = newExp;
	}

	public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{
		TYPE t;
		TYPE expType;
	
		/****************************/
		/* [1] Check If Type exists */
		/****************************/
		t = type.SemantMe();
		if (t == null)
		{
			System.out.format(">> ERROR [%d] non existing type %s - class AST_VAR_DEC\n",lineNumber, type.id);
			throw new SEMANTIC_EXCEPTION(lineNumber + 1);
		}
		
		/**************************************/
		/* [2] Check That Name does NOT exist */
		/**************************************/
		if (SYMBOL_TABLE.getInstance().findInScope(id) != null)
		{
			System.out.format(">> ERROR [%d] variable %s already exists in scope - class AST_VAR_DEC\n",lineNumber,id);
			throw new SEMANTIC_EXCEPTION(lineNumber + 1);
		}

		/**************************************/
		/* [3] Check That it can be assign to exp */
		/**************************************/
		if (exp != null) {
			TYPE t3 = exp.SemantMe();
			System.out.format(">> INFO [%d] trying to assign exp %s to var %s - class AST_VAR_DEC\n", lineNumber, t3.typeName, t.typeName);
			if (!t.canAssign(t3)) {
				System.out.format(">> ERROR [%d] can't assign exp %s to var %s - class AST_VAR_DEC\n", lineNumber, t3.typeName, t.typeName);
				throw new SEMANTIC_EXCEPTION(lineNumber + 1);
			}
		}

		/**************************************/
		/* [3] Check That it can be assign to newExp */
		/**************************************/
		if (newExp != null) {
			TYPE t2 = newExp.SemantMe();
			System.out.format(">> INFO [%d] trying to assign new exp %s to var %s - class AST_VAR_DEC\n",lineNumber,t2.typeName, t.typeName);
			if (!t.canAssign(t2)) {
				System.out.format(">> ERROR [%d] can't assign new exp %s to var %s - class AST_VAR_DEC\n",lineNumber,t2.typeName, t.typeName);
				throw new SEMANTIC_EXCEPTION(lineNumber + 1);
			}
			else {
				if (t.typeName == "class") {
					TYPE_CLASS class1 = (TYPE_CLASS) t;
					TYPE_CLASS class2 = (TYPE_CLASS) t2;
					System.out.format(">> INFO [%d] Assign to %s father: %s, %s father %s - class AST_STMT_ASSIGN_NEW\n",lineNumber, class1.name, class1.father, class2.name, class2.father);
					boolean isOk = false;
					if (class1.name != class2.name) {
						// check if class1 is a father of class2 and then it's ok
						TYPE_CLASS father = class2.father;
						while (father != null) {
							if (father.name == class1.name) {
								isOk = true;
							}
							father = father.father;
						}
						if (!isOk) {
							System.out.format(">> ERROR [%d] can't assign class %s to class %s - class AST_VAR_DEC\n",lineNumber,class2.name, class1.name);
							throw new SEMANTIC_EXCEPTION(lineNumber + 1);
						}
					}
				}
			}
		}

		/***************************************************/
		/* [4] Enter the Var Type to the Symbol Table */
		/***************************************************/
		SYMBOL_TABLE.getInstance().enter(id,t);

		/*********************************************************/
		/* [5] Return value is irrelevant for class declarations */
		/*********************************************************/
		System.out.format("INFO [%d] ast var dec got var:%s with type name:%s typeName:%s - class AST_VAR_DEC\n", lineNumber, id, t.name, t.typeName);
		return t;
	}
}
