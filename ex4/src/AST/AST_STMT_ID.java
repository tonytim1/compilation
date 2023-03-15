package AST;

import TYPES.*;
import SYMBOL_TABLE.*;
import TEMP.*;
import IR.*;

public class AST_STMT_ID extends AST_STMT {
  public String id;
  public TYPE_FUNCTION func; 
  public AST_STMT_ID(String id, int line) {
    this.id = id;
    this.line = line;
    SerialNumber = AST_Node_Serial_Number.getFresh();

    System.out.print("====================== stmt -> ID();\n");
  }


  

  public TYPE SemantMe() {
    TYPE t = funcSig(id, null, this.line);

    this.func = (TYPE_FUNCTION) (SYMBOL_TABLE.getInstance().find(id));

    return t;
  }

  public TEMP IRme() {

    TEMP t = TEMP_FACTORY.getInstance().getFreshTEMP();

    String startLabel = null;
    if (id.equals("PrintInt")) {
      startLabel = "PrintInt";
    }
    else if (id.equals("PrintString"))
    {
      startLabel = "PrintString";
    }
    else
    {
      startLabel = this.func.startLabel;
    }

    IR.getInstance().Add_IRcommand(new IRcommand_Call_Func(t, startLabel, null));

    return t;
  }
}