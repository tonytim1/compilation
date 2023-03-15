package AST;

public class AST_ARG_LIST extends AST_Node {
  /****************/
  /* DATA MEMBERS */
  /****************/
  public AST_ARG head;
  public AST_ARG_LIST tail;

  /******************/
  /* CONSTRUCTOR(S) */
  /******************/
  public AST_ARG_LIST(AST_ARG head, AST_ARG_LIST tail) {
    this.head = head;
    this.tail = tail;
    /******************************/
    /* SET A UNIQUE SERIAL NUMBER */
    /******************************/
    SerialNumber = AST_Node_Serial_Number.getFresh();

    /***************************************/
    /* PRINT CORRESPONDING DERIVATION RULE */
    /***************************************/
    if (tail != null)
      System.out.print("====================== args -> arg, args\n");
    if (tail == null)
      System.out.print("====================== args -> arg      \n");

    /*******************************/
    /* COPY INPUT DATA NENBERS ... */
    /*******************************/

  }

  /******************************************************/
  /* The printing message for a statement list AST node */
  /******************************************************/
  

  public void printArgList() {
    AST_ARG_LIST data_members = this;
    for (AST_ARG_LIST it = data_members; it != null; it = it.tail) {
      System.out.print(it.head.t.typeName + ", " + it.head.id + " ");
    }
  }
}
