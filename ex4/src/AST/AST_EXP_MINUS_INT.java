package AST;

import IR.IR;
import IR.IRcommand_Const_Int;
import TEMP.TEMP;
import TEMP.TEMP_FACTORY;
import TYPES.*;

public class AST_EXP_MINUS_INT extends AST_EXP {
  public int value;

  public AST_EXP_MINUS_INT(int value) {
    this.value = -value;
    SerialNumber = AST_Node_Serial_Number.getFresh();


    System.out.format("====================== exp -> -INT(%d)\n", value);

  }

  

  public TYPE SemantMe() {
    System.out.println("EXP MINUS INT - semant me");
    return TYPE_INT.getInstance();
  }

  public TEMP IRme() {
    System.out.println("EXP MINUS INT - IRme");

    TEMP t = TEMP_FACTORY.getInstance().getFreshTEMP();
    IR.getInstance().Add_IRcommand(new IRcommand_Const_Int(t, value));
    return t;
  }
}
