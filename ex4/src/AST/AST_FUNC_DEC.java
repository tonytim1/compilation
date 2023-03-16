package AST;

import SYMBOL_TABLE.SYMBOL_TABLE;
import TEMP.TEMP;
import TYPES.*;
import IR.*;

import java.util.Map;
import java.util.HashMap;

public class AST_FUNC_DEC extends AST_Node {
    public AST_TYPE returnType;
    public String id;
    public AST_ARG_LIST arglist;
    public AST_STMT_LIST list;
    public TYPE_FUNCTION func;
    public String className;

    public AST_FUNC_DEC(AST_TYPE returnType, String id, AST_ARG_LIST arglist, AST_STMT_LIST list, int line) {
        this.returnType = returnType;
        this.id = id;
        this.arglist = arglist;
        this.list = list;
        this.line = line;

        SerialNumber = AST_Node_Serial_Number.getFresh();
    }


    public TYPE SemantMe() {
        TYPE_LIST argListTypes = null;
        TYPE returnTypeType = null;
        TYPE t;

        returnTypeType = findType(returnType.typeName);

        if (returnTypeType == null || returnTypeType instanceof TYPE_NIL) {
            printError(line);
        }

        for (AST_ARG_LIST it = arglist; it != null; it = it.tail) {
            t = findType(it.head.t.typeName);

            if (t == null) {
                printError(line);
            }

            if (t instanceof TYPE_NIL || t instanceof TYPE_VOID) {
                printError(line);
            }
            for (AST_ARG_LIST it2 = arglist; it2 != null && it2 != it; it2 = it2.tail) {
                if (it.head.id.equals(it2.head.id)) {
                    printError(line);
                }
            }
            argListTypes = new TYPE_LIST(t, argListTypes);
        }
        if (argListTypes != null) {
            argListTypes = argListTypes.reverseList();
        }

        boolean flag = true;
        className = SYMBOL_TABLE.getInstance().inClassScope();
        if (className != null) {
            String father = SYMBOL_TABLE.getInstance().findExtendsClass(className);
            if (father != null) {
                TYPE_CLASS fatherClass = (TYPE_CLASS) SYMBOL_TABLE.getInstance().find(father);
                while (fatherClass != null && flag) {
                    AST_TYPE_NAME_LIST funcs = fatherClass.functions;
                    for (AST_TYPE_NAME_LIST it = funcs; it != null; it = it.tail) {
                        TYPE_FUNCTION currF = (TYPE_FUNCTION) it.head.type;
                        if (currF.name.equals(id)) {
                            if (!(currF.returnType.name.equals(returnTypeType.name))) {
                                printError(line);
                            }
                            TYPE_LIST params = currF.params;
                            for (TYPE_LIST it2 = argListTypes; it2 != null; it2 = it2.tail) {
                                if (params == null || params.head == null
                                        || !(it2.head.name.equals(params.head.name))) {
                                    printError(line);
                                }
                                params = params.tail;
                            }
                            if (params != null) {
                                printError(line);
                            }
                            flag = false;
                            break;
                        }
                    }
                    fatherClass = fatherClass.father;
                }
            }
        }

        this.func = new TYPE_FUNCTION(returnTypeType, id, argListTypes);
        SYMBOL_TABLE.getInstance().enter(id, func);

        SYMBOL_TABLE.getInstance().beginScope("func-" + id + "-" + returnTypeType.name);

        for (AST_ARG_LIST it = arglist; it != null; it = it.tail) {
            t = findType(it.head.t.typeName);
            SYMBOL_TABLE.getInstance().enter(it.head.id, t);
        }

        list.SemantMe();
        SYMBOL_TABLE.getInstance().endScope();
        return func;
    }

    public TEMP IRme() {
        if (id.equals("main")) {
            this.id = "user_main";
        }

        int argCnt = 0;
        if (className != null)
            argCnt += 1;
        for (AST_ARG_LIST it = arglist; it != null; it = it.tail) {
            String off = String.valueOf(8 + 4 * argCnt);
            offsets.put(it.head.id, off);
            argCnt += 1;
        }

        int varCnt = 0;
        for (AST_STMT_LIST it = list; it != null; it = it.tail) {
            if (it.head instanceof AST_STMT_VAR_DEC) {
                varCnt += 1;
                continue;
            }
            if (it.head instanceof AST_STMT_IF || it.head instanceof AST_STMT_WHILE) {
                varCnt += localsInIfOrWhile(it.head);
            }
        }

        String labelStart;
        if (id.equals("user_main")) {
            labelStart = id;
        } else {
            if (className != null)
                labelStart = className + "_" + id;
            else {
                labelStart = IRcommand.getFreshLabel("start_" + id);
                offsets.put(id, labelStart);
            }
        }

        this.func.startLabel = labelStart;

        IR.getInstance().Add_IRcommand(new IRcommand_Label(labelStart));
        IR.getInstance().Add_IRcommand(new IRcommand_Prologue(varCnt));

        varCnt = 0;
        for (AST_STMT_LIST it = list; it != null; it = it.tail) {
            if (it.head instanceof AST_STMT_VAR_DEC) {
                varCnt += 1;
                AST_STMT_VAR_DEC stmtVarDec = (AST_STMT_VAR_DEC) (it.head);
                AST_VAR_DEC varDec = stmtVarDec.varDec;
                String off = String.valueOf(varCnt * (-4) + -40);
                offsets.put(varDec.id, off);
            }
            if (it.head instanceof AST_STMT_IF || it.head instanceof AST_STMT_WHILE) {
                varsInFunc = varCnt;
                varCnt += localsInIfOrWhile(it.head);
            }
            it.head.IRme();
        }

        IR.getInstance().Add_IRcommand(new IRcommand_Epilogue());

        System.out.println(offsets);
        return null;
    }
}
