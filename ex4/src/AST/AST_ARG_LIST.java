package AST;

public class AST_TYPE_ID_LIST extends AST_Node {
  /****************/
  /* DATA MEMBERS */
  /****************/
  public AST_TYPE_ID head;
  public AST_TYPE_ID_LIST tail;

  /******************/
  /* CONSTRUCTOR(S) */
  /******************/
  public AST_TYPE_ID_LIST(AST_TYPE_ID head, AST_TYPE_ID_LIST tail) {
    /******************************/
    /* SET A UNIQUE SERIAL NUMBER */
    /******************************/
    SerialNumber = AST_Node_Serial_Number.getFresh();

    /***************************************/
    /* PRINT CORRESPONDING DERIVATION RULE */
    /***************************************/
    if (tail != null)
      System.out.print("====================== args -> typeId, args\n");
    if (tail == null)
      System.out.print("====================== args -> typeId      \n");

    /*******************************/
    /* COPY INPUT DATA NENBERS ... */
    /*******************************/
    this.head = head;
    this.tail = tail;
  }

  /******************************************************/
  /* The printing message for a statement list AST node */
  /******************************************************/
  public void PrintMe() {
    /**************************************/
    /* AST NODE TYPE = AST STATEMENT LIST */
    /**************************************/
    System.out.print("AST ARG_LIST NODE\n");

    /*************************************/
    /* RECURSIVELY PRINT HEAD + TAIL ... */
    /*************************************/
    if (head != null)
      head.PrintMe();
    if (tail != null)
      tail.PrintMe();

    /**********************************/
    /* PRINT to AST GRAPHVIZ DOT file */
    /**********************************/
    AST_GRAPHVIZ.getInstance().logNode(SerialNumber, "ARG_LIST");

    /****************************************/
    /* PRINT Edges to AST GRAPHVIZ DOT file */
    /****************************************/
    if (head != null)
      AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, head.SerialNumber);
    if (tail != null)
      AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, tail.SerialNumber);
  }

  public void printArgList() {
    AST_TYPE_ID_LIST data_members = this;
    for (AST_TYPE_ID_LIST it = data_members; it != null; it = it.tail) {
      System.out.print(it.head.t.typeName + ", " + it.head.id + " ");
    }
  }
}
