package IR;


import TEMP.*;
import MIPS.*;

public class IRcommand_Store_Field extends IRcommand {
    public TEMP val;

    public IRcommand_Store_Field(TEMP value) {
        this.val = value;
    }

    public void MIPSme() {
        MIPSGenerator.getInstance().store_field(offset, val);
    }
}