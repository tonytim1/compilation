package AST;

import SYMBOL_TABLE.*;
import TYPES.*;

public class AST_TYPE_ID extends AST_TYPE {

  public AST_TYPE_ID(String typeName, int line) {
    this.typeName = typeName;
    this.line = line;

    SerialNumber = AST_Node_Serial_Number.getFresh();

    System.out.print("=============== type -> ID \n");
  }


  

  public TYPE SemantMe() {
    TYPE res = findType(typeName);
    if (res == null) {
            printError(this.line);
    }

        if (!res.name.equals(typeName)) {
            printError(this.line);
    }

    return res;
  }

}