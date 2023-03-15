package IR;

import TEMP.*;
import MIPS.*;

public class IRcommand_Epilogue extends IRcommand {
    public IRcommand_Epilogue() {
    }

    public void MIPSme() {
        MIPSGenerator.getInstance().epilogue();
    }
}
