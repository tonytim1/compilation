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

public class IRcommand_ArrayNew extends IRcommand
{
	TEMP dst;
	TEMP size;

	public IRcommand_ArrayNew(TEMP dst,TEMP size)
	{
		this.dst = dst;
		this.size = size;
	}
}