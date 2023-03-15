/***********/
/* PACKAGE */
/***********/
package IR;

import MIPS.*;
/*******************/
/* PROJECT IMPORTS */
/*******************/
import TEMP.*;

public class IRcommand_Virtual_Call extends IRcommand {
  public TEMP dst;
  public TEMP classTemp;
  String funcName;
  public TEMP_LIST args;

public IRcommand_Virtual_Call(TEMP dst, TEMP classTemp, String funcName, TEMP_LIST tempList) {
    this.dst = dst;
    this.classTemp = classTemp;
    this.funcName = funcName;
    this.args = tempList;   
    
  }

  public void MIPSme() {
    System.out.println("IRcommand_Virtual_Call" + " - MIPSme");
    MIPSGenerator.getInstance().virtual_call(dst, classTemp, offset, args);
  }
}