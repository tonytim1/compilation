package AST;

import TYPES.*;

import SYMBOL_TABLE.*;
import TEMP.*;
import IR.*;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.PrintWriter;

public abstract class AST_Node {
    public String typeName;
    public int SerialNumber;
    public int line;

    public static int varsInFunc;
    private static String file;

    public static Map<String, String> offsets = new HashMap<>();
    public static Map<String, Integer> classSize = new HashMap<>();
    public static Map<String, ArrayList<String>> classfields = new HashMap<>();
    public static Map<String, String> defaultFields = new HashMap<>();
    public static Map<String, Map<String, Integer>> classFuncsOff = new HashMap<>();


    public TYPE SemantMe() {
        return null;
    }

    public TYPE_LIST SemantMe(int ignore) {
        return null;
    }

    public TEMP IRme() {
        return null;
    }

    public TEMP_LIST IRme(int ignore) {
        return null;
    }

    public void getFile(String file) {
        this.file = file;
    }

    public void printError(int line) {
        try {
            PrintWriter file_writer;
            file_writer = new PrintWriter(this.file);
            file_writer.print("ERROR");
            file_writer.print("(");
            file_writer.print(line);
            file_writer.print(")");
            file_writer.close();
            System.exit(0);
        } catch (Exception e) {
        }
    }

    public boolean areTypesAssinable(TYPE t1, TYPE t2) {
        if (t1 == t2) {
            return true;
        }

        if (t1 == TYPE_INT.getInstance() || t1 == TYPE_STRING.getInstance() || t1 == TYPE_VOID.getInstance() ||
                t2 == TYPE_INT.getInstance() || t2 == TYPE_STRING.getInstance() || t2 == TYPE_VOID.getInstance()) {
            return false;
        }

        if ((t1 instanceof TYPE_NIL) || (t2 instanceof TYPE_NIL)) {
            return true;
        }

        if (t1.isArray() && t2.isArray()) {
            return t1.name.equals(t2.name) || t2.name.equals(((TYPE_ARRAY) t1).entryType.name + "[]")
                    || areTypesAssinable(((TYPE_ARRAY) t1).entryType, ((TYPE_ARRAY) t2).entryType);
        }

        if (t1.isClass() && t2.isClass() && t1.name.equals(t2.name))
            return true;

        if (t2.isClass() && t1.isClass()) {
            TYPE_CLASS father = ((TYPE_CLASS) t2).father;
            while (father != null) {
                if (father.name.equals(t1.name))
                    return true;
                father = father.father;
            }

            String cllass = SYMBOL_TABLE.getInstance().inClassScope();
            if (cllass != null && cllass.equals(t2.name) && t1.isClass()) {
                String fatherName = SYMBOL_TABLE.getInstance().findExtendsClass(t2.name);
                if (fatherName.equals(t1.name))
                    return true;
                TYPE_CLASS father_class = ((TYPE_CLASS) SYMBOL_TABLE.getInstance().find(fatherName)).father;
                while (father_class != null) {
                    if (father_class.name.equals(t1.name))
                        return true;
                    father_class = father_class.father;
                }
            }
        }


        return false;
    }

    public TYPE funcType(String id, AST_EXP_LIST list, int line) {
        TYPE type = SYMBOL_TABLE.getInstance().isRealFunc(id, list);

        if (type == null) {
            printError(line);
        }
        return type;
    }

    public TYPE findType(String name) {
        TYPE t = SYMBOL_TABLE.getInstance().find(name);

        if (t == null) {
            String cl = SYMBOL_TABLE.getInstance().inClassScope();
            if (cl != null && cl.equals(name)) {
                t = new TYPE_CLASS(null, name, null, null);
            }
        }
        return t;
    }

    public TYPE isFuncOfClass(String className, String funcName, AST_EXP_LIST funcArgs, int line) {

        if (SYMBOL_TABLE.getInstance().inClassScope() != null &&
                SYMBOL_TABLE.getInstance().inClassScope().equals(className)) {
            TYPE f = SYMBOL_TABLE.getInstance().findInClassScope(funcName);
            if (f instanceof TYPE_FUNCTION)
                return funcType(funcName, funcArgs, line);
            String extendClass = SYMBOL_TABLE.getInstance().findExtendsClass(className);
            if (extendClass != null) {
                TYPE_CLASS father = (TYPE_CLASS) (SYMBOL_TABLE.getInstance().find(className));
                while (father != null) {
                    AST_TYPE_NAME_LIST funcs = father.functions;
                    for (AST_TYPE_NAME_LIST it = funcs; it != null; it = it.tail) {
                        if (it.head.name.equals(funcName))
                            return SYMBOL_TABLE.getInstance().compare((TYPE_FUNCTION) it.head.type, funcArgs);
                    }
                    father = father.father;
                }
            }
            return null;
        }

        TYPE cl = SYMBOL_TABLE.getInstance().find(className);
        if (cl == null || !(cl.isClass())) {
            printError(line);
        }

        TYPE_CLASS currClass = (TYPE_CLASS) cl;
        TYPE_FUNCTION a = null;
        while (currClass != null) {
            for (AST_TYPE_NAME_LIST it = currClass.functions; it != null; it = it.tail) {
                if (it.head.name.equals(funcName)) {
                    a = (TYPE_FUNCTION) it.head.type;
                    break;
                }
            }
            currClass = currClass.father;
        }

        if (a == null) {
            printError(line);
        }
        return SYMBOL_TABLE.getInstance().compare(a, funcArgs);

    }


    public static int GetOffset(String name) {
        return Integer.valueOf(offsets.get(name));
    }

    public static int getClassSize(String name) {
        return Integer.valueOf(classSize.get(name));
    }

    public int localsInIfOrWhile(AST_STMT scope) {
        AST_STMT_LIST body = null;
        if (scope instanceof AST_STMT_IF)
            body = ((AST_STMT_IF) scope).body;
        else
            body = ((AST_STMT_WHILE) scope).body;
        int result = 0;
        for (AST_STMT_LIST it = body; it != null; it = it.tail) {
            if (it.head instanceof AST_STMT_VAR_DEC)
                result += 1;

            if (it.head instanceof AST_STMT_IF || it.head instanceof AST_STMT_WHILE) {
                result += localsInIfOrWhile(it.head);
                continue;
            }
        }
        return result;
    }

    public void ifScope(AST_STMT_LIST body) {
        Map<String, String> temps = new HashMap<>();
        int varCnt = varsInFunc;
        for (AST_STMT_LIST it = body; it != null; it = it.tail) {
            if (it.head instanceof AST_STMT_VAR_DEC) {
                varCnt += 1;
                AST_STMT_VAR_DEC a = (AST_STMT_VAR_DEC) (it.head);
                AST_VAR_DEC b = (AST_VAR_DEC) (a.varDec);
                if (offsets.get(b.id) != null)
                    temps.put(b.id, offsets.get(b.id));
                offsets.put(b.id, String.valueOf(varCnt * (-4) + -40));
            }
            if (it.head instanceof AST_STMT_IF || it.head instanceof AST_STMT_WHILE) {
                varsInFunc = varCnt;
                varCnt += localsInIfOrWhile(it.head);
            }
            it.head.IRme();
        }

        for (Map.Entry<String, String> entry : temps.entrySet()) {
            offsets.put(entry.getKey(), entry.getValue());
        }
    }

    public TEMP varIdExpIRme(AST_VAR var, AST_EXP_LIST list, TYPE_CLASS tl, String id) {
        TEMP varAddress = var.IRme();
        TEMP_LIST resTempsList = null;

        for (AST_EXP_LIST it = list; it != null; it = it.tail) {
            TEMP curr = it.head.IRme();
            resTempsList = new TEMP_LIST(curr, resTempsList);
        }

        if (resTempsList != null) {
            resTempsList = resTempsList.reverseList();
        }

        TEMP t = TEMP_FACTORY.getInstance().getFreshTEMP();

        IRcommand ic = new IRcommand_Virtual_Call(t, varAddress, id, resTempsList);
        ic.offset = classFuncsOff.get(tl.name).get(id);
        IR.getInstance().Add_IRcommand(ic);
        return t;
    }

}
