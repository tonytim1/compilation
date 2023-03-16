package AST;

import TYPES.*;
import TEMP.*;

public class AST_STMT_VAR_DEC extends AST_STMT {
  public AST_VAR_DEC varDec;

  public AST_STMT_VAR_DEC(AST_VAR_DEC varDec) {
    this.varDec = varDec;
    SerialNumber = AST_Node_Serial_Number.getFresh();

    System.out.print("=============== stmt -> varDec\n");
  }

  public TYPE SemantMe() {
    varDec.SemantMe();
    return null;
  }

  public TEMP IRme() {
    varDec.IRme();
    return null;
  }
}