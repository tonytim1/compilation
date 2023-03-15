package AST;

import SYMBOL_TABLE.SYMBOL_TABLE;
import TYPES.*;
import TEMP.*;
import IR.*;

public class AST_VAR_DEC_NEW_EXP extends AST_VAR_DEC {
  public AST_NEW_EXP exp;
  public TYPE t; 

  public AST_VAR_DEC_NEW_EXP(AST_TYPE type, String id, AST_NEW_EXP exp, int line) {
    this.type = type;
    this.id = id;
    this.exp = exp;
    this.line = line;

    SerialNumber = AST_Node_Serial_Number.getFresh();

    if (type != null && exp != null)
      System.out.print("====================== varDec -> type ID ASSIGN newExp SEMICOLON \n");

  }


  

  public TYPE SemantMe() {
    System.out.println("VARDEC NEWEXP - semant me");

    TYPE t1 = type.SemantMe();

    TYPE t2 = exp.SemantMe();


    if (t1 == null || t2 == null || t1 instanceof TYPE_NIL || t1 instanceof TYPE_VOID) {
      System.out.format(">> ERROR [%d] non existing type %s %s (vardec_newexp)\n", line, t1, t2);
      printError(this.line);
    }

    if (!(type_equals(t1, t2))) {
      System.out.format(">> ERROR [%d] type mismatch for type id = newExp;  --- %s %s  (vardec_newexp)\n", line,
          t1.name, t2.name);
      printError(this.line);
    }

    String scope = SYMBOL_TABLE.getInstance().getScope();

    if (scope.equals("class")) {
      System.out.format(">> ERROR [%d] cant declare non primitive variable\n", line);
      printError(this.line);
    }

    TYPE res = SYMBOL_TABLE.getInstance().findInCurrScope(id);
    if (res != null) {
      System.out.format(">> ERROR [%d] %s is already declared.\n", line, id);
      printError(this.line);
    } else {
      isOverride();
      SYMBOL_TABLE.getInstance().enter(id, t1);
    }

    t = t1;

        this.scope = scope;

    return t1;
  }

  public TEMP IRme() {
    System.out.println("VARDEC NEWEXP - IRme");

    TEMP t = exp.IRme();

    if (scope.equals("global")) {
            printError(666);
    } else {
      
            IRcommand command = new IRcommand_Store_Local(id, t);

      command.offset = GetOffset(id);

      IR.getInstance().Add_IRcommand(command);
    }

    return null;
  }

}