package AST;

public class AST_ARG_LIST extends AST_Node {
  public AST_ARG head;
  public AST_ARG_LIST tail;

  public AST_ARG_LIST(AST_ARG head, AST_ARG_LIST tail) {
    this.head = head;
    this.tail = tail;
    SerialNumber = AST_Node_Serial_Number.getFresh();

    if (tail != null) {
      System.out.print("=============== args -> arg, args\n");
      }
    if (tail == null) {
      System.out.print("=============== args -> arg      \n");
      }


  }

}
