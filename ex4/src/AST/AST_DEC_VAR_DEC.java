package AST;

import TYPES.*;
import TEMP.*;

public class AST_DEC_VAR_DEC extends AST_DEC {
    public AST_VAR_DEC varDec;

    public AST_DEC_VAR_DEC(AST_VAR_DEC varDec) {
        this.varDec = varDec;

        SerialNumber = AST_Node_Serial_Number.getFresh();

        if (varDec != null) {
            System.out.print("=============== dec -> varDec\n");
        }
    }


    public TYPE SemantMe() {
        if (varDec != null) {
            return varDec.SemantMe();
        }

        return null;
    }

    public TEMP IRme() {
        if (varDec != null) {
            return varDec.IRme();
        }
        return null;
    }

}