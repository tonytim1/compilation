package SYMBOL_TABLE;


import java.io.PrintWriter;

import TYPES.*;
import AST.*;


public class SYMBOL_TABLE {
    private int hashArraySize = 13;

    private SYMBOL_TABLE_ENTRY[] table = new SYMBOL_TABLE_ENTRY[hashArraySize];
    private SYMBOL_TABLE_ENTRY top;
    private int top_index = 0;


    private int hash(String s) {
       return 1;
    }


    public void enter(String name, TYPE t) {
        int hashValue = hash(name);

        SYMBOL_TABLE_ENTRY next = table[hashValue];

        SYMBOL_TABLE_ENTRY e = new SYMBOL_TABLE_ENTRY(name, t, hashValue, next, top, top_index++);

        top = e;

        table[hashValue] = e;

        PrintMe();
    }


    public TYPE find(String name) {
        SYMBOL_TABLE_ENTRY e;

        for (e = table[hash(name)]; e != null; e = e.next) {
            if (name.equals(e.name)) {
                return e.type;
            }
        }

        return null;
    }


    public void beginScope(String name) {
        String boundary = "SCOPE-BOUNDARY-" + name;
        enter(
                boundary,
                new TYPE_SCOPE_BOUNDARIES("NONE"));

        PrintMe();
    }


    public void endScope() {
        while (!(top.name.startsWith("SCOPE-BOUNDARY"))) {
            table[top.index] = top.next;
            top_index = top_index - 1;
            top = top.prevtop;
        }
        table[top.index] = top.next;
        top_index = top_index - 1;
        top = top.prevtop;

        PrintMe();
    }

    public static int n = 0;

    public void PrintMe() {
        int i = 0;
        int j = 0;
        String dirname = "./output/";
        String filename = String.format("SYMBOL_TABLE_%d_IN_GRAPHVIZ_DOT_FORMAT.txt", n++);

        try {
            PrintWriter fileWriter = new PrintWriter(dirname + filename);

            fileWriter.print("digraph structs {\n");
            fileWriter.print("rankdir = LR\n");
            fileWriter.print("node [shape=record];\n");

            fileWriter.print("hashTable [label=\"");
            for (i = 0; i < hashArraySize - 1; i++) {
                fileWriter.format("<f%d>\n%d\n|", i, i);
            }
            fileWriter.format("<f%d>\n%d\n\"];\n", hashArraySize - 1, hashArraySize - 1);

            for (i = 0; i < hashArraySize; i++) {
                if (table[i] != null) {
                    fileWriter.format("hashTable:f%d -> node_%d_0:f0;\n", i, i);
                }
                j = 0;
                for (SYMBOL_TABLE_ENTRY it = table[i]; it != null; it = it.next) {
                    fileWriter.format("node_%d_%d ", i, j);
                    fileWriter.format("[label=\"<f0>%s|<f1>%s|<f2>prevtop=%d|<f3>next\"];\n",
                            it.name,
                            it.type.name,
                            it.prevtop_index);

                    if (it.next != null) {
                        fileWriter.format(
                                "node_%d_%d -> node_%d_%d [style=invis,weight=10];\n",
                                i, j, i, j + 1);
                        fileWriter.format(
                                "node_%d_%d:f3 -> node_%d_%d:f0;\n",
                                i, j, i, j + 1);
                    }
                    j++;
                }
            }
            fileWriter.print("}\n");
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static SYMBOL_TABLE instance = null;


    protected SYMBOL_TABLE() {
    }


    public static SYMBOL_TABLE getInstance() {
        if (instance == null) {
            instance = new SYMBOL_TABLE();

            instance.enter("int", TYPE_INT.getInstance());
            instance.enter("string", TYPE_STRING.getInstance());

            instance.enter("void", TYPE_VOID.getInstance());
            instance.enter(
                    "PrintInt",
                    new TYPE_FUNCTION(
                            TYPE_VOID.getInstance(),
                            "PrintInt",
                            new TYPE_LIST(
                                    TYPE_INT.getInstance(),
                                    null)));

            instance.enter(
                    "PrintString",
                    new TYPE_FUNCTION(
                            TYPE_VOID.getInstance(),
                            "PrintString",
                            new TYPE_LIST(
                                    TYPE_STRING.getInstance(),
                                    null)));
        }
        return instance;
    }

    public String getScope() {
        SYMBOL_TABLE_ENTRY curr = top;

        while (curr != null) {
            if (curr.name.startsWith("SCOPE-BOUNDARY")) {
                return curr.name.split("-")[2];
            }
            curr = curr.prevtop;
        }
        return "global";
    }

    public String inClassScope() {
        SYMBOL_TABLE_ENTRY curr = top;

        while (curr != null) {
            if (curr.name.startsWith("SCOPE-BOUNDARY-class"))
                return curr.name.split("-")[3];

            curr = curr.prevtop;
        }
        return null;
    }

    public TYPE findInClassScope(String name) {
        SYMBOL_TABLE_ENTRY curr = top;
        TYPE t = null;

        while (curr != null && !curr.name.startsWith("SCOPE-BOUNDARY-class")) {
            if (curr.name.equals(name)) {
                t = curr.type;
            }
            curr = curr.prevtop;
        }
        return t;
    }

    public TYPE findClass(String name) {
        SYMBOL_TABLE_ENTRY e = top;
        while (e.name != null) {
            if (e.name.equals(name))
                return e.type;
            if (e.name.startsWith("SCOPE-BOUNDARY-class"))
                if (e.name.split("-")[3].equals(name))
                    return new TYPE_CLASS(null, name, null, null);
            e = e.prevtop;
        }
        return null;
    }

    public String findExtendsClass(String className) {
        SYMBOL_TABLE_ENTRY curr = top;
        while (curr != null) {
            if (curr.name.startsWith("SCOPE-BOUNDARY-class")) {
                String[] splited = curr.name.split("-");
                if (splited.length > 5 && splited[3].equals(className))
                    return curr.name.split("-")[5];
                return null;
            }
            curr = curr.prevtop;
        }
        return null;
    }


    public boolean inFuncScope() {
        SYMBOL_TABLE_ENTRY curr = top;

        while (curr != null) {
            if (curr.name.startsWith("SCOPE-BOUNDARY-func"))
                return true;

            curr = curr.prevtop;
        }
        return false;
    }

    public TYPE findInFuncScope(String name) {
        SYMBOL_TABLE_ENTRY curr = top;
                while (curr != null && !curr.name.startsWith("SCOPE-BOUNDARY-func")) {
            if (curr.name.equals(name)) {
                return curr.type;
            }
            curr = curr.prevtop;
        }
        return null;
    }


    public TYPE findInCurrScope(String name) {
        SYMBOL_TABLE_ENTRY curr = top;
        TYPE t = null;

        while (curr != null && !curr.name.startsWith("SCOPE-BOUNDARY")) {
            if (curr.name.equals(name)) {
                t = curr.type;
            }
            curr = curr.prevtop;
        }
        return t;
    }

    public int findFunc(String returnType) {
        SYMBOL_TABLE_ENTRY table_entry = top;
        AST_TYPE_VOID af = new AST_TYPE_VOID(-1);
        while (table_entry != null) {
            if (table_entry.name.startsWith("SCOPE-BOUNDARY-func")) {
                String[] split_name = table_entry.name.split("-");
                if (split_name[split_name.length - 1].equals(returnType))
                    return 1;

                TYPE name = af.findType(returnType);
                if (name.isClass()) {
                                        TYPE_CLASS father = ((TYPE_CLASS) name).father;
                    while (father != null) {
                        if (father.name.equals(split_name[split_name.length - 1]))
                            return 1;
                        father = father.father;
                    }
                    String f = findExtendsClass(name.name);
                    TYPE_CLASS ex = (TYPE_CLASS) SYMBOL_TABLE.getInstance().find(f);
                    father = ex.father;
                    while (father != null) {
                        if (father.name.equals(split_name[split_name.length - 1]))
                            return 1;
                        father = father.father;
                    }
                }
            }
            if (table_entry.name.startsWith("SCOPE-BOUNDARY-class"))
                return 0;

            table_entry = table_entry.prevtop;
        }
        return 0;
    }

    public TYPE isRealFunc(String name, AST_EXP_LIST params) {
        SYMBOL_TABLE_ENTRY table_entry = top;
        while (table_entry != null && table_entry.name != null) {
            if (table_entry.type.isFunc()) {
                if (!(table_entry.name.equals(name))) {
                    table_entry = table_entry.prevtop;
                    continue;
                }

                TYPE_LIST parameters = ((TYPE_FUNCTION) table_entry.type).params;
                for (AST_EXP_LIST it = params; it != null; it = it.tail) {
                    if (parameters == null)
                        return null;

                    TYPE curr = parameters.head;
                    if (curr == null)
                        return null;

                    TYPE t = it.head.SemantMe();

                    if (t.name == curr.name) {
                        parameters = parameters.tail;
                        continue;
                    }
                    else
                    {
                        if (t.name.equals("nil") &&
                                (curr.name.equals("int") || curr.name.equals("string"))) {
                            return null;
                        }
                        parameters = parameters.tail;
                    }
                }
                if (parameters == null || parameters.head == null)
                    return ((TYPE_FUNCTION) table_entry.type).returnType;

                return null;
            }
            table_entry = table_entry.prevtop;
        }
        return null;
    }

    public TYPE compare(TYPE_FUNCTION realFunc, AST_EXP_LIST params) {
        TYPE_LIST args = realFunc.params;
        for (AST_EXP_LIST it = params; it != null; it = it.tail) {
            if (args == null)
                return null;

            TYPE argType = args.head;
            if (argType == null)
                return null;

            TYPE paramType = it.head.SemantMe();
            if (!(params.areTypesAssinable(argType, paramType)))
                return null;

            args = args.tail;
        }
        if (args == null || args.head == null)
            return realFunc.returnType;

        return null;
    }

    public void clearScope() {
        while (!(top.name.startsWith("SCOPE-BOUNDARY-class"))) {
            table[top.index] = top.next;
            top_index = top_index - 1;
            top = top.prevtop;
        }
        table[top.index] = top.next;
        top_index = top_index - 1;
        top = top.prevtop;
    }
}
