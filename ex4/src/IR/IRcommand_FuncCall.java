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

public class IRcommand_FuncCall extends IRcommand
{
    public String name;
	public TEMP_LIST args;
	public TEMP dst;

    public IRcommand_FuncCall(TEMP dst, String name, TEMP_LIST args) {
		this.name = name;
		this.args = args;
		this.dst = dst;
	}

	public void MIPSme()
	{	
        MIPSGenerator m = MIPSGenerator.getInstance();

		// push temps to stack
        m.funcPrologue();

		// save args to stack
		int stack_offset = m.pushList(args);
		
		// jump and link to function
		m.jal(String.format("func_%s",name));

		// pop arguments from stack
		m.addu("$sp","$sp",stack_offset);

        // pop temps from stack
        m.funcEpilogue();

		// save return value
        if(dst != null)
        {
            m.mov(dst.toString(),"$v0");
        }
    }
}
