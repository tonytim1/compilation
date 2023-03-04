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
import TYPES.*;

public class IRcommand_Allocate_Class extends IRcommand
{
    TYPE_CLASS my_class;

    public IRcommand_Allocate_Class(TYPE_CLASS my_class)
    {
        this.my_class = my_class;
    }

    public void MIPSme()
	{	
        // build virtual table

        // alocate 
    }
}
