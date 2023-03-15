/***********/
/* PACKAGE */
/***********/
package IR;

/*******************/
/* GENERAL IMPORTS */
/*******************/

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TEMP.*;
import MIPS.*;

public class IRcommand_Const_Int extends IRcommand_Assign {
	//TEMP dst;
	int val;

	public IRcommand_Const_Int(TEMP t, int value) {
		this.dst = t;
		this.val = value;
		UpdateIRName("IRcommand_Assign");
	}

	/***************/
	/* MIPS me !!! */
	/***************/
	public void MIPSme() {
		System.out.println("IRcommand_Const_Int" + "- MIPSme");
		MIPSGenerator.getInstance().li(dst, val);
	}
}
