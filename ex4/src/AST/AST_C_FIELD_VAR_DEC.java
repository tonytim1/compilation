package AST;

import TYPES.*;
import TEMP.*;

public class AST_C_FIELD_VAR_DEC extends AST_C_FIELD {
	public AST_VAR_DEC vd;

	public AST_C_FIELD_VAR_DEC(AST_VAR_DEC vd) {
		this.vd = vd;
		SerialNumber = AST_Node_Serial_Number.getFresh();

		if (vd != null) {
			System.out.print("====================== cfield -> varDec\n");
			}
	}


	

	public TYPE SemantMe() {
		System.out.println("CFEILD VARDEC - semant me");
		return vd.SemantMe();

	}
	public TEMP IRme(){
		System.out.println("CFEILD_VARDEC" + "- IRme");
		vd.IRme();
		return null;
	}

}
