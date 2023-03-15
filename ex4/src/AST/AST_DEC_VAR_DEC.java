package AST;

import TYPES.*;
import TEMP.*;

public class AST_DEC_VAR_DEC extends AST_DEC {
	public AST_VAR_DEC v;

	public AST_DEC_VAR_DEC(AST_VAR_DEC v) {
		this.v = v;

		SerialNumber = AST_Node_Serial_Number.getFresh();

		if (v != null) {
			System.out.print("====================== dec -> varDec\n");
			}
	}

	

	public TYPE SemantMe() {
		System.out.println("DEC VARDEC" + "- semantme");
		if (v != null) {
			return v.SemantMe();
		}
		
		return null;
	}
	public TEMP IRme() {
		System.out.println("DEC_VARDEC" + "- IRme");
		if (v != null) {
			return v.IRme();
		}	
		return null;
	}

}