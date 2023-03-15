package AST;

import IR.IR;
import IR.IRcommand_Const_Int;
import TEMP.TEMP;
import TEMP.TEMP_FACTORY;
import TYPES.*;

public class AST_EXP_MINUS_INT extends AST_EXP {
  public int value;

  /******************/
  /* CONSTRUCTOR(S) */
  /******************/
  public AST_EXP_MINUS_INT(int value) {
    this.value = -value;
    /******************************/
    /* SET A UNIQUE SERIAL NUMBER */
    /******************************/
    SerialNumber = AST_Node_Serial_Number.getFresh();

    /*******************************/
    /* COPY INPUT DATA NENBERS ... */
    /*******************************/

    /***************************************/
    /* PRINT CORRESPONDING DERIVATION RULE */
    /***************************************/
    System.out.format("====================== exp -> -INT(%d)\n", value);

  }

  /************************************************/
  /* The printing message for an INT EXP AST node */
  /************************************************/
  

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
