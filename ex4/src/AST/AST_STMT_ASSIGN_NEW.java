package AST;

import IR.*;
import SYMBOL_TABLE.SYMBOL_TABLE;
import TEMP.*;
import TYPES.*;

public class AST_STMT_ASSIGN_NEW extends AST_STMT {
  public AST_VAR var;
  public AST_NEW_EXP exp;
  public TYPE scope;   public String inclass; 
  public AST_STMT_ASSIGN_NEW(AST_VAR var, AST_NEW_EXP exp, int line) {
    this.var = var;
    this.exp = exp;
    this.line = line;

    SerialNumber = AST_Node_Serial_Number.getFresh();
    System.out.print("====================== stmt -> var:v ASSIGN newExp:ne SEMICOLON \n");

  }


  

  public TYPE SemantMe() {

    TYPE t1 = null;
    TYPE t2 = null;

    if (var == null || exp == null) {
      printError(this.line);
    }

    t1 = var.SemantMe();
    t2 = exp.SemantMe();

    if (t1 == null || t2 == null) {
            printError(line);
    }

    if (!(type_equals(t1, t2))) {
      System.out.format(">> ERROR [%d] type mismatch for type id = newExp; --- %s %s (stmt_newexp)\n", line, t1.name,
          t2.name);
      printError(this.line);
    }

    if (var instanceof AST_VAR_SIMPLE && SYMBOL_TABLE.getInstance().inFuncScope()) {
      scope = SYMBOL_TABLE.getInstance().findInFuncScope(((AST_VAR_SIMPLE) var).name);
    }
    inclass = SYMBOL_TABLE.getInstance().inClassScope();

    return null;
  }

  public TEMP IRme() {

    TEMP value = exp.IRme();

    if (var instanceof AST_VAR_SIMPLE) {
      String name = ((AST_VAR_SIMPLE) var).name;
            if (((AST_VAR_SIMPLE) var).inGlobal == 1)
        IR.getInstance().Add_IRcommand(new IRcommand_Store_Global(value, name));
            else {
        if (scope != null)         {
          String varName = ((AST_VAR_SIMPLE) var).name;
          IRcommand cRcommand = new IRcommand_Store_Local(varName, value);
          IR.getInstance().Add_IRcommand(cRcommand);
          cRcommand.offset = GetOffset(varName);
        }
        else if (inclass != null)
        {           String varName = inclass + "_" + ((AST_VAR_SIMPLE) var).name;
          IRcommand c = new IRcommand_Store_Field(inclass, varName, value);
          c.offset = GetOffset(varName);
          IR.getInstance().Add_IRcommand(c);

                                      }
                                      else
                                      {
        }
      }

    }
    else if (var instanceof AST_VAR_FIELD)
    {
      TEMP t1 = ((AST_VAR_FIELD) var).var.IRme();
      String f_name = ((AST_VAR_FIELD) var).fieldName;
      String c = ((AST_VAR_FIELD) var).classN;
      IRcommand r = new IRcommand_Field_Set(t1, f_name, value);
      r.offset = GetOffset(c + "_" + f_name);
      IR.getInstance().Add_IRcommand(r);

    }
    else
    { 
      AST_VAR_SUBSCRIPT subVar = (AST_VAR_SUBSCRIPT) var;
      TEMP array = subVar.var.IRme();
      TEMP index = subVar.subscript.IRme();
      IR.getInstance().Add_IRcommand(new IRcommand_Array_Set(array, index, value));
    }

    return null;
  }
}