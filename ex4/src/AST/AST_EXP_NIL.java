package AST;

import TYPES.*;
import IR.*;
import TEMP.*;

public class AST_EXP_NIL extends AST_EXP {
  /******************/
  /* CONSTRUCTOR(S) */
  /******************/
  public AST_EXP_NIL() {
    /******************************/
    /* SET A UNIQUE SERIAL NUMBER */
    /******************************/
    SerialNumber = AST_Node_Serial_Number.getFresh();

  }

  /****************** outside CONSTRUCTOR code *******************/

  /*************************************************/
  /* The printing message for a XXX node */
  /*************************************************/
  

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