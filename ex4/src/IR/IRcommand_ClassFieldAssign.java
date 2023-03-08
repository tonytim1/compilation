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

public class IRcommand_ClassFieldAssign extends IRcommand
{
	TEMP my_class;
	String field_name;
	TEMP value;
	int index;

	public IRcommand_ClassFieldAssign(TEMP my_class, String field_name, TEMP value, int index)
	{
		this.my_class = my_class;
		this.field_name = field_name;
        this.value = value;
		this.index = index;
	}
	
	/***************/
	/* MIPS me !!! */
	/***************/
	public void MIPSme()
	{
		int offset = (index+1)*4;
			
		if (my_class != null)
		{
			MIPSGenerator.getInstance().beqz(my_class.toString(), "abort_pointer");
			MIPSGenerator.getInstance().sw(value.toString(), my_class.toString(), offset);
		} 
		else
		{
			if (value != null)
			{
				MIPSGenerator.getInstance().sw(value.toString(), "$a0", offset);
			} 
			else 
			{
				MIPSGenerator.getInstance().sw("$zero", "$a0", offset);
			}
		}
	}
}
