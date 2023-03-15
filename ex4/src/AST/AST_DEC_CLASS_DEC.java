package AST;
import TYPES.*;
import TEMP.*;
public class AST_DEC_CLASS_DEC extends AST_DEC {
  public AST_CLASS_DEC c;

  /*******************/
  /* CONSTRUCTOR(S) */
  /*******************/
  public AST_DEC_CLASS_DEC(AST_CLASS_DEC c) {
    this.c = c;
    /******************************/
    /* SET A UNIQUE SERIAL NUMBER */
    /******************************/
    SerialNumber = AST_Node_Serial_Number.getFresh();

    /***************************************/
    /* PRINT CORRESPONDING DERIVATION RULE */
    /***************************************/
    if (c != null)
      System.out.print("====================== dec -> classDec\n");

  }

  /****************** outside CONSTRUCTOR code *******************/

  /*************************************************/
  /* The printing message for a XXX node */
  /*************************************************/
  
  
	public TYPE SemantMe() {
    System.out.println("DEC CLASSDEC - semant me");
    return c.SemantMe();
	}

	public TEMP IRme() {
    System.out.println("DEC_CLASSDEC" + "- IRme");
    c.IRme();
    return null;
}
}
