package AST;

import TYPES.*;
import TEMP.*;

public class AST_DEC_ARRAY_DEC extends AST_DEC {
  public AST_ARRAY_TYPE_DEF array;

  /*******************/
  /* CONSTRUCTOR(S) */
  /*******************/
  public AST_DEC_ARRAY_DEC(AST_ARRAY_TYPE_DEF array) {
    this.array = array;
    /******************************/
    /* SET A UNIQUE SERIAL NUMBER */
    /******************************/
    SerialNumber = AST_Node_Serial_Number.getFresh();

    /***************************************/
    /* PRINT CORRESPONDING DERIVATION RULE */
    /***************************************/
    if (array != null)
      System.out.print("====================== dec -> ATD\n");
  }

  /****************** outside CONSTRUCTOR code *******************/

  /*************************************************/
  /* The printing message for a XXX node */
  /*************************************************/
  

  public TYPE SemantMe() {

    System.out.println("DEC_ATD" + "- semantme");

    array.SemantMe();
    return null;
  }

  public TEMP IRme() {
    System.out.println("DEC_ATD" + "- IRme");

    array.IRme();
    return null;
  }

}