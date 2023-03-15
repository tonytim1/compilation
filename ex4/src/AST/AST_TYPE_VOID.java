package AST;

import TYPES.*;

public class AST_TYPE_VOID extends AST_TYPE {
  public AST_TYPE_VOID(int line) {
    this.typeName = "void";
    this.line = line;

    SerialNumber = AST_Node_Serial_Number.getFresh();

    System.out.print("====================== type -> TYPE_VOID \n");
  }


  

  public TYPE SemantMe() {
    System.out.format("TYPE_VOID" + "- semant me\n");
    return TYPE_VOID.getInstance();
  }
}