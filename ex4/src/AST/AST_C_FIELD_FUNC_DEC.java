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
		return func.SemantMe();
	}

	public TEMP IRme() {
		func.IRme();
		return null;
	}
}
