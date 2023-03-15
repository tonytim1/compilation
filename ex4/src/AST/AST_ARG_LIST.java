package AST;

public class AST_ARG_LIST extends AST_Node {
  public AST_ARG head;
  public AST_ARG_LIST tail;

  public AST_ARG_LIST(AST_ARG head, AST_ARG_LIST tail) {
    this.head = head;
    this.tail = tail;
    SerialNumber = AST_Node_Serial_Number.getFresh();

    if (tail != null) {
      System.out.print("====================== args -> arg, args\n");
      }
    if (tail == null) {
      System.out.print("====================== args -> arg      \n");
      }


  }

  

  public void printArgList() {
    AST_ARG_LIST data_members = this;
    for (AST_ARG_LIST it = data_members; it != null; it = it.tail) {
      System.out.print(it.head.t.typeName + ", " + it.head.id + " ");
    }
  }
}
