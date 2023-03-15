package AST;

import TYPES.*;
import TEMP.*;

public class AST_DEC_ARRAY_DEC extends AST_DEC {
  public AST_ARRAY_TYPE_DEF array;

  public AST_DEC_ARRAY_DEC(AST_ARRAY_TYPE_DEF array) {
    this.array = array;
    SerialNumber = AST_Node_Serial_Number.getFresh();

    if (array != null) {
      System.out.print("=============== dec -> ATD\n");
      }
  }


  

  public TYPE SemantMe() {


    array.SemantMe();
    return null;
  }

  public TEMP IRme() {

    array.IRme();
    return null;
  }

}