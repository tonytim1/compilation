package IR;

import MIPS.MIPSGenerator;
import TEMP.TEMP;

public class IRcommand_New_Array extends IRcommand {
    public TEMP src;
    public TEMP dst;

    public IRcommand_New_Array(TEMP dst, TEMP src) {
        this.src = src;
        this.dst = dst;
    }

    public void MIPSme() {
        MIPSGenerator.getInstance().new_array(dst, src);
    }
}