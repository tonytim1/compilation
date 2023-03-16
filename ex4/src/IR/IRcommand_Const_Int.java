package IR;


import TEMP.*;
import MIPS.*;

public class IRcommand_Const_Int extends IRcommand_Assign {
    public int value;

    public IRcommand_Const_Int(TEMP t, int value) {
        this.dst = t;
        this.value = value;
        UpdateIRName("IRcommand_Assign");
    }

    public void MIPSme() {
        MIPSGenerator.getInstance().li(dst, value);
    }
}
