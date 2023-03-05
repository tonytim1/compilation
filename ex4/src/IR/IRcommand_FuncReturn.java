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

public class IRcommand_FuncReturn extends IRcommand
{
    public TEMP value; //Return value

    public IRcommand_FuncReturn(TEMP value)
    {
        this.value = value;
    }

	public void MIPSme()
	{	
        MIPSGenerator m = MIPSGenerator.getInstance();
        // move value to $v0
		if(value != null)
        {
            m.move("$v0",value.toString());	
        }
		// pop out all local func variables from stack
		m.move("$sp","$fp");
		// pop fp from stack
		m.pop_from_stack("$fp");
		// pop return address from stack
		m.pop_from_stack("$ra");
		// jump to the return address
		m.jr("$ra");
    }
}
