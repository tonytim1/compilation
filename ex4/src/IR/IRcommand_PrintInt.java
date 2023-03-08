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


public class IRcommand_PrintInt extends IRcommand {
    TEMP t;

    public IRcommand_PrintInt(TEMP t) {
        this.t = t;
    }

    public void MIPSme() {
        MIPSGenerator.getInstance().print_int(t);
    }
}
