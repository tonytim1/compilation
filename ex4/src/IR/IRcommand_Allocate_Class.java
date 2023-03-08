/***********/
/* PACKAGE */
/***********/
package IR;
import TYPES.*;
import TEMP.*;
import MIPS.*;
import REGALLOC.*;
import java.util.*;

/*******************/
/* GENERAL IMPORTS */
/*******************/

/*******************/
/* PROJECT IMPORTS */
/*******************/

public class IRcommand_Allocate_Class extends IRcommand
{
    TYPE_CLASS my_class;

    public IRcommand_Allocate_Class(TYPE_CLASS my_class)
    {
        this.my_class = my_class;
    }

    public void MIPSme()
	{	
		MIPSGenerator m = MIPSGenerator.getInstance();
        // virtual table
		List<String> funcList = my_class.getFuncList();
        if (!funcList.isEmpty())
        {
			m.label_data(String.format("vt_%s", my_class.name));
			for(String func : funcList)
            {
				String class_name_of_func = my_class.getClassNameOfFunc(func);
				m.allocate_func(String.format("%s_%s", class_name_of_func, func));
			}
		}

		// class allocation function
		m.label_text(String.format("class_%s", my_class.name));
		m.malloc_num_bytes(my_class.getMallocSize());

		// virtual table to class pointer
		m.push_to_stack("$s0");
		if (!funcList.isEmpty())
        {
			m.la("$s0", String.format("vt_%s", my_class.name));
        }
        else
        {
			m.move("$s0", "$zero");
        }
        m.sw("$s0","$v0",0);
		m.pop_from_stack("$s0");
		m.push_to_stack("$v0");
		
		// pointer to stack
		m.move("$a0", "$v0");
		m.label(String.format("init_class_%s",this.my_class.name));
		m.push_to_stack("$ra");
		if(this.my_class.father != null)
        {
			m.jal(String.format("init_class_%s",this.my_class.father.name));
		}
    }
}
