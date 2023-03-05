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
		m.malloc(my_class.getMallocSize());

		// virtual table to class pointer
		m.push("$s0");
		if (!funcList.isEmpty())
        {
			m.la("$s0", String.format("vt_%s", my_class.name));
        }
        else
        {
			m.mov("$s0", "$zero");
        }
        m.sw("$s0","$v0",0);
		m.pop("$s0");
		m.push("$v0");
		
		// pointer to stack
		m.mov("$a0", "$v0");
		m.label(String.format("init_class_%s",this.my_class.name));
		m.push("$ra");
		if(this.my_class.father != null)
        {
			m.jal(String.format("init_class_%s",this.my_class.father.name));
		}
    }
}
