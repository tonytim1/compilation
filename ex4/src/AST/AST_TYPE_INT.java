package AST;

import TYPES.*;

public class AST_TYPE_INT extends AST_TYPE {

  public AST_TYPE_INT(int line) {
    this.typeName = "int";
    this.line = line;
    SerialNumber = AST_Node_Serial_Number.getFresh();

    System.out.print("=============== type -> TYPE_INT \n");
  }


  

  public TYPE SemantMe() {
        return TYPE_INT.getInstance();
  }
}