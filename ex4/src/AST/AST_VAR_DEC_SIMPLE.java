package AST;

import TYPES.*;
import SYMBOL_TABLE.*;
import TEMP.*;
import IR.*;

public class AST_VAR_DEC_SIMPLE extends AST_VAR_DEC {

    public String isInClass;
    public boolean isInFunc;

    public AST_VAR_DEC_SIMPLE(AST_TYPE type, String id, int line) {
        this.type = type;
        this.id = id;
        this.line = line;

        SerialNumber = AST_Node_Serial_Number.getFresh();

        if (type != null) {
            System.out.print("=============== varDec -> type ID SEMICOLON \n");
        }
    }


    public TYPE SemantMe() {
        SYMBOL_TABLE table = SYMBOL_TABLE.getInstance();
        TYPE t1 = findType(type.typeName);
        if (t1 == null || t1 instanceof TYPE_VOID || t1 instanceof TYPE_NIL) {
            printError(line);
        }

        TYPE t2 = table.findInCurrScope(id);
        if (t2 != null) {
            printError(line);
        }

        isOverride();

        table.enter(id, t1);
        scope = table.getScope();
        isInClass = table.inClassScope();
        isInFunc = table.inFuncScope();
        return t1;
    }

    public TEMP IRme() {
        if (scope.equals("global")) {
            if (type instanceof AST_TYPE_STRING) {
                IR.getInstance().Add_IRcommand(new IRcommand_Declare_Global_String(id, "temp"));
            } else if (type instanceof AST_TYPE_INT) {
                IR.getInstance().Add_IRcommand(new IRcommand_Declare_Global_Int(id, 0));
            } else {
                IR.getInstance().Add_IRcommand(new IRcommand_Declare_Global_Object(id));
            }
        } else {
            if (!isInFunc && isInClass != null) {
                String namec = isInClass + "_" + id;
                IR.getInstance().Add_IRcommand(new IRcommand_Declare_Global_Object(namec));
            }
        }
        return null;
    }
}