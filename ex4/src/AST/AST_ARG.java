package AST;

public class AST_ARG extends AST_Node {
  public AST_TYPE t;
  public String id;
  public String val; 
  public AST_ARG(AST_TYPE t, String id) {
    this.t = t;
    this.id = id;
    SerialNumber = AST_Node_Serial_Number.getFresh();

  }


  
}
