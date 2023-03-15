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

public class IRcommand_Jump_Beqz extends IRcommand_Conditional_Jump {
	//TEMP oprnd1;
	//String label;

	public IRcommand_Jump_Beqz(TEMP t, String label) {
		this.oprnd1 = t;
		this.label = label;
		changeName("IRcommand_Conditional_Jump");
	}

	/***************/
	/* MIPS me !!! */
	/***************/
	public void MIPSme() {
		System.out.println("IRcommand_Jump_Beqz" + "- MIPSme");
		MIPSGenerator.getInstance().beqz(oprnd1, label);
	}
}
