package AST;

import TEMP.TEMP;
import TYPES.*;

public class AST_EXP_SIMPLE extends AST_EXP {
	public AST_EXP exp;

	public AST_EXP_SIMPLE(AST_EXP exp) {
		this.exp = exp;
		SerialNumber = AST_Node_Serial_Number.getFresh();

		System.out.print("====================== exp -> (exp)\n");

	}

	

	public TYPE SemantMe() {
		return exp.SemantMe();
	}

	public TEMP IRme() {

		return exp.IRme();
	}
}
