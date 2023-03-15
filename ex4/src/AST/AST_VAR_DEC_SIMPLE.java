package AST;

import TYPES.*;
import SYMBOL_TABLE.*;
import TEMP.*;
import IR.*;

public class AST_VAR_DEC_SIMPLE extends AST_VAR_DEC {

  public String inclass;
  public TYPE t;
  public boolean inFunc;

  public AST_VAR_DEC_SIMPLE(AST_TYPE type, String id, int line) {
    this.type = type;
    this.id = id;
    this.line = line;

    SerialNumber = AST_Node_Serial_Number.getFresh();

    if (type != null) {
      System.out.print("====================== varDec -> type ID SEMICOLON \n");
      }
  }


  

  public TYPE SemantMe() {

    TYPE t1 = findType(type.typeName);
    t = t1;
    if (t1 == null || t1 instanceof TYPE_VOID || t1 instanceof TYPE_NIL) {
            printError(line);
    }

    TYPE t2 = SYMBOL_TABLE.getInstance().findInCurrScope(id);
    if (t2 != null) {
            printError(line);
    }

    isOverride();

    SYMBOL_TABLE.getInstance().enter(id, t1);
    scope = SYMBOL_TABLE.getInstance().getScope();
    inclass = SYMBOL_TABLE.getInstance().inClassScope();
    inFunc = SYMBOL_TABLE.getInstance().inFuncScope();
    return t1;
  }

  public TEMP IRme() {

        if (scope.equals("global")) {
      if (type instanceof AST_TYPE_STRING) {
        IR.getInstance().Add_IRcommand(new IRcommand_Declare_Global_String(id, "aaa"));
      }
      else if (type instanceof AST_TYPE_INT)
      {
        IR.getInstance().Add_IRcommand(new IRcommand_Declare_Global_Int(id, 0));
      }
      else
      {         IR.getInstance().Add_IRcommand(new IRcommand_Declare_Global_Object(id));
      }
    }

        else {
      if (!inFunc && inclass != null) {
        String namec = inclass + "_" + id;
        IR.getInstance().Add_IRcommand(new IRcommand_Declare_Global_Object(namec));
      }

                }
        return null;
  }
}