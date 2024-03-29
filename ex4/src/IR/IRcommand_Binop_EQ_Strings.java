package IR;


import TEMP.*;
import MIPS.*;

public class IRcommand_Binop_EQ_Strings extends IRcommand_Binop {
    public IRcommand_Binop_EQ_Strings(TEMP dst, TEMP t1, TEMP t2) {
        this.dst = dst;
        this.t1 = t1;
        this.t2 = t2;
        UpdateIRName("IRcommand_Binop");
    }

    public void MIPSme() {
        String label0 = IRcommand.getFreshLabel("loop");
        String label1 = IRcommand.getFreshLabel("not_equals");
        String label2 = IRcommand.getFreshLabel("end");
        String[] labels = {label0, label1, label2};

        MIPSGenerator.getInstance().eq_strings(dst, t1, t2, labels);
    }
}
