package AST;

import TYPES.*;
import TEMP.*;
import IR.*;
import SYMBOL_TABLE.*;

public class AST_EXP_STRING extends AST_EXP {
	public String s;
	public String scope;
	public String label; 
	public AST_EXP_STRING(Object s) {
		this.s = ((String) s).split("\"")[1];
		
		SerialNumber = AST_Node_Serial_Number.getFresh();
		System.out.print("=============== exp -> STRING\n");
	}

	public TYPE SemantMe() {
		scope = SYMBOL_TABLE.getInstance().getScope();
		return TYPE_STRING.getInstance();
	}

	public TEMP IRme() {
		TEMP t = TEMP_FACTORY.getInstance().getFreshTEMP();
		this.label = IRcommand.getFreshLabel("const_string");
		IR.getInstance().Add_IRcommand(new IRcommand_Const_String(t, label, s));
		return t;
	}
}