package AST;

import TYPES.*;
import TEMP.*;

public class AST_STMT_VAR_EXP_LIST extends AST_STMT {
  public AST_VAR var;
  public String id;
  public AST_EXP_LIST list;
  public TYPE_CLASS tl;

  /*******************/
  /* CONSTRUCTOR(S) */
  /*******************/
  public AST_STMT_VAR_EXP_LIST(AST_VAR var, String id, AST_EXP_LIST list) {
    this.var = var;
    this.id = id;
    this.list = list;

    /***************************************/
    /* PRINT CORRESPONDING DERIVATION RULE */
    /***************************************/
    System.out.print("====================== stmt -> var.ID(expList);\n");

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
    System.out.println("STMT VARDOT EXPLIST - semant me not completed!");
    TYPE t1 = var.SemantMe();
    if (t1 == null || !(t1 instanceof TYPE_CLASS)) // not a class
    {
      System.out.println(">> ERROR [" + line + "] var.dot is of wrong class");
      printError(line);
    }
    tl = (TYPE_CLASS) t1;
    TYPE t2 = isFuncOfClass(t1.name, id, list, this.line);
    if (t2 == null) {
      System.out.println(">> ERROR [" + line + "] var.dot is wrong");
      printError(line);
    }

    return t2;
  }

  public TEMP IRme() {
    System.out.println("EXP VARDOT - IRme");
    return vardotIR(var, list, tl, id);
  }
}
