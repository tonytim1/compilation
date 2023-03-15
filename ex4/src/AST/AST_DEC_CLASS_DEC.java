package AST;
import TYPES.*;
import TEMP.*;
public class AST_DEC_CLASS_DEC extends AST_DEC {
  public AST_CLASS_DEC c;

  public AST_DEC_CLASS_DEC(AST_CLASS_DEC c) {
    this.c = c;
    SerialNumber = AST_Node_Serial_Number.getFresh();

    if (c != null) {
      System.out.print("=============== dec -> classDec\n");
      }

  }


  
  
	public TYPE SemantMe() {
    return c.SemantMe();
	}

	public TEMP IRme() {
    c.IRme();
    return null;
}
}
