package IR;


import TEMP.*;
import MIPS.*;

public class IRcommand_This_Dot_Field extends IRcommand {
    public TEMP dst;
    public boolean isAllocated;

    public IRcommand_This_Dot_Field(TEMP dst) {
        this.isAllocated = false;
        this.dst = dst;
    }

    public void MIPSme() {
        MIPSGenerator.getInstance().load_field_in_func(dst, offset);
    }
}