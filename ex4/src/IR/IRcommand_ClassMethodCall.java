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

public class IRcommand_ClassMethodCall extends IRcommand
{
	TEMP dst;
	TEMP my_class;
        String method_name;
        TEMP_LIST args;
	int index;
	
	public IRcommand_ClassMethodCall(TEMP dst, TEMP my_class, String name, TEMP_LIST args, int index)
	{
		this.dst = dst;
		this.my_class = my_class;
        this.method_name = name;
        this.args = args;
		this.index = index;
	}

	/***************/
	/* MIPS me !!! */
	/***************/
	public void MIPSme()
	{
        MIPSGenerator m = MIPSGenerator.getInstance();

		m.beqz(my_class.toString(), "abort_pointer");

		// get function address from class pointer
		m.push_to_stack("$s0");
		m.lw("$s0", my_class.toString(), 0);
		m.lw("$s0", "$s0", index*4);

		// push temps to stack
		m.funcPrologue();

		// save args to stack
		int stack_offset = m.pushList(this.args);

		// save class pointer to stack
		m.push_to_stack(my_class.toString());
		stack_offset += 4;

		m.jalr("$s0");

        // pop arguments from stack
		m.addu("$sp","$sp",stack_offset);

		// pop temps from stack
		m.funcEpilogue();

		m.pop_from_stack("$s0");

        // save return value
		if(dst != null){
			m.mov(dst.toString(),"$v0");
		}
	}
}
