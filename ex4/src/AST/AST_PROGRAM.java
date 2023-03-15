package AST;

import TYPES.*;
import SYMBOL_TABLE.*;
import TEMP.*;

public class AST_PROGRAM extends AST_Node {
	public AST_DEC_LIST list;

	public AST_PROGRAM(AST_DEC_LIST list, String file) {
		this.list = list;
		SerialNumber = AST_Node_Serial_Number.getFresh();

		getFile(file);

		System.out.print("====================== program -> decs \n");
	}

	@Override
	

	public TYPE SemantMe() {

		list.SemantMe();

		return null;
	}

	public TEMP IRme() {

		list.IRme();

		return null;
	}
}
