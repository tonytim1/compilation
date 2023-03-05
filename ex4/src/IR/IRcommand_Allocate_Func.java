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

public class IRcommand_Allocate_Func extends IRcommand
{
    String func_name;
    String class_name;
	int num_args;

	public IRcommand_Allocate_Func(String func_name, String class_name, int num_args)
	{
        this.func_name = func_name;
        this.class_name = class_name;
		this.num_args = num_args;
	}

	public void MIPSme()
	{
        MIPSGenerator m = MIPSGenerator.getInstance();

        if(class_name != null)
        {
            m.label_text(String.format("%s_%s", class_name, func_name));
        }
        else if(func_name.equals("main"))
        {
			m.label_text("main");
		} 
        else
        {
            m.label_text(String.format("func_%s",func_name));
        }

        m.push_to_stack("$ra");
        m.push_to_stack("$fp");
        m.mov("$fp","$sp");
        
		if(num_args > 0)
        {
			m.subu("$sp","$sp", num_args * 4);
        }
	}
}
