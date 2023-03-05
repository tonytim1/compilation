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

public class IRcommand_Binop_Div_Integers extends IRcommand {
    public TEMP t1;
    public TEMP t2;
    public TEMP dst;

    public IRcommand_Binop_Div_Integers(TEMP dst, TEMP t1, TEMP t2) {
        this.dst = dst;
        this.t1 = t1;
        this.t2 = t2;
    }

    public void MIPSme() {
        int maxInt = 32767;
        int minInt = -32768;
        String abortLabel = getFreshLabel("abort_label");
        String endLabel = getFreshLabel("end_label");
        String maxIntLabel = getFreshLabel("max_int_label");
        String minIntLabel = getFreshLabel("min_int_label");

        MIPSGenerator.getInstance().push_to_stack("$s0");

        // if t2 equal to zero print message and exit
        MIPSGenerator.getInstance().beqz(t2.toString(), abortLabel);
        MIPSGenerator.getInstance().div(dst.toString(), t1.toString(), t2.toString());

        // if the division product greater than maxInt assign maxInt to dst
        MIPSGenerator.getInstance().li("$s0", maxInt);
        MIPSGenerator.getInstance().ble(dst.toString(), "$s0", maxIntLabel);
        MIPSGenerator.getInstance().mov(dst.toString(), "$s0");
        MIPSGenerator.getInstance().jump(minIntLabel);
        MIPSGenerator.getInstance().label(maxIntLabel);

        // if the division product less than min assign min to dst
        MIPSGenerator.getInstance().li("$s0", minInt);
        MIPSGenerator.getInstance().bge(dst.toString(), "$s0", minIntLabel);
        MIPSGenerator.getInstance().mov(dst.toString(), "$s0");
        MIPSGenerator.getInstance().label(minIntLabel);
        MIPSGenerator.getInstance().popStackTo("$s0");

        // abort function
        MIPSGenerator.getInstance().jump(endLabel);
        MIPSGenerator.getInstance().label(abortLabel);
        MIPSGenerator.getInstance().la("$a0", "string_illegal_div_by_0");
        MIPSGenerator.getInstance().print_string();
        MIPSGenerator.getInstance().exit();
        MIPSGenerator.getInstance().label(endLabel);
    }
}