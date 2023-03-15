package AST;

import TYPES.*;

public class AST_TYPE_STRING extends AST_TYPE {

	public AST_TYPE_STRING(int line) {

		this.typeName = "string";
		this.line = line;

		SerialNumber = AST_Node_Serial_Number.getFresh();

		System.out.print("====================== type -> TYPE_STRING \n");
	}

	

	public TYPE SemantMe() {
				return TYPE_STRING.getInstance();
	}
}