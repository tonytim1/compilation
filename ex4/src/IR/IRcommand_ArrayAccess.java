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

public class IRcommand_ArrayAccess extends IRcommand
{
    TEMP dst;
    TEMP array;
    TEMP index;
	
	public IRcommand_ArrayAccess(TEMP dst, TEMP array, TEMP index)
	{
		this.dst = dst;
        this.array = array;
        this.index = index;
	}
	
	public void MIPSme()
	{
        m = MIPSGenerator.getInstance();
		m.push_to_stack("$s0");
		// put in dst the size of the array
		m.bltz(index.toString(), "abort_array");
		m.lw("$s0",array.toString(),0);
		m.bge(index.toString(),"$s0","abort_array");
		// put in dst the offset value
		m.addi("$s0",index.toString(),1);
		m.sll("$s0","$s0",2);
		// put in dst the address value
		m.add("$s0",array.toString(),"$s0");
		m.lw(dst.toString(),"$s0",0);
		
		m.pop_from_stack("$s0");
	}
}
