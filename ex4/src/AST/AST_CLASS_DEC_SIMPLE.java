package AST;

import TYPES.*;
import SYMBOL_TABLE.*;
import TEMP.*;
import IR.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class AST_CLASS_DEC_SIMPLE extends AST_CLASS_DEC {

  /*******************/
  /* CONSTRUCTOR(S) */
  /*******************/
  public AST_CLASS_DEC_SIMPLE(String id, AST_C_FIELD_LIST data_members, int line) {
    this.id = id;
    this.father = null;
    this.data_members = data_members;
    this.line = line;

    /******************************/
    /* SET A UNIQUE SERIAL NUMBER */
    /******************************/
    SerialNumber = AST_Node_Serial_Number.getFresh();

    /***************************************/
    /* PRINT CORRESPONDING DERIVATION RULE */
    /***************************************/
    if (data_members != null)
      System.out.print("====================== classDec -> CLASS ID: LBRACE cFieldList RBRACE\n");
  }

  /****************** outside CONSTRUCTOR code *******************/

  /*************************************************/
  /* The printing message for a XXX node */
  /*************************************************/
  

  public TYPE SemantMe() {
    System.out.format("CLASSDEC SIMPLE(%s) - semant me\n", id);
    /*************************/
    /* [1] Begin Class Scope */
    /*************************/
    TYPE isExist = SYMBOL_TABLE.getInstance().findInCurrScope(id);
    if (isExist != null) { // already has a varible with the same name
      System.out.format(">> ERROR [%d] already exist a variable with the (class) name " + id + " in the same scope",
          line);
      printError(line);
    }
    SYMBOL_TABLE.getInstance().beginScope("class-" + id);

    /***************************/
    /* [2] Semant Data Members */
    /***************************/
    AST_ARG_LIST fields = null;
    AST_TYPE_NAME_LIST funcs = null;
    TYPE t = null;

    for (AST_C_FIELD_LIST it = data_members; it != null; it = it.tail) {
      t = it.head.SemantMe(); // enter garbage to the stack
      AST_TYPE currType = null;

      if (it.head instanceof AST_C_FIELD_VAR_DEC) {
        switch (t.name) {
          case "int": {
            currType = new AST_TYPE_INT(line);
            break;
          }
          case "string": {
            currType = new AST_TYPE_STRING(line);
            break;
          }
          case "void": {
            System.out.println(">> ERROR [" + line + "] void variable is illegal");
            printError(line);
          }
          default: {
            currType = new AST_TYPE_ID(t.name, line);
            break;
          }
        }
        AST_ARG curr = new AST_ARG(currType, ((AST_C_FIELD_VAR_DEC) it.head).vd.id);
        fields = new AST_ARG_LIST(curr, fields);
      }

      if (it.head instanceof AST_C_FIELD_FUNC_DEC) {
        AST_TYPE_NAME curr = new AST_TYPE_NAME(t, ((AST_C_FIELD_FUNC_DEC) it.head).func.id);
        funcs = new AST_TYPE_NAME_LIST(curr, funcs);
      }
    }

    /*****************/
    /* [3] End Scope */
    /*****************/
    SYMBOL_TABLE.getInstance().cleanGarbage();
    TYPE_CLASS classType = new TYPE_CLASS(father, id, fields, funcs);
    SYMBOL_TABLE.getInstance().enter(id, classType);
    SYMBOL_TABLE.getInstance().beginScope("class-" + id);

    for (AST_C_FIELD_LIST it = data_members; it != null; it = it.tail) {
      t = it.head.SemantMe(); // enter real values to the stack
    }

    SYMBOL_TABLE.getInstance().endScope();

    /************************************************/
    /* [4] Enter the Class Type to the Symbol Table */
    /************************************************/

    // TYPE_CLASS classType = new TYPE_CLASS(null, id, fields, funcs);
    // classType.data_members.printArgList();
    System.out.println("------");
    // SYMBOL_TABLE.getInstance().enter(id, classType);

    /*********************************************************/
    /* [5] Return value is irrelevant for class declarations */
    /*********************************************************/
    return null;
  }

  public TEMP IRme() {
    ArrayList<ArrayList<String>> funclist = new ArrayList<ArrayList<String>>();
    Map<String, Integer> funcOff = new HashMap<>();
    int fieldCnt = 0;
    int funcCnt = 0;
    for (AST_C_FIELD_LIST it = data_members; it != null; it = it.tail) {
      AST_C_FIELD field = (AST_C_FIELD) (it.head);
      if (field instanceof AST_C_FIELD_VAR_DEC) { // field
        fieldCnt += 1;
        continue;
      }
      if (field instanceof AST_C_FIELD_FUNC_DEC) { // func
                                                 // funcCnt += 1;
        AST_C_FIELD_FUNC_DEC a = (AST_C_FIELD_FUNC_DEC) field;
        AST_FUNC_DEC b = (AST_FUNC_DEC) (a.func);
        offsets.put(id + "_" + b.id, id + "_" + b.id);
      }

      AST_C_FIELD_FUNC_DEC a = (AST_C_FIELD_FUNC_DEC) field;
      AST_FUNC_DEC b = (AST_FUNC_DEC) (a.func);

      ArrayList<String> function = new ArrayList<String>();
      function.add(b.id);
      function.add(id);
      funclist.add(function);
      funcOff.put(b.id, funcCnt * 4);
      funcCnt += 1;
    }
    classFuncsOff.put(id, funcOff);

    ArrayList<ArrayList<ArrayList<String>>> fields = new ArrayList<ArrayList<ArrayList<String>>>();

    fieldCnt = 0;
    ArrayList<String> fieldslist = new ArrayList<>();
    for (AST_C_FIELD_LIST it = data_members; it != null; it = it.tail) {
      AST_C_FIELD field = (AST_C_FIELD) (it.head);

      if (field instanceof AST_C_FIELD_VAR_DEC) { // field
                                                // fieldCnt += 1;
        AST_C_FIELD_VAR_DEC var = (AST_C_FIELD_VAR_DEC) field;
        AST_VAR_DEC b = (AST_VAR_DEC) (var.vd);
        String off = String.valueOf(fieldCnt * 4 + 4);
        offsets.put(id + "_" + b.id, off);
        fieldslist.add(b.id);
        fieldCnt += 1;

        ArrayList<String> field1 = new ArrayList<String>();
        field1.add(b.id);
        if (b.type instanceof AST_TYPE_STRING)
          field1.add("1");
        else
          field1.add("0");
        ArrayList<ArrayList<String>> fieldandclass = new ArrayList<ArrayList<String>>();
        fieldandclass.add(field1);
        ArrayList<String> classname = new ArrayList<String>();
        classname.add(id);
        fieldandclass.add(classname);
        fields.add(fieldandclass);
      }
    }
    // IR.getInstance().Add_IRcommand(new IRcommand_Declare_Class(id, funclist,
    // fields));
    for (AST_C_FIELD_LIST it = data_members; it != null; it = it.tail) {
      if (it.head instanceof AST_C_FIELD_FUNC_DEC)
        it.head.IRme();
    }
    classSize.put(id, fields.size() * 4 + 4);
    IR.getInstance().Add_IRcommand(new IRcommand_Declare_Class(id, funclist, fields));
    for (AST_C_FIELD_LIST it = data_members; it != null; it = it.tail) {
      if (it.head instanceof AST_C_FIELD_VAR_DEC)
        it.head.IRme();
    }
    classfields.put(id, fieldslist);
    return null;
  }
}