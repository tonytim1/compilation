package AST;

import TYPES.*;
import IR.*;
import SYMBOL_TABLE.*;
import TEMP.*;

public class AST_NEW_EXP_TYPE extends AST_NEW_EXP {
  public AST_TYPE type;

  public AST_NEW_EXP_TYPE(AST_TYPE type, int line) {
    this.type = type;
    this.line = line;
    SerialNumber = AST_Node_Serial_Number.getFresh();
    System.out.print("=============== newExp -> NEW type:t \n");
  }

  public TYPE SemantMe() {
    if (type.typeName.equals("void") || type.typeName.equals("nil")) {
      printError(line);
    }
        if (type.typeName.equals("int")) {
      return TYPE_INT.getInstance();
    }
    if (type.typeName.equals("string")) {
      return TYPE_STRING.getInstance();
    }

    TYPE cl = SYMBOL_TABLE.getInstance().findClass(type.typeName);
    if (cl == null) {
      printError(line);
    }
    return cl;
  }

  public TEMP IRme() {
    TEMP t1 = TEMP_FACTORY.getInstance().getFreshTEMP();
    IR.getInstance().Add_IRcommand(new IRcommand_New_Class_Object(t1, type.typeName));
    return t1;
  }
}