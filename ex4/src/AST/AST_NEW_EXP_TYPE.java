package AST;

import TYPES.*;
import IR.*;
import SYMBOL_TABLE.*;
import TEMP.*;

public class AST_NEW_EXP_TYPE extends AST_NEW_EXP {
  public AST_TYPE t;

  public AST_NEW_EXP_TYPE(AST_TYPE t, int line) {
    this.t = t;
    this.line = line;
    SerialNumber = AST_Node_Serial_Number.getFresh();
    System.out.print("====================== newExp -> NEW type:t \n");
  }


  

  public TYPE SemantMe() {

    if (t.typeName.equals("void") || t.typeName.equals("nil")) {
      printError(line);
    }
        if (t.typeName.equals("int")) {
      return TYPE_INT.getInstance();
    }
    if (t.typeName.equals("string")) {
      return TYPE_STRING.getInstance();
    }

    TYPE cl = SYMBOL_TABLE.getInstance().findClass(t.typeName);
    if (cl == null) {
      printError(line);
    }
    return cl;
  }

  public TEMP IRme() {

    TEMP t1 = TEMP_FACTORY.getInstance().getFreshTEMP();
    IR.getInstance().Add_IRcommand(new IRcommand_New_Class_Object(t1, t.typeName));
    return t1;
  }
}