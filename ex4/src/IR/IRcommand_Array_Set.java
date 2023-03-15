package IR;

import MIPS.MIPSGenerator;
import TEMP.*;

public class IRcommand_Array_Set extends IRcommand {
  public TEMP val;
  public TEMP array;
  public TEMP index;

  public IRcommand_Array_Set(TEMP array, TEMP index, TEMP val) {
    this.array = array;
    this.index = index;
    this.val = val;

  }

  public void MIPSme() {
    MIPSGenerator.getInstance().array_set(array, index, val);
  }
}