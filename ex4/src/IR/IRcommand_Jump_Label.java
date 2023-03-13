/***********/
/* PACKAGE */
/***********/
package IR;
import TYPES.*;
import TEMP.*;
import MIPS.*;
import REGALLOC.*;

/*******************/
/* GENERAL IMPORTS */
/*******************/

/*******************/
/* PROJECT IMPORTS */
/*******************/

public class IRcommand_Jump_Label extends IRcommand
{
	public String label_name;
	
	public IRcommand_Jump_Label(String label_name)
	{
		this.label_name = label_name;
	}
	
	/***************/
	/* MIPS me !!! */
	/***************/
	public void MIPSme()
	{
		if(label_name.equals("back")){
			MIPSGenerator.getInstance().pop_from_stack("$ra");	
			MIPSGenerator.getInstance().jr("$ra");	
		} else {
			MIPSGenerator.getInstance().jump(label_name);
		}
	}
}
