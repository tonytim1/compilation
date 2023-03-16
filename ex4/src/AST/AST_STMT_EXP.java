package AST;

import TYPES.*;
import SYMBOL_TABLE.*;
import TEMP.*;
import IR.*;

public class AST_STMT_EXP extends AST_STMT {
    public AST_EXP exp;

    public AST_STMT_EXP(AST_EXP exp, int line) {
        this.exp = exp;
        this.line = line;
        SerialNumber = AST_Node_Serial_Number.getFresh();

        System.out.print("=============== stmt -> RETURN exp SEMICOLON	\n");
    }

    public TYPE SemantMe() {
        TYPE type = exp.SemantMe();
        if (type == null) {
            return null;
        }
        String returnName = type.name;

        int func = SYMBOL_TABLE.getInstance().findFunc(returnName);
        if (func == 0) {
            printError(line);
        }
        return type;
    }

    public TEMP IRme() {
        TEMP retVal = exp.IRme();
        IR.getInstance().Add_IRcommand(new IRcommand_Return(retVal));
        return null;
    }
}