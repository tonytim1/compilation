package IR;

import MIPS.MIPSGenerator;
import TEMP.*;
//not in liveness
public class IRcommand_Array_Set extends IRcommand {

  public TEMP array;
  public TEMP index;
  public TEMP val;

  // array_set t1, t2, t3 (array, index, val)
  public IRcommand_Array_Set(TEMP array, TEMP index, TEMP val) {
    this.array = array;
    this.index = index;
    this.val = val;

  }

  public void MIPSme() {
    System.out.println("IRcommand_Array_Set" + "- MIPSme");
    MIPSGenerator.getInstance().array_set(array, index, val);
  }
}