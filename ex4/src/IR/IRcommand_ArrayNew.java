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

public class IRcommand_ArrayNew extends IRcommand
{
	TEMP dst;
	TEMP size;

	public IRcommand_ArrayNew(TEMP dst,TEMP size)
	{
		this.dst = dst;
		this.size = size;
	}

	public void MIPSme()
	{
		MIPSGenerator.getInstance().addi("$a0",size.toString(),1);
		MIPSGenerator.getInstance().sll("$a0","$a0",2); // equals to multiply by 4
		MIPSGenerator.getInstance().malloc();
		MIPSGenerator.getInstance().mov(dst.toString(),"$v0");
		MIPSGenerator.getInstance().sw(size.toString(),dst.toString(),0);
	}
}