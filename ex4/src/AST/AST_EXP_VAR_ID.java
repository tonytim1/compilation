package AST;

import TYPES.*;
import TEMP.*;
import IR.*;
import SYMBOL_TABLE.*;

public class AST_EXP_VAR_ID extends AST_EXP {
	public AST_VAR var;
	public String id;
	public TYPE_CLASS tl; 
	public AST_EXP_VAR_ID(AST_VAR var, String id, int line) {
		this.var = var;
		this.id = id;
		this.line = line;
		SerialNumber = AST_Node_Serial_Number.getFresh();

		System.out.print("=============== exp -> vardot\n");

	}

	

	public TYPE SemantMe() {
		TYPE t1 = var.SemantMe();
		if (t1 == null || !(t1 instanceof TYPE_CLASS)) 		{
			printError(line);
		}
		tl = (TYPE_CLASS)t1;

		TYPE t2 = isFuncOfClass(t1.name,id,null,this.line);
		if (t2 == null)
		{
			printError(line);
		}
		
		return t2;
		
	}
	public TEMP IRme()
	{
	return vardotIR(var, null, tl, id);
	}
}