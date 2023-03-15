/***********/
/* PACKAGE */
/***********/
package IR;

/*******************/
/* GENERAL IMPORTS */
/*******************/

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TEMP.*;
import MIPS.*;

public class IRcommand_Binop_Concat_Strings extends IRcommand_Binop {

 // public TEMP t1;
  //public TEMP t2;
  //public TEMP dst;

  public IRcommand_Binop_Concat_Strings(TEMP dst, TEMP t1, TEMP t2) {
    this.dst = dst;
    this.t1 = t1;
    this.t2 = t2;
    UpdateIRName("IRcommand_Binop");
  }

  /***************/
  /* MIPS me !!! */
  /***************/
  @Override
  public void MIPSme() {
	    System.out.println("IRcommand_Binop_Concat_Strings" + "- MIPSme");
	    String labelabel0 = IRcommand.getFreshLabel("count_start");
	    String labelabel1 = IRcommand.getFreshLabel("count_str1");
	    String labelabel2 = IRcommand.getFreshLabel("count_str2");
	    String label3 = IRcommand.getFreshLabel("copy_start");
	    String label4 = IRcommand.getFreshLabel("copy_str1");
	    String label5 = IRcommand.getFreshLabel("copy_str2");
	    String label6 = IRcommand.getFreshLabel("end");

	    String[] arr = { labelabel0, labelabel1, labelabel2, label3, label4, label5, label6 };
	    MIPSGenerator.getInstance().concat_string(dst, t1, t2, arr);
	  }
}
