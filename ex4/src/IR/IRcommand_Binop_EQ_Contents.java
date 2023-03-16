package IR;


import TEMP.*;
import MIPS.*;

public class IRcommand_Binop_EQ_Contents extends IRcommand_Binop {
    public IRcommand_Binop_EQ_Contents(TEMP dst, TEMP t1, TEMP t2) {
        this.dst = dst;
        this.t1 = t1;
        this.t2 = t2;
        UpdateIRName("IRcommand_Binop");
    }

    public void MIPSme() {

        String label_end = getFreshLabel("end");
        String label_assign_one = getFreshLabel("assign_one");
        String label_assign_zero = getFreshLabel("assign_zero");

        MIPSGenerator generator = MIPSGenerator.getInstance();
        generator.beq(t1, t2, label_assign_one);
        generator.bne(t1, t2, label_assign_zero);
        generator.label(label_assign_one);
        generator.li(dst, 1);
        generator.jump(label_end);
        generator.label(label_assign_zero);
        generator.li(dst, 0);
        generator.jump(label_end);
        generator.label(label_end);
    }
}
