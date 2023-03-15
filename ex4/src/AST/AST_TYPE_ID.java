package AST;

import SYMBOL_TABLE.*;
import TYPES.*;

public class AST_TYPE_ID extends AST_TYPE {

  /*******************/
  /* CONSTRUCTOR(S) */
  /*******************/
  public AST_TYPE_ID(String typeName, int line) {
    this.typeName = typeName;
    this.line = line;

    /******************************/
    /* SET A UNIQUE SERIAL NUMBER */
    /******************************/
    SerialNumber = AST_Node_Serial_Number.getFresh();

    /***************************************/
    /* PRINT CORRESPONDING DERIVATION RULE */
    /***************************************/
    System.out.print("====================== type -> ID \n");
  }

  /****************** outside CONSTRUCTOR code *******************/

  /*************************************************/
  /* The printing message for a XXX node */
  /*************************************************/
  

  public TYPE SemantMe() {
    System.out.println("TYPE ID - semant me");
    TYPE res = findType(typeName);
    if (res == null) {
      System.out.format(">> ERROR(%d) non existing type %s (type_id)\n", line, res);
      printError(this.line);
    }

        if (!res.name.equals(typeName)) {
      System.out.format(">> ERROR [%d]- type name isn't declared correctly! %s %s", line, res.name, typeName);
      printError(this.line);
    }

    return res;
  }

}