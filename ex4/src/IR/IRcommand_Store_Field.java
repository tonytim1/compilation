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

public class IRcommand_Store_Field extends IRcommand {
	public TEMP val;
	String className;
	String var;

			public IRcommand_Store_Field(String className, String varName, TEMP value) {
		this.className = className;
		this.var = varName;
		this.val = value;
	}

	/***************/
	/* MIPS me !!! */
	/***************/
	public void MIPSme() {
				MIPSGenerator.getInstance().store_field(offset, val);
	}
}