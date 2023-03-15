package AST;

import TYPES.*;

import TEMP.*;
import IR.*;

public class AST_BINOP extends AST_Node {

    int number;
    public AST_EXP left;
    public AST_EXP right;
    public TYPE leftType;

    public AST_BINOP(int number, AST_EXP left, AST_EXP right, int line) {
        this.number = number;
        this.left = left;
        this.right = right;
        this.line = line;

        SerialNumber = AST_Node_Serial_Number.getFresh();
    }



    public TYPE SemantMe() {
        
        TYPE t1 = null;
        TYPE t2 = null;

        if (left != null)
            t1 = left.SemantMe();
        if (right != null)
            t2 = right.SemantMe();

        if (left == null || right == null) {
            printError(line);
        }
        this.leftType = t1; 
        if (number >= 6 )         {
            if (type_equals(t1, t2) || type_equals(t2, t1))                 return TYPE_INT.getInstance();

            if ((t1 == TYPE_INT.getInstance()) || (t2 == TYPE_STRING.getInstance()) ||
                    (t1 == TYPE_STRING.getInstance()) || (t2 == TYPE_INT.getInstance()))             {
                                printError(line);
            }

            if (t1 != TYPE_NIL.getInstance() && t2 != TYPE_NIL.getInstance()) {
                                printError(line);
            }

            return TYPE_INT.getInstance();         }

        if (number == 0)         {
            if ((t1 == TYPE_INT.getInstance()) && (t2 == TYPE_INT.getInstance()))                 return TYPE_INT.getInstance();

            if ((t1 == TYPE_STRING.getInstance()) && (t2 == TYPE_STRING.getInstance()))                 return TYPE_STRING.getInstance();

            printError(line);
        }

                if ((t1 != TYPE_INT.getInstance()) || (t2 != TYPE_INT.getInstance())) {                         printError(line);
        }

                if ((right instanceof AST_EXP_INT) && ((AST_EXP_INT) right).value == 0 && number == 3) {
                        printError(line);
        }
        return TYPE_INT.getInstance();
    }

    public TEMP IRme() {
        TEMP t1 = null;
        TEMP t2 = null;

        if (left != null)
            t1 = left.IRme();
        if (right != null)
            t2 = right.IRme();

        TEMP dst = TEMP_FACTORY.getInstance().getFreshTEMP();

        if (number == 0)         {
                        if (leftType == TYPE_STRING.getInstance()) {
                IR.getInstance().Add_IRcommand(new IRcommand_Binop_Concat_Strings(dst, t1, t2));
            }

                        else {
                IR.getInstance().Add_IRcommand(new IRcommand_Binop_Add_Integers(dst, t1, t2));
            }
        }
        if (number == 2)         {
            IR.getInstance().Add_IRcommand(new IRcommand_Binop_Mul_Integers(dst, t1, t2));
        }
        if (number == 6)         {
            if (leftType == TYPE_STRING.getInstance()) {                IR.getInstance().Add_IRcommand(new IRcommand_Binop_EQ_Strings(dst, t1, t2));
            } else
                IR.getInstance().Add_IRcommand(new IRcommand_Binop_EQ_Contents(dst, t1, t2));

        }
        if (number == 7)         {
            if (leftType == TYPE_STRING.getInstance()) {                IR.getInstance().Add_IRcommand(new IRcommand_Binop_NEQ_Strings(dst, t1, t2));
            } else
                IR.getInstance().Add_IRcommand(new IRcommand_Binop_NEQ_Contents(dst, t1, t2));

        }

        if (number == 5)         {
            IR.getInstance().Add_IRcommand(new IRcommand_Binop_LT_Integers(dst, t1, t2));
        }
        if (number == 4)         {
            IR.getInstance().Add_IRcommand(new IRcommand_Binop_GT_Integers(dst, t1, t2));
        }
        if (number == 1)         {
            IR.getInstance().Add_IRcommand(new IRcommand_Binop_SUB_Integers(dst, t1, t2));
        }
        if (number == 3)         {
            IR.getInstance().Add_IRcommand(new IRcommand_Binop_DIV_Integers(dst, t1, t2));
        }

        return dst;
    }

}