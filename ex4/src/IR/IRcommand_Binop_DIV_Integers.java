package IR;


import TEMP.*;
import MIPS.*;

public class IRcommand_Binop_DIV_Integers extends IRcommand_Binop {
    public IRcommand_Binop_DIV_Integers(TEMP dst, TEMP t1, TEMP t2) {
        this.dst = dst;
        this.t1 = t1;
        this.t2 = t2;
        UpdateIRName("IRcommand_Binop");
    }
    public void MIPSme() {
        MIPSGenerator.getInstance().div(dst, t1, t2);
    }
}
