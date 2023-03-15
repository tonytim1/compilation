package AST;

import IR.*;
import TEMP.*;
import TYPES.*;

public class AST_NEW_EXP_TYPE_EXP extends AST_NEW_EXP {
  public AST_TYPE t;
  public AST_EXP e;

  public AST_NEW_EXP_TYPE_EXP(AST_TYPE t, AST_EXP e, int line) {
    this.t = t;
    this.e = e;
    this.line = line;
    SerialNumber = AST_Node_Serial_Number.getFresh();
    System.out.print("=============== newExp -> NEW type:t LBRACK exp:e RBRACK \n");
  }


  

  public TYPE SemantMe() {

    TYPE t1 = null;
    TYPE t2 = null;

    if (t == null || e == null || t.typeName.equals("nil") || t.typeName.equals("void")) {
      printError(this.line);
    }

    t1 = t.SemantMe();
    t2 = e.SemantMe();

    if (t1 == null || t2 == null) {
            printError(line);
    }

    if (!areTypesAssinable(t2, TYPE_INT.getInstance())) {
            printError(this.line);
    }

    if ((e instanceof AST_EXP_INT) && (((AST_EXP_INT) e).value <= 0)) {
            printError(this.line);
    }

    if (e instanceof AST_EXP_MINUS_INT) {
            printError(this.line);
    }

    return new TYPE_ARRAY(t1, t1.name + "[]");
  }

  public TEMP IRme() {
    TEMP t1 = TEMP_FACTORY.getInstance().getFreshTEMP();
    TEMP t2 = e.IRme();
    IR.getInstance().Add_IRcommand(new IRcommand_New_Array(t1, t2));

    return t1;
  }
}