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
	String class;
	String var;

	// this.var = value
	// where var is a field of class c [i.e. x = t3, where x is a field] .
	public IRcommand_Store_Field(String class, String varName, TEMP value) {
		this.class = class;
		this.var = varName;
		this.val = value;
	}

	/***************/
	/* MIPS me !!! */
	/***************/
	public void MIPSme() {
		System.out.println("IRcommand_Store_Field" + "- MIPSme");
		MIPSGenerator.getInstance().store_field(offset, val);
	}
}