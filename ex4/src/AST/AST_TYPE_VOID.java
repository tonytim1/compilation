package AST;

import TYPES.*;

public class AST_TYPE_VOID extends AST_TYPE {
  /*******************/
  /* CONSTRUCTOR(S) */
  /*******************/
  public AST_TYPE_VOID(int line) {
    this.typeName = "void";
    this.line = line;

    /******************************/
    /* SET A UNIQUE SERIAL NUMBER */
    /******************************/
    SerialNumber = AST_Node_Serial_Number.getFresh();

    /***************************************/
    /* PRINT CORRESPONDING DERIVATION RULE */
    /***************************************/
    System.out.print("====================== type -> TYPE_VOID \n");
  }

  /****************** outside CONSTRUCTOR code *******************/

  /*************************************************/
  /* The printing message for a XXX node */
  /*************************************************/
  

  public TYPE SemantMe() {
    System.out.format("TYPE_VOID" + "- semant me\n");
    return TYPE_VOID.getInstance();
  }
}