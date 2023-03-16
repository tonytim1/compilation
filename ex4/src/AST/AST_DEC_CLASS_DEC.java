package AST;

import TYPES.*;
import TEMP.*;

public class AST_DEC_CLASS_DEC extends AST_DEC {
    public AST_CLASS_DEC classDec;

    public AST_DEC_CLASS_DEC(AST_CLASS_DEC classDec) {
        this.classDec = classDec;
        SerialNumber = AST_Node_Serial_Number.getFresh();

        if (classDec != null) {
            System.out.print("=============== dec -> classDec\n");
        }
    }

    public TYPE SemantMe() {
        return classDec.SemantMe();
    }

    public TEMP IRme() {
        classDec.IRme();
        return null;
    }
}
