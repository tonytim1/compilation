/***********/
/* PACKAGE */
/***********/
package IR;

import MIPS.*;
/*******************/
/* PROJECT IMPORTS */
/*******************/
import TEMP.*;

public class IRcommand_Virtual_Call extends IRcommand {
    public TEMP dst;
    public TEMP classTemp;
    public TEMP_LIST args;

    public IRcommand_Virtual_Call(TEMP dst, TEMP classTemp, TEMP_LIST tempList) {
        this.dst = dst;
        this.classTemp = classTemp;
        this.args = tempList;
    }

    public void MIPSme() {
        MIPSGenerator.getInstance().virtual_call(dst, classTemp, offset, args);
    }
}