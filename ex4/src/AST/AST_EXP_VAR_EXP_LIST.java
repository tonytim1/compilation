package AST;

import TEMP.*;
import TYPES.*;
import IR.*;

public class AST_EXP_VAR_EXP_LIST extends AST_EXP {
	public AST_VAR var;
	public String id;
	public AST_EXP_LIST list;
	public TYPE_CLASS tl;

	public AST_EXP_VAR_EXP_LIST(AST_VAR var, String id, AST_EXP_LIST list, int line) {
		this.var = var;
		this.id = id;
		this.list = list;
		this.line = line;

		SerialNumber = AST_Node_Serial_Number.getFresh();

		System.out.print("====================== exp -> vardot_explist\n");

	}

	

	public TYPE SemantMe() {
				System.out.println("EXP VARDOT EXPLIST - semant me");
		TYPE t1 = var.SemantMe();
		if (t1 == null || !(t1 instanceof TYPE_CLASS)) 		{
			System.out.println(">> ERROR [" + line + "] var.dot is of wrong class");
			printError(line);
		}
		tl = (TYPE_CLASS)t1;

		TYPE t2 = isFuncOfClass(t1.name, id, list, this.line);
		if (t2 == null) {
			System.out.println(">> ERROR [" + line + "] var.dot is wrong");
			printError(line);
		}

		return t2;

	}

	public TEMP_LIST IRme(int ignore) {
		System.out.println("EXP VARDOT EXPLIST - IR me");
		if (list == null) {
			return new TEMP_LIST(var.IRme(), null);
		}
		return new TEMP_LIST(var.IRme(), list.IRme(0));
	}

	public TEMP IRme()
	{
		System.out.println("EXP VARDOT EXPLIST - IRme  ");
		return vardotIR(var, list, tl, id);
	}
}