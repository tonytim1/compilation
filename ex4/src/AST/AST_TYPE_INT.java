package AST;

import TYPES.*;

public class AST_TYPE_INT extends AST_TYPE {

  /*******************/
  /* CONSTRUCTOR(S) */
  /*******************/
  public AST_TYPE_INT(int line) {
    this.typeName = "int";
    this.line = line;
    /******************************/
    /* SET A UNIQUE SERIAL NUMBER */
    /******************************/
    SerialNumber = AST_Node_Serial_Number.getFresh();

    /***************************************/
    /* PRINT CORRESPONDING DERIVATION RULE */
    /***************************************/
    System.out.print("====================== type -> TYPE_INT \n");
  }

  /****************** outside CONSTRUCTOR code *******************/

  /*************************************************/
  /* The printing message for a XXX node */
  /*************************************************/
  

  public TYPE SemantMe() {
    System.out.format("TYPE_INT" + "- semant me\n");
    return TYPE_INT.getInstance();
  }
}