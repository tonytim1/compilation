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

public class IRcommand_Declare_Global_Object extends IRcommand_Assign_Non_Temp {

	public IRcommand_Declare_Global_Object(String id) {
		this.id = id;
		UpdateIRName("IRcommand_Assign_Non_Temp");
	}

	/***************/
	/* MIPS me !!! */
	/***************/
	public void MIPSme() {
		System.out.println("IRcommand_Declare_Global_Object" + " - MIPSme");
		MIPSGenerator.getInstance().declare_global_object(id);

	}
}
