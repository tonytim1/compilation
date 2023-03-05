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

public class IRcommand_Binop_Mul_Integers extends IRcommand {
    public TEMP t1;
    public TEMP t2;
    public TEMP dst;

    public IRcommand_Binop_Mul_Integers(TEMP dst, TEMP t1, TEMP t2) {
        this.dst = dst;
        this.t1 = t1;
        this.t2 = t2;
    }

    public void MIPSme() {
        int maxInt = 32767;
        int minInt = -32768;
        String maxIntLabel = getFreshLabel("max_int_label");
        String minIntLabel = getFreshLabel("min_int_label")

        MIPSGenerator.getInstance().push_to_stack("$s0");
        MIPSGenerator.getInstance().mul(dst.toString(), t1.toString(), t2.toString());

        // if the mul product greater than max assign max to dst
        MIPSGenerator.getInstance().li("$s0", maxInt);
        MIPSGenerator.getInstance().ble(dst.toString(), "$s0", maxIntLabel);
        MIPSGenerator.getInstance().mov(dst.toString(), "$s0");
        MIPSGenerator.getInstance().jump(minIntLabel);
        MIPSGenerator.getInstance().label(maxIntLabel);

        // if the mul product less than min assign min to dst
        MIPSGenerator.getInstance().li("$s0", minInt);
        MIPSGenerator.getInstance().bge(dst.toString(), "$s0", minIntLabel);
        MIPSGenerator.getInstance().mov(dst.toString(), "$s0");
        MIPSGenerator.getInstance().label(minIntLabel);
        MIPSGenerator.getInstance().popStackTo("$s0");
    }
}
