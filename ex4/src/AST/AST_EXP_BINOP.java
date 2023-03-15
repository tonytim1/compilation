package AST;

import TYPES.*;
import TEMP.*;

public class AST_EXP_BINOP extends AST_EXP {
	public AST_BINOP binop;

	public AST_EXP_BINOP(AST_BINOP binop) {
		SerialNumber = AST_Node_Serial_Number.getFresh();

		System.out.print("====================== exp -> exp BINOP exp\n");

		this.binop = binop;
	}

	

	public TYPE SemantMe() {
		System.out.println("EXP BINOP - semant me");
		return binop.SemantMe();
	}

	public TEMP IRme() {
		System.out.println("EXP BINOP - IRme");

		return binop.IRme();
	}
}