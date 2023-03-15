package IR;


import TEMP.*;
import MIPS.*;

public class IRcommand_Binop_Concat_Strings extends IRcommand_Binop {

     
  public IRcommand_Binop_Concat_Strings(TEMP dst, TEMP t1, TEMP t2) {
    this.dst = dst;
    this.t1 = t1;
    this.t2 = t2;
    UpdateIRName("IRcommand_Binop");
  }

  @Override
  public void MIPSme() {
	    String label0 = IRcommand.getFreshLabel("count_start");
	    String label1 = IRcommand.getFreshLabel("count_string_number1");
	    String label2 = IRcommand.getFreshLabel("count_string_number2");
	    String label3 = IRcommand.getFreshLabel("copy_start");
	    String label4 = IRcommand.getFreshLabel("copy_string_number1");
	    String label5 = IRcommand.getFreshLabel("copy_string_number2");
	    String label6 = IRcommand.getFreshLabel("end");

	    String[] arr = { label0, label1, label2, label3, label4, label5, label6 };
	    MIPSGenerator.getInstance().concat_string(dst, t1, t2, arr);
	  }
}
