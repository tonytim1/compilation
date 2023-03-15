package AST;

import IR.IR;
import IR.IRcommand_Const_Int;
import IR.IRcommand_Call_Func;
import SYMBOL_TABLE.SYMBOL_TABLE;
import TEMP.*;
import TYPES.*;

public class AST_EXP_ID extends AST_EXP {
  public String id;
  public TYPE_FUNCTION func; 
  /******************/
  /* CONSTRUCTOR(S) */
  /******************/
  public AST_EXP_ID(String id, int line) {
    this.id = id;
    this.line = line;
    /******************************/
    /* SET A UNIQUE SERIAL NUMBER */
    /******************************/
    SerialNumber = AST_Node_Serial_Number.getFresh();

    /***************************************/
    /* PRINT CORRESPONDING DERIVATION RULE */
    /***************************************/
    System.out.print("====================== exp -> id()\n");

    /*******************************/
    /* COPY INPUT DATA NENBERS ... */
    /*******************************/
  }

  /*************************************************/
  /* The printing message for a XXX node */
  /*************************************************/
  

  public TYPE SemantMe() {
    System.out.println("EXP ID - semant me");
    TYPE t = funcSig(id, null, this.line);

    this.func = (TYPE_FUNCTION) (SYMBOL_TABLE.getInstance().find(id));

    return t;
  }

  public TEMP IRme() {
    System.out.println("EXP ID - IRme");

    TEMP t = TEMP_FACTORY.getInstance().getFreshTEMP();

    String startLabel = null;
    if (id.equals("PrintInt")) {
      startLabel = "PrintInt";
    } else if (id.equals("PrintString")) {
      startLabel = "PrintString";
    } else {
      startLabel = this.func.startLabel;
    }

    IR.getInstance().Add_IRcommand(new IRcommand_Call_Func(t, startLabel, null));
    return t;
  }
}