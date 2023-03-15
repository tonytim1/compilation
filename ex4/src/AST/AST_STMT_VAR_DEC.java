package AST;

import TYPES.*;
import TEMP.*;

public class AST_STMT_VAR_DEC extends AST_STMT {
  public AST_VAR_DEC v;

  /*******************/
  /* CONSTRUCTOR(S) */
  /*******************/
  public AST_STMT_VAR_DEC(AST_VAR_DEC v) {
    this.v = v;
    /******************************/
    /* SET A UNIQUE SERIAL NUMBER */
    /******************************/
    SerialNumber = AST_Node_Serial_Number.getFresh();

    System.out.print("====================== stmt -> varDec\n");
  }

  /****************** outside CONSTRUCTOR code *******************/

  /*************************************************/
  /* The printing message for a XXX node */
  /*************************************************/
  

  public TYPE SemantMe() {
    System.out.println("STMT VARDEC - semant me");
    v.SemantMe();
    return null;
  }

  public TEMP IRme() {
    System.out.println("STMT VARDEC - ir me");
    v.IRme();
    return null;
  }
}