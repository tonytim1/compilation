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
        
    }
}