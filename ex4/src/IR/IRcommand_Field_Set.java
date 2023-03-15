package IR;

import MIPS.MIPSGenerator;
import TEMP.*;

public class IRcommand_Field_Set extends IRcommand {

  public TEMP dst;
  String varn;
  public TEMP val;

      public IRcommand_Field_Set(TEMP t1, String varName, TEMP val) {
    this.dst = t1;
    this.val = val;
    this.varn = varName;
  }

  public void MIPSme() {
        MIPSGenerator.getInstance().field_set(dst,offset,val);
  }
}