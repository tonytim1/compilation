package AST;

import TEMP.TEMP_LIST;
import TYPES.TYPE_LIST;

public class AST_EXP_LIST extends AST_Node {
  public AST_EXP head;
  public AST_EXP_LIST tail;

  public AST_EXP_LIST(AST_EXP head, AST_EXP_LIST tail, int line) {
    this.head = head;
    this.tail = tail;
    this.line = line;
    SerialNumber = AST_Node_Serial_Number.getFresh();

    if (tail != null) {
      System.out.print("====================== explis -> exp, exps\n");
      }
    if (tail == null) {
      System.out.print("====================== explist -> exp      \n");
      }

  }

  

  public TYPE_LIST SemantMe(int ignore) {
    System.out.println("EXPLIST - semant me");

    if (tail == null) {
      return new TYPE_LIST(head.SemantMe(), null);
    }
    else
    {
      return new TYPE_LIST(head.SemantMe(), tail.SemantMe(0));
    }
  }

  public TEMP_LIST IRme(int ignore) {
    System.out.println("EXPLIST - IRme");
    if ((head == null) && (tail == null)) {
      return null;
    }
    else if (tail == null)
    {
      return new TEMP_LIST(head.IRme(), null);
    }
    else
    {
      return new TEMP_LIST(head.IRme(), tail.IRme(0));
    }
  }
}
