package AST;

import TYPES.*;
import SYMBOL_TABLE.*;
import TEMP.*;
import IR.*;

public class AST_STMT_EXP extends AST_STMT {
  public AST_EXP e;

  public AST_STMT_EXP(AST_EXP e, int line) {
    this.e = e;
    this.line = line;
    SerialNumber = AST_Node_Serial_Number.getFresh();

    System.out.print("=============== stmt -> RETURN exp SEMICOLON	\n");
  }


  

  public TYPE SemantMe() {
    TYPE ty = e.SemantMe();
    if (ty == null) {
      return null;
    }
    String returnName = ty.name;

    int a = SYMBOL_TABLE.getInstance().findFunc(returnName);
    if (a == 0) {
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