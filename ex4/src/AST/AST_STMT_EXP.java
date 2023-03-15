package AST;

import TYPES.*;
import SYMBOL_TABLE.*;
import TEMP.*;
import IR.*;

public class AST_STMT_EXP extends AST_STMT {
  public AST_EXP e;

  /*******************/
  /* CONSTRUCTOR(S) */
  /*******************/
  public AST_STMT_EXP(AST_EXP e, int line) {
    this.e = e;
    this.line = line;
    /******************************/
    /* SET A UNIQUE SERIAL NUMBER */
    /******************************/
    SerialNumber = AST_Node_Serial_Number.getFresh();

    /***************************************/
    /* PRINT CORRESPONDING DERIVATION RULE */
    /***************************************/
    System.out.print("====================== stmt -> RETURN exp SEMICOLON	\n");
  }

  /****************** outside CONSTRUCTOR code *******************/

  /*************************************************/
  /* The printing message for a XXX node */
  /*************************************************/
  

  public TYPE SemantMe() {
    System.out.println("STMT EXP - semant me");
    TYPE ty = e.SemantMe();
    if (ty == null) {
      return null;
    }
    String returnName = ty.name;

    int a = SYMBOL_TABLE.getInstance().findFunc(returnName);
    if (a == 0) {
      System.out.println("=======Error in return statement!");
      printError(line);
    }
    return ty;
  }

  public TEMP IRme() {
    TEMP retVal = e.IRme();
    IR.getInstance().Add_IRcommand(new IRcommand_Return(retVal));
    return null;
  }
}