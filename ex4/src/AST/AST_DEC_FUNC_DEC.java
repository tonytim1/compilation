package AST;

import TEMP.TEMP;
import TYPES.*;

public class AST_DEC_FUNC_DEC extends AST_DEC {
  public AST_FUNC_DEC func;

  public AST_DEC_FUNC_DEC(AST_FUNC_DEC func) {
    this.func = func;
    SerialNumber = AST_Node_Serial_Number.getFresh();

    if (func != null)
      System.out.print("====================== dec -> funcDec\n");
  }


  

  public TYPE SemantMe() {
    System.out.println("DEC_FUNCDEC" + "- semantme");
    func.SemantMe();
    return null;
  }

  public TEMP IRme() {
    System.out.println("DEC_FUNCDEC" + "- IRme");

    func.IRme();

    return null;
  }
}
