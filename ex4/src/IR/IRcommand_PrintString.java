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


public class IRcommand_PrintString extends IRcommand {
    TEMP t;

    public IRcommand_PrintString(TEMP t) {
        this.t = t;
    }


    public void MIPSme() {
        MIPSGenerator.getInstance().mov("$a0", t.toString());
        MIPSGenerator.getInstance().print_string();
    }
}