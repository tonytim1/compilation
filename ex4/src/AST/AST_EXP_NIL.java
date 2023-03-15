package AST;

import TYPES.*;
import IR.*;
import TEMP.*;

public class AST_EXP_NIL extends AST_EXP {
  public AST_EXP_NIL() {
    SerialNumber = AST_Node_Serial_Number.getFresh();

  }


  

  public TYPE SemantMe() {
    System.out.println("EXP NIL - semant me");
    return TYPE_NIL.getInstance();
  }

  public TEMP IRme() {
    System.out.println("EXP NIL - IRme");

    TEMP t = TEMP_FACTORY.getInstance().getFreshTEMP();
    IR.getInstance().Add_IRcommand(new IRcommand_Set_Nil(t));
    return t;
  }
}