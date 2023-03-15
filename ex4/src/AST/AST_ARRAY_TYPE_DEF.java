package AST;

import SYMBOL_TABLE.SYMBOL_TABLE;
import TEMP.TEMP;
import TYPES.*;

public class AST_ARRAY_TYPE_DEF extends AST_Node {
	public String id;
	public AST_TYPE type;

	public AST_ARRAY_TYPE_DEF(String id, AST_TYPE type, int line) {
		this.id = id;
		this.type = type;
		this.line = line;

		System.out.print("====================== 		arrayTypedef ::= 	array ID = type[]; \n");

		SerialNumber = AST_Node_Serial_Number.getFresh();
	}

	

	public TYPE SemantMe() {


		TYPE typeType = type.SemantMe();

				if (!(SYMBOL_TABLE.getInstance().getScope().equals("global"))) {
						printError(line);
		}

				if (typeType == null || typeType instanceof TYPE_VOID || typeType instanceof TYPE_NIL) {
						printError(line);
		}

				if (SYMBOL_TABLE.getInstance().find(id) != null) {
						printError(line);
		}

				SYMBOL_TABLE.getInstance().enter(id, new TYPE_ARRAY(typeType, id));

				return null;
	}

	public TEMP IRme() {
		return null;
	}
}