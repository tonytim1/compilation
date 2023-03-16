package AST;

import TYPES.*;
import SYMBOL_TABLE.*;
import IR.*;
import TEMP.*;

import java.util.Map;
import java.util.HashMap;

public class AST_STMT_IF extends AST_STMT {
    public AST_EXP cond;
    public AST_STMT_LIST body;
    public boolean infunc;

    public AST_STMT_IF(AST_EXP cond, AST_STMT_LIST body, int line) {
        this.cond = cond;
        this.body = body;
        this.line = line;

        SerialNumber = AST_Node_Serial_Number.getFresh();
    }

    public TYPE SemantMe() {
        if (cond.SemantMe() != TYPE_INT.getInstance()) {
            printError(this.line);
        }

        SYMBOL_TABLE table = SYMBOL_TABLE.getInstance();
        table.beginScope("if");
        body.SemantMe();
        table.endScope();
        infunc = table.inFuncScope();
        return TYPE_INT.getInstance();
    }

    public TEMP IRme() {
        String label_end = IRcommand.getFreshLabel("end");
        String label_start = IRcommand.getFreshLabel("start");

        IR.getInstance().Add_IRcommand(new IRcommand_Label(label_start));
        TEMP cond_temp = cond.IRme();
        IR.getInstance().Add_IRcommand(new IRcommand_Jump_Beqz(cond_temp, label_end));

        if (infunc) {
            ifScope(body);
        } else {
            body.IRme();
        }

        IR.getInstance().Add_IRcommand(new IRcommand_Label(label_end));
        return null;
    }
}
