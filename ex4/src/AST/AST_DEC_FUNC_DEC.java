package AST;

import TEMP.TEMP;
import TYPES.*;

public class AST_DEC_FUNC_DEC extends AST_DEC {
  public AST_FUNC_DEC func;

  public AST_DEC_FUNC_DEC(AST_FUNC_DEC func) {
    this.func = func;
    SerialNumber = AST_Node_Serial_Number.getFresh();

    if (func != null) {
      System.out.print("====================== dec -> funcDec\n");
      }
  }


  

  public TYPE SemantMe() {
    func.SemantMe();
    return null;
  }

  public TEMP IRme() {

    func.IRme();

    return null;
  }
}
