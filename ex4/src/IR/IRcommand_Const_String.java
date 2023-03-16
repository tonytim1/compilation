package IR;


import TEMP.*;
import MIPS.*;

public class IRcommand_Const_String extends IRcommand_Assign {
    String label;
    String value;

    public IRcommand_Const_String(TEMP dst, String label, String value) {
        this.dst = dst;
        this.label = label;
        this.value = val;
        UpdateIRName("IRcommand_Assign");
    }

    public void MIPSme() {
        MIPSGenerator.getInstance().allocate_const_string(dst, label, value);
    }
}
