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
  public void PrintMe() {

    /*************************************/
    /* AST NODE TYPE- change XXX with this class name */
    /*************************************/
    System.out.print(String.format("AST %s NODE\n", "DEC_CLASSDEC"));

    /**************************************/
    /* RECURSIVELY PRINT non-null(!) sons (list, left and right...) */
    /**************************************/
    if (c != null)
      c.PrintMe();
    /***************************************/
    /* PRINT Node to AST GRAPHVIZ DOT file */
    /* print node name and optional string (maybe only needed in binop nodes) */
    /***************************************/
    AST_GRAPHVIZ.getInstance().logNode(SerialNumber, String.format("DEC_CLASSDEC"));

    /****************************************/
    /* PRINT Edges to AST GRAPHVIZ DOT file */
    /*
     * Print Edges to every son!
     */
    /****************************************/
    if (c != null)
      AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, c.SerialNumber);

  }
  
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
