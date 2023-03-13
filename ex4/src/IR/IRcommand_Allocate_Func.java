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
        System.out.format("MIPSME ----------- IRcommand_Allocate_Func ----------class name %s, func name %s \n", class_name, func_name);
        if(class_name != null)
        {
            m.label_text(String.format("%s_%s", class_name, func_name));
            System.out.println("1");
        }
        else if(func_name.equals("main"))
        {
			m.label_text("main");
             System.out.println("2");
		} 
        else
        {
            m.label_text(String.format("func_%s",func_name));
             System.out.println("3");
        }

        m.push_to_stack("$ra");
        m.push_to_stack("$fp");
        m.move("$fp","$sp");
        
		if(num_args > 0)
        {
			m.subu("$sp","$sp", num_args * 4);
        }
	}
}
