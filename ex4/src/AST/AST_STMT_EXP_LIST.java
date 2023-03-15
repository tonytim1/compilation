package AST;

import TYPES.*;
import IR.*;
import TEMP.*;
import SYMBOL_TABLE.*;

public class AST_STMT_EXP_LIST extends AST_STMT {
  public String id;
  public AST_EXP_LIST list;
  public TYPE_FUNCTION func; // for IRme

  /*******************/
  /* CONSTRUCTOR(S) */
  /*******************/
  public AST_STMT_EXP_LIST(String id, AST_EXP_LIST list, int line) {
    this.id = id;
    this.list = list;
    this.line = line;

    /******************************/
    /* SET A UNIQUE SERIAL NUMBER */
    /******************************/
    SerialNumber = AST_Node_Serial_Number.getFresh();

    System.out.print("====================== stmt -> ID(expList);\n");
  }

  /****************** outside CONSTRUCTOR code *******************/

  /*************************************************/
  /* The printing message for a XXX node */
  /*************************************************/
  

  public TYPE SemantMe() {
    System.out.println("STMT EXPLIST - semant me");

    TYPE t = funcSig(id, list, this.line);

    this.func = (TYPE_FUNCTION) (SYMBOL_TABLE.getInstance().find(id));

    return t;
  }

  public TEMP IRme() {
    System.out.println("STMT EXPLIST - IRme");

    TEMP t = TEMP_FACTORY.getInstance().getFreshTEMP();
    TEMP_LIST resTempsList = null;

    // set resTempList
    for (AST_EXP_LIST it = list; it != null; it = it.tail) {
      TEMP curr = it.head.IRme();
      resTempsList = new TEMP_LIST(curr, resTempsList);
    }

    // reverse list
    if (resTempsList != null) {
      resTempsList = resTempsList.reverseList();
    }

    String startLabel = null;
    if (id.equals("PrintInt")) {
      startLabel = "PrintInt";
    } else if (id.equals("PrintString")) {
      startLabel = "PrintString";
    } else {
      startLabel = this.func.startLabel;
    }

    IR.getInstance().Add_IRcommand(new IRcommand_Call_Func(t, startLabel, resTempsList));

    return t;
  }
}