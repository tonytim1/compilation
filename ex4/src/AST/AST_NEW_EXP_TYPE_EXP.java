package AST;

import IR.*;
import TEMP.*;
import TYPES.*;

public class AST_NEW_EXP_TYPE_EXP extends AST_NEW_EXP {
  public AST_TYPE t;
  public AST_EXP e;

  /*******************/
  /* CONSTRUCTOR(S) */
  /*******************/
  public AST_NEW_EXP_TYPE_EXP(AST_TYPE t, AST_EXP e, int line) {
    this.t = t;
    this.e = e;
    this.line = line;
    /******************************/
    /* SET A UNIQUE SERIAL NUMBER */
    /******************************/
    SerialNumber = AST_Node_Serial_Number.getFresh();
    /***************************************/
    /* PRINT CORRESPONDING DERIVATION RULE */
    /***************************************/
    System.out.print("====================== newExp -> NEW type:t LBRACK exp:e RBRACK \n");
  }

  /****************** outside CONSTRUCTOR code *******************/

  /*************************************************/
  /* The printing message for a XXX node */
  /*************************************************/
  

  public TYPE SemantMe() {
    System.out.println("VARDEC NEWEXP - semant me");

    TYPE t1 = null;
    TYPE t2 = null;

    if (t == null || e == null || t.typeName.equals("nil") || t.typeName.equals("void")) {
      System.out.println(">> ERROR [" + line + "] cant declate type void/nil");
      printError(this.line);
    }

    t1 = t.SemantMe();
    t2 = e.SemantMe();

    if (t1 == null || t2 == null) {
      System.out.format(">> ERROR [%d] non existing type %s %s\n", line, t1, t2);
      printError(line);
    }

    if (!type_equals(t2, TYPE_INT.getInstance())) {
      System.out.format(">> ERROR [%d] array subscript type is %s- new type[exp]; (newexp_exp)\n", line, t2.name);
      printError(this.line);
    }

    if ((e instanceof AST_EXP_INT) && (((AST_EXP_INT) e).value <= 0)) {
      System.out.format(">> ERROR [%d] array subscript must be positive; (newexp_exp)\n", line);
      printError(this.line);
    }

    if (e instanceof AST_EXP_MINUS_INT) {
      System.out.format(">> ERROR [%d] array subscript must be positive; (newexp_exp)\n", line);
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