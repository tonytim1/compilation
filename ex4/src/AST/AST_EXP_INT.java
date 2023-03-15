package AST;

import TYPES.*;
import TEMP.*;
import IR.*;
import SYMBOL_TABLE.*;

public class AST_EXP_INT extends AST_EXP {
	public int value;

	public AST_EXP_INT(int value) {
		SerialNumber = AST_Node_Serial_Number.getFresh();

		this.value = value;

		System.out.format("====================== exp -> INT(%d)\n", value);
	}

	

	public TYPE SemantMe() {
		System.out.println("EXP INT (recoginzed int)- semant me");
		return TYPE_INT.getInstance();
	}

	public TEMP IRme() {
		System.out.println("EXP INT- IRme");

		TEMP t = TEMP_FACTORY.getInstance().getFreshTEMP();
		IR.getInstance().Add_IRcommand(new IRcommand_Const_Int(t, value));
		return t;
	}
}