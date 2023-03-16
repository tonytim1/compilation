package AST;

import TYPES.*;
import TEMP.*;
import IR.*;
import SYMBOL_TABLE.*;

public class AST_STMT_ASSIGN extends AST_STMT {
    public AST_VAR var;
    public AST_EXP exp;
    public TYPE scope;
    public String inclass;

    public AST_STMT_ASSIGN(AST_VAR var, AST_EXP exp, int line) {
        this.var = var;
        this.exp = exp;
        this.line = line;
        SerialNumber = AST_Node_Serial_Number.getFresh();

        System.out.print("=============== stmt -> var ASSIGN exp SEMICOLON\n");
    }

    public TYPE SemantMe() {
        TYPE t1;
        TYPE t2;

        if (var == null || exp == null) {
            printError(this.line);
        }

        t1 = var.SemantMe();
        t2 = exp.SemantMe();

        if (t1 == null || t2 == null) {
            printError(line);
        }

        if (!(areTypesAssinable(t1, t2))) {
            printError(this.line);
        }

        if (var instanceof AST_VAR_SIMPLE && SYMBOL_TABLE.getInstance().inFuncScope()) {
            scope = SYMBOL_TABLE.getInstance().findInFuncScope(((AST_VAR_SIMPLE) var).name);
        }
        inclass = SYMBOL_TABLE.getInstance().inClassScope();

        return null;
    }

    public TEMP IRme() {
        if (var instanceof AST_VAR_SIMPLE) {
            TEMP value = exp.IRme();
            ((AST_VAR_SIMPLE) var).cfgVar = true;
            String name = ((AST_VAR_SIMPLE) var).name;
            if (((AST_VAR_SIMPLE) var).inGlobal == 1)
                IR.getInstance().Add_IRcommand(new IRcommand_Store_Global(value, name));
            else {
                if (scope != null) {
                    String varName = ((AST_VAR_SIMPLE) var).name;
                    IRcommand cRcommand = new IRcommand_Store_Local(varName, value);
                    IR.getInstance().Add_IRcommand(cRcommand);
                    cRcommand.offset = GetOffset(varName);
                } else if (inclass != null) {
                    String varName = inclass + "_" + ((AST_VAR_SIMPLE) var).name;
                    IRcommand c = new IRcommand_Store_Field(inclass, varName, value);
                    c.offset = GetOffset(varName);
                    IR.getInstance().Add_IRcommand(c);
                }
            }
        } else if (var instanceof AST_VAR_FIELD) {
            TEMP t1 = ((AST_VAR_FIELD) var).var.IRme();
            String f_name = ((AST_VAR_FIELD) var).fieldName;
            String c = ((AST_VAR_FIELD) var).classN;
            TEMP value = exp.IRme();
            IRcommand r = new IRcommand_Field_Set(t1, value);
            r.offset = GetOffset(c + "_" + f_name);
            IR.getInstance().Add_IRcommand(r);
            if (((AST_VAR_FIELD) var).var instanceof AST_VAR_SIMPLE)
                ((AST_VAR_SIMPLE) ((AST_VAR_FIELD) var).var).cfgVar = true;

        } else {
            AST_VAR_SUBSCRIPT subVar = (AST_VAR_SUBSCRIPT) var;
            TEMP array = subVar.var.IRme();
            TEMP index = subVar.subscript.IRme();
            TEMP value = exp.IRme();
            IR.getInstance().Add_IRcommand(new IRcommand_Array_Set(array, index, value));
        }
        return null;
    }
}