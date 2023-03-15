package AST;

import TYPES.*;
import IR.*;
import SYMBOL_TABLE.*;
import TEMP.*;

public class AST_NEW_EXP_TYPE extends AST_NEW_EXP {
  public AST_TYPE t;

  /*******************/
  /* CONSTRUCTOR(S) */
  /*******************/
  public AST_NEW_EXP_TYPE(AST_TYPE t, int line) {
    this.t = t;
    this.line = line;
    /******************************/
    /* SET A UNIQUE SERIAL NUMBER */
    /******************************/
    SerialNumber = AST_Node_Serial_Number.getFresh();
    /***************************************/
    /* PRINT CORRESPONDING DERIVATION RULE */
    /***************************************/
    System.out.print("====================== newExp -> NEW type:t \n");
  }

  /****************** outside CONSTRUCTOR code *******************/

  /*************************************************/
  /* The printing message for a XXX node */
  /*************************************************/
  

  public TYPE SemantMe() {
    System.out.println("NEWEXP TYPE - sament me");

    if (t.typeName.equals("void") || t.typeName.equals("nil")) {
      System.out.println(">> ERROR [" + line + "] cant declare type void/nil");
      printError(line);
    }
        if (t.typeName.equals("int")) {
      return TYPE_INT.getInstance();
    }
    if (t.typeName.equals("string")) {
      return TYPE_STRING.getInstance();
    }

    TYPE cl = SYMBOL_TABLE.getInstance().findClass(t.typeName);
    if (cl == null) {
      System.out.println(">> ERROR [" + line + "] cant declate " + t.typeName + " type");
      printError(line);
    }
    return cl;
  }

  public TEMP IRme() {
    System.out.println("NEWEXP TYPE- IRme");

    TEMP t1 = TEMP_FACTORY.getInstance().getFreshTEMP();
    IR.getInstance().Add_IRcommand(new IRcommand_New_Class_Object(t1, t.typeName));
    return t1;
  }
}