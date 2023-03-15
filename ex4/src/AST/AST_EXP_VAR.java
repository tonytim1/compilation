package AST;

import TEMP.TEMP;
import TYPES.*;

public class AST_EXP_VAR extends AST_EXP {
	public AST_VAR var;

	public AST_EXP_VAR(AST_VAR var) {
		this.var = var;
		SerialNumber = AST_Node_Serial_Number.getFresh();

		System.out.print("====================== exp -> var\n");

	}

	

	public TYPE SemantMe() {
		System.out.println("EXP VAR - semant me");

		return var.SemantMe();
	}

	public TEMP IRme() {
		System.out.println("EXP VAR - IRme");
		return var.IRme();
	}
}
