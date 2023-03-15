package AST;

import TEMP.TEMP;
import TYPES.*;

public class AST_DEC_FUNC_DEC extends AST_DEC {
  public AST_FUNC_DEC func;

  /*******************/
  /* CONSTRUCTOR(S) */
  /*******************/
  public AST_DEC_FUNC_DEC(AST_FUNC_DEC func) {
    this.func = func;
    /******************************/
    /* SET A UNIQUE SERIAL NUMBER */
    /******************************/
    SerialNumber = AST_Node_Serial_Number.getFresh();

    /***************************************/
    /* PRINT CORRESPONDING DERIVATION RULE */
    /***************************************/
    if (func != null)
      System.out.print("====================== dec -> funcDec\n");
  }

  /****************** outside CONSTRUCTOR code *******************/

  /*************************************************/
  /* The printing message for a XXX node */
  /*************************************************/
  

  public TYPE SemantMe() {
    System.out.println("DEC_FUNCDEC" + "- semantme");
    func.SemantMe();
    /*********************************************************/
    /* [1] Return value is irrelevant for the program itself */
    /*********************************************************/
    return null;
  }

  public TEMP IRme() {
    System.out.println("DEC_FUNCDEC" + "- IRme");

    func.IRme();

    return null;
  }
}
