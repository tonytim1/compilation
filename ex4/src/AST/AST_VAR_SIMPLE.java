package AST;

import TYPES.*;
import SYMBOL_TABLE.*;
import TEMP.*;
import IR.*;

public class AST_VAR_SIMPLE extends AST_VAR {
    public String name;
    public String className = null;
    public TYPE t;
    public boolean cfgVar = false;
    int inGlobal = 0;

    public AST_VAR_SIMPLE(String name, int line) {
        this.name = name;
        this.line = line;

        SerialNumber = AST_Node_Serial_Number.getFresh();

        System.out.format("=============== var -> ID\n");
    }

    public TYPE SemantMe() {
        TYPE res = SYMBOL_TABLE.getInstance().findInCurrScope(name);
        if (res != null && SYMBOL_TABLE.getInstance().getScope().equals("global"))
            inGlobal = 1;
        className = SYMBOL_TABLE.getInstance().inClassScope();
        if (res == null) {
            TYPE fieldType = SYMBOL_TABLE.getInstance().findInClassScope(name);
            if (className != null && fieldType != null)
                return fieldType;

            else if (className != null) {
                String fatherName = SYMBOL_TABLE.getInstance().findExtendsClass(className);
                if (fatherName != null) {
                    TYPE_CLASS fatherClass = (TYPE_CLASS) SYMBOL_TABLE.getInstance().find(fatherName);
                    while (fatherClass != null) {
                        for (AST_ARG_LIST it = fatherClass.dataMembers; it != null; it = it.tail) {
                            if (it.head.id.equals(name)) {
                                String resName = it.head.t.typeName;
                                return SYMBOL_TABLE.getInstance().find(resName);
                            }
                        }
                        fatherClass = fatherClass.father;
                    }
                }
                printError(line);
            }
        }

        if (res == null) {
            boolean s = SYMBOL_TABLE.getInstance().inFuncScope();
            if (s)
                res = SYMBOL_TABLE.getInstance().findInFuncScope(name);
        }

        if (res == null) {
            res = SYMBOL_TABLE.getInstance().find(name);
            if (res != null)
                inGlobal = 1;
        }

        if (res == null) {
            printError(line);
        }
        t = res;
        return res;
    }

    public TEMP IRme() {
        TEMP t = TEMP_FACTORY.getInstance().getFreshTEMP();
        if (inGlobal == 1) {
            IR.getInstance().Add_IRcommand(new IRcommand_Load_Global(t, name));
        } else {
            String realName = name;
            boolean c = false;
            IRcommand command;
            if (className != null && offsets.get(name) == null) {
                c = true;
                realName = className + "_" + name;
            }
            if (c && (!(this.t instanceof TYPE_CLASS) || !(this.t.name.equals(className)))) {
                command = new IRcommand_This_Dot_Field(t);
                ((IRcommand_This_Dot_Field) command).isAllocated = cfgVar;
            } else
                command = new IRcommand_Load_Local(realName, t);

            command.offset = GetOffset(realName);
            IR.getInstance().Add_IRcommand(command);
        }
        return t;
    }
}