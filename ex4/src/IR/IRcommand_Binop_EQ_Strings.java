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

public class IRcommand_Binop_EQ_Strings extends IRcommand {
    public TEMP t1;
    public TEMP t2;
    public TEMP dst;

    public IRcommand_Binop_EQ_Strings(TEMP dst, TEMP t1, TEMP t2) {
        this.dst = dst;
        this.t1 = t1;
        this.t2 = t2;
    }

    public void MIPSme() {
        /*******************************/
        /* [1] Allocate 3 fresh labels */
        /*******************************/
        String label_start = getFreshLabel("start");
        String label_end = getFreshLabel("end");
        String label_AssignOne = getFreshLabel("AssignOne");

        // save $s0-$s3 to stack
        MIPSGenerator.getInstance().push_to_stack("$s0");
        MIPSGenerator.getInstance().push_to_stack("$s1");
        MIPSGenerator.getInstance().push_to_stack("$s2");
        MIPSGenerator.getInstance().push_to_stack("$s3");

        // mov the pointer of the 2 string to a register
        MIPSGenerator.getInstance().mov("$s2", t1.toString());
        MIPSGenerator.getInstance().mov("$s3", t2.toString());

        // assume not equal at the start
        MIPSGenerator.getInstance().li(dst.toString(), 0);

        // label_start
        MIPSGenerator.getInstance().label(label_start);

        // compare char instead of byte
        MIPSGenerator.getInstance().lb("$s0", "$s2", 0);
        MIPSGenerator.getInstance().lb("$s1", "$s3", 0);

        // if not equal end
        MIPSGenerator.getInstance().bne("$s0", "$s1", label_end);

        // assign 1 if one of the string is null because they are equal at this point
        MIPSGenerator.getInstance().beqz("$s0", label_AssignOne);

        // increase the pointers by one and return to start to continue comparing
        MIPSGenerator.getInstance().addu("$s2", "$s2", 1);
        MIPSGenerator.getInstance().addu("$s3", "$s3", 1);
        MIPSGenerator.getInstance().jump(label_start);

        /************************/
        /* label_AssignOne:     */
        /*                      */
        /*         t3 := 1      */
        /*         goto end;    */
        /*                      */
        /************************/
        MIPSGenerator.getInstance().label(label_AssignOne);
        MIPSGenerator.getInstance().li(dst.toString(), 1);
        MIPSGenerator.getInstance().label(label_end);

        //clean stack
        MIPSGenerator.getInstance().pop_from_stack("$s3");
        MIPSGenerator.getInstance().pop_from_stack("$s2");
        MIPSGenerator.getInstance().pop_from_stack("$s1");
        MIPSGenerator.getInstance().pop_from_stack("$s0");
    }
}