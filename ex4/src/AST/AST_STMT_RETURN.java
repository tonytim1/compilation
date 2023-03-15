package AST;

import TYPES.*;
import SYMBOL_TABLE.*;
import IR.*;
import TEMP.*;

public class AST_STMT_RETURN extends AST_STMT {

  /*******************/
  /* CONSTRUCTOR(S) */
  /*******************/
  public AST_STMT_RETURN(int line) {
    this.line = line;
    /******************************/
    /* SET A UNIQUE SERIAL NUMBER */
    /******************************/
    SerialNumber = AST_Node_Serial_Number.getFresh();

    System.out.print("====================== stmt -> return;\n");

  }

  /****************** outside CONSTRUCTOR code *******************/

  /*************************************************/
  /* The printing message for a XXX node */
  /*************************************************/
  

  public TYPE SemantMe() {
    System.out.println("STMT RETURN - semant me");
    int a = SYMBOL_TABLE.getInstance().findFunc("void");
    if (a == 0) {
      System.out.println("=======Error in return statement!");
      printError(line);
    }
    return TYPE_VOID.getInstance();
  }

  public TEMP IRme() {
    IR.getInstance().Add_IRcommand(new IRcommand_Return(null));
    return null;
  }
}