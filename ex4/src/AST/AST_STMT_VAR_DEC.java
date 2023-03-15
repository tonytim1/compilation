package AST;

import TYPES.*;
import TEMP.*;

public class AST_STMT_VAR_DEC extends AST_STMT {
  public AST_VAR_DEC v;

  public AST_STMT_VAR_DEC(AST_VAR_DEC v) {
    this.v = v;
    SerialNumber = AST_Node_Serial_Number.getFresh();

    System.out.print("=============== stmt -> varDec\n");
  }


  

  public TYPE SemantMe() {
    v.SemantMe();
    return null;
  }

  public TEMP IRme() {
    v.IRme();
    return null;
  }
}