package IR;


import TEMP.*;
import MIPS.*;

public class IRcommand_Load_Local extends IRcommand {
    public TEMP dst;
    String var;

    public IRcommand_Load_Local(String var, TEMP dst) {
        this.dst = dst;
        this.var = var;
    }

    public void MIPSme() {
        MIPSGenerator.getInstance().load_local(dst, offset);
    }
}