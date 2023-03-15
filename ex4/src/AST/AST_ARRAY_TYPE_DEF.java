package AST;

import SYMBOL_TABLE.SYMBOL_TABLE;
import TEMP.TEMP;
import TYPES.*;

public class AST_ARRAY_TYPE_DEF extends AST_Node {
	public String id;
	public AST_TYPE type;

	/*******************/
	/* CONSTRUCTOR(S) */
	/*******************/
	public AST_ARRAY_TYPE_DEF(String id, AST_TYPE type, int line) {
		this.id = id;
		this.type = type;
		this.line = line;

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.print("====================== 		arrayTypedef ::= 	array ID = type[]; \n");

		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
	}

	/*************************************************/
	/* The printing message ------------------------ */
	/*************************************************/
	

	public TYPE SemantMe() {

		System.out.println("ATD" + "- semantme");

		TYPE typeType = type.SemantMe();

				if (!(SYMBOL_TABLE.getInstance().getScope().equals("global"))) {
			System.out.format(">> ERROR [%d] array dec in wrong scope\n", line);
			printError(line);
		}

				if (typeType == null || typeType instanceof TYPE_VOID || typeType instanceof TYPE_NIL) {
			System.out.format(">> ERROR [%d] non existing type\n", line);
			printError(line);
		}

				if (SYMBOL_TABLE.getInstance().find(id) != null) {
			System.out.format(">> ERROR [%d] %s is already declared.\n", line, id);
			printError(line);
		}

				SYMBOL_TABLE.getInstance().enter(id, new TYPE_ARRAY(typeType, id));

				return null;
	}

	public TEMP IRme() {
		System.out.println("ATD- IRme");
		return null;
	}
}