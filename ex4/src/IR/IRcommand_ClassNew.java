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

public class IRcommand_ClassNew extends IRcommand
{
	TEMP dst;
	String name;

	public IRcommand_ClassNew(TEMP dst, String name)
	{
		this.dst = dst;
		this.name = name;
	}

	public void MIPSme()
	{	
		MIPSGenerator.getInstance().jal(String.format("class_%s", name));
		MIPSGenerator.getInstance().pop_from_stack(dst.toString());        
    }
		
	public TEMP_LIST getLiveTemp(TEMP_LIST input){
		TEMP_LIST result = input.clone();
		result.remove(dst);
		return result;
	}
}