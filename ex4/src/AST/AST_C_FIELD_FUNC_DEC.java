package AST;

import TYPES.*;
import TEMP.*;

public class AST_C_FIELD_FUNC_DEC extends AST_C_FIELD {
	public AST_FUNC_DEC func;

	public AST_C_FIELD_FUNC_DEC(AST_FUNC_DEC func) {
		this.func = func;

		SerialNumber = AST_Node_Serial_Number.getFresh();
	}

	

	public TYPE SemantMe() {
		System.out.println("CFEILD FUNCDEC semant me");
		return func.SemantMe();
	}

	public TEMP IRme() {
		System.out.println("CFEILD_FUNCDEC - IRme");
		func.IRme();
		return null;
	}
}
