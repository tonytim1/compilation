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

public class IRcommand_Load extends IRcommand {
    TEMP dst;
    String var_name;
    String scope_type;
    int index;
    Boolean is_string;

    public IRcommand_Load(TEMP dst, String var_name, String scope_type, int index, Boolean is_string) {
        this.dst = dst;
        this.var_name = var_name;
        this.scope_type = scope_type;
        this.index = index;
        this.is_string = is_string;
    }

    /***************/
    /* MIPS me !!! */

    /***************/
    public void MIPSme() {
        if (scope_type.equals("global")) {
            if (is_string)
                MIPSGenerator.getInstance().load(dst.toString() ,var_name);
	else
                MIPSGenerator.getInstance().load_label(dst.toString(), var_name);
        } else if (scope_type.equals("param"))
            MIPSGenerator.getInstance().lw(dst.toString(), "$fp", (index + 2) * 4);
        else if (scope_type.equals("local_func"))
            MIPSGenerator.getInstance().lw(dst.toString(), "$fp", (index + 1) * -4);
        else if (scope_type.equals("local_class")) {
            // the first argument of a function is the class pointer
            MIPSGenerator.getInstance().lw(dst.toString(), "$fp", 8);
            MIPSGenerator.getInstance().lw(dst.toString(), dst.toString(), (index + 1) * 4);
        }
    }

    public TEMP_LIST getLiveTemp(TEMP_LIST input) {
        TEMP_LIST result = input.clone();
        result.remove(dst);
        return result;
    }
}
