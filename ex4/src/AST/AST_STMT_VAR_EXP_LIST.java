package AST;

import TYPES.*;
import TEMP.*;

public class AST_STMT_VAR_EXP_LIST extends AST_STMT {
  public AST_VAR var;
  public String id;
  public AST_EXP_LIST list;
  public TYPE_CLASS tl;

  public AST_STMT_VAR_EXP_LIST(AST_VAR var, String id, AST_EXP_LIST list) {
    this.var = var;
    this.id = id;
    this.list = list;

    System.out.print("====================== stmt -> var.ID(expList);\n");

    SerialNumber = AST_Node_Serial_Number.getFresh();

  }


  

  public TYPE SemantMe() {
    TYPE t1 = var.SemantMe();
    if (t1 == null || !(t1 instanceof TYPE_CLASS))     {
      printError(line);
    }
    tl = (TYPE_CLASS) t1;
    TYPE t2 = isFuncOfClass(t1.name, id, list, this.line);
    if (t2 == null) {
      printError(line);
    }

    return t2;
  }

  public TEMP IRme() {
    return vardotIR(var, list, tl, id);
  }
}
