package AST;

import SYMBOL_TABLE.SYMBOL_TABLE;
import TYPES.*;
import TEMP.*;
import IR.*;
import SYMBOL_TABLE.*;
import MIPS.*;

public class AST_VAR_DEC_EXP extends AST_VAR_DEC {
    public AST_EXP exp;
    String scope;
    String class_name;
    boolean inFunc;

    public AST_VAR_DEC_EXP(AST_TYPE type, String id, AST_EXP exp, int line) {
        this.type = type;
        this.id = id;
        this.exp = exp;
        this.line = line;

        SerialNumber = AST_Node_Serial_Number.getFresh();

        if (type != null && exp != null) {
            System.out.print("=============== varDec -> type ID ASSIGN exp SEMICOLON \n");
        }
    }


    public TYPE SemantMe() {
        TYPE t1;
        TYPE t2;

        if (type == null || exp == null) {
            printError(this.line);
        }
        t1 = findType(type.typeName);
        t2 = exp.SemantMe();

        if (t1 == null || !(areTypesAssinable(t1, t2)) || t1 instanceof TYPE_VOID || t1 instanceof TYPE_NIL) {
            printError(this.line);
        }
        TYPE res = SYMBOL_TABLE.getInstance().findInCurrScope(id);
        if (res != null) {
            printError(this.line);
        }

        class_name = SYMBOL_TABLE.getInstance().inClassScope();
        scope = SYMBOL_TABLE.getInstance().getScope();
        inFunc = SYMBOL_TABLE.getInstance().inFuncScope();
        if (scope.equals("class") && !(exp instanceof AST_EXP_INT ||
                exp instanceof AST_EXP_NIL ||
                exp instanceof AST_EXP_STRING ||
                exp instanceof AST_EXP_MINUS_INT)) {
            printError(line);
        }
        isOverride();
        SYMBOL_TABLE.getInstance().enter(id, t1);

        return t1;
    }

    public TEMP IRme() {
        String RealId = id;

        if (scope.equals("global") || (!inFunc && class_name != null)) {
            if (!inFunc && class_name != null)
                RealId = class_name + "_" + id;
            if (type instanceof AST_TYPE_STRING) {
                String value = ((AST_EXP_STRING) exp).s;
                IR.getInstance().Add_IRcommand(new IRcommand_Declare_Global_String(RealId, value));
                if (class_name != null)
                    defaultFields.put(class_name + "_" + id, value);
            } else if (type instanceof AST_TYPE_INT) {
                int value = ((AST_EXP_INT) exp).value;
                IR.getInstance().Add_IRcommand(new IRcommand_Declare_Global_Int(RealId, value));
                if (class_name != null)
                    defaultFields.put(class_name + "_" + id, String.valueOf(value));
            } else {
                IR.getInstance().Add_IRcommand(new IRcommand_Declare_Global_Object(RealId));
            }
        } else {
            TEMP t = exp.IRme();

            IRcommand command = new IRcommand_Store_Local(t);
            command.offset = GetOffset(id);
            IR.getInstance().Add_IRcommand(command);
        }
        return null;
    }
}