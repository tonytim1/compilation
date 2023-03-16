package IR;


import TEMP.*;
import MIPS.*;

public class IRcommand_Store_Local extends IRcommand {
    public TEMP src;

    public IRcommand_Store_Local(TEMP src) {
        this.src = src;
    }

    public void MIPSme() {
        MIPSGenerator.getInstance().store_local(src, offset);
    }
}