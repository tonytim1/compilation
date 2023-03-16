package IR;

import MIPS.MIPSGenerator;
import TEMP.*;

public class IRcommand_Field_Access extends IRcommand {
    public TEMP src;
    public TEMP dst;

    public IRcommand_Field_Access(TEMP dst, TEMP src) {
        this.dst = dst;
        this.src = src;
    }

    public void MIPSme() {
        MIPSGenerator.getInstance().field_Access(dst, offset, src);
    }
}
