package AST;

import TYPES.*;
import TEMP.*;

public class AST_C_FIELD_VAR_DEC extends AST_C_FIELD {
    public AST_VAR_DEC varDec;

    public AST_C_FIELD_VAR_DEC(AST_VAR_DEC varDec) {
        this.varDec = varDec;
        SerialNumber = AST_Node_Serial_Number.getFresh();

        if (varDec != null) {
            System.out.print("=============== cfield -> varDec\n");
        }
    }


    public TYPE SemantMe() {
        return varDec.SemantMe();
    }

    public TEMP IRme() {
        varDec.IRme();
        return null;
    }
}
