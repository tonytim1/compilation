package IR;

import MIPS.MIPSGenerator;
import TEMP.*;

public class IRcommand_Array_Set extends IRcommand {
    public TEMP value;
    public TEMP array;
    public TEMP index;

    public IRcommand_Array_Set(TEMP array, TEMP index, TEMP value) {
        this.array = array;
        this.index = index;
        this.value = value;

    }

    public void MIPSme() {
        MIPSGenerator.getInstance().array_set(array, index, value);
    }
}