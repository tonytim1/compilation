package IR;

import MIPS.*;
import TEMP.*;

public class IRcommand_Call_Func extends IRcommand {
  public TEMP t;
  String startLabel;
  public TEMP_LIST tempList;

  public IRcommand_Call_Func(TEMP t, String startLabel, TEMP_LIST tempList) {
	    this.t = t;
	    this.startLabel = startLabel;
	    this.tempList = tempList;
  }

  public void MIPSme() {
    TEMP_LIST reversed = null;
    if (tempList != null) {
      reversed = tempList.reverseList();
    }
    MIPSGenerator.getInstance().call_func(t, startLabel, reversed);
  }
}
