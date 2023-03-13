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

public class IRcommand_Jump_If_Eq_To_Zero extends IRcommand
{
	TEMP t;
	public String label_name;
	
	public IRcommand_Jump_If_Eq_To_Zero(TEMP t, String label_name)
	{
		this.t          = t;
		this.label_name = label_name;
	}
	
	/***************/
	/* MIPS me !!! */
	/***************/
	public void MIPSme()
	{
		System.out.format("IRcommand_Jump_If_Eq_To_Zero -------- %s\n", label_name);
		MIPSGenerator.getInstance().beqz(t.toString() ,label_name);
	}
	public TEMP_LIST getLiveTemp(TEMP_LIST input){
		TEMP_LIST result = input.clone();
		result.add(t);
		return result;
	}
}
