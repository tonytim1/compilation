package IR;

import MIPS.MIPSGenerator;
import TEMP.*;

public class IRcommand_Field_Set extends IRcommand {
    public TEMP dst;
    public TEMP val;

    public IRcommand_Field_Set(TEMP t1, TEMP val) {
        this.dst = t1;
        this.val = val;
    }

    public void MIPSme() {
        MIPSGenerator.getInstance().field_set(dst, offset, val);
    }
}