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

        m.funcPrologue();

		int stack_offset = this.saveToStack(this.args);

		m.jal(String.format("func_%s",name));

		m.addu("$sp","$sp",stack_offset);

        if(dst != null)
        {
            m.mov(dst.toString(),"$v0");
        }
        
        m.funcEpilogue();
    }
}
