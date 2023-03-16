package AST;

import IR.*;
import TEMP.*;
import TYPES.*;

public class AST_NEW_EXP_TYPE_EXP extends AST_NEW_EXP {
    public AST_TYPE type;
    public AST_EXP exp;

    public AST_NEW_EXP_TYPE_EXP(AST_TYPE type, AST_EXP exp, int line) {
        this.type = type;
        this.exp = exp;
        this.line = line;
        SerialNumber = AST_Node_Serial_Number.getFresh();
        System.out.print("=============== newExp -> NEW type:t LBRACK exp:e RBRACK \n");
    }

    public TYPE SemantMe() {
        TYPE t1;
        TYPE t2;

        if (type == null || exp == null || type.typeName.equals("nil") || type.typeName.equals("void")) {
            printError(this.line);
        }

        t1 = type.SemantMe();
        t2 = exp.SemantMe();

        if (t1 == null || t2 == null) {
            printError(line);
        }

        if (!areTypesAssinable(t2, TYPE_INT.getInstance())) {
            printError(this.line);
        }

        if ((exp instanceof AST_EXP_INT) && (((AST_EXP_INT) exp).value <= 0)) {
            printError(this.line);
        }

        if (exp instanceof AST_EXP_MINUS_INT) {
            printError(this.line);
        }

        return new TYPE_ARRAY(t1, t1.name + "[]");
    }

    public TEMP IRme() {
        TEMP t1 = TEMP_FACTORY.getInstance().getFreshTEMP();
        TEMP t2 = exp.IRme();
        IR.getInstance().Add_IRcommand(new IRcommand_New_Array(t1, t2));

        return t1;
    }
}