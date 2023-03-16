package IR;

import MIPS.MIPSGenerator;
import TEMP.*;

public class IRcommand_Set_Nil extends IRcommand_Assign {

    public IRcommand_Set_Nil(TEMP t) {
        this.dst = t;
    }

    public void MIPSme() {
        MIPSGenerator.getInstance().li(dst, 0);
    }
}
