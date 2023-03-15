package IR;

import TEMP.*;
import MIPS.*;

public class IRcommand_Prologue extends IRcommand {
    int localVars;

    public IRcommand_Prologue(int localVars) {
        this.localVars = localVars;
    }

    public void MIPSme() {
        MIPSGenerator.getInstance().prologue(localVars);
    }

}
