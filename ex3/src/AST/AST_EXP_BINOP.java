package AST;
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_EXP_BINOP extends AST_EXP
{
	AST_BINOP OP;
	public AST_EXP left;
	public AST_EXP right;
	
	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_EXP_BINOP(int lineNumber, AST_EXP left,AST_EXP right,AST_BINOP OP)
	{
		super(lineNumber);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.print("====================== exp -> exp BINOP exp\n");

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.left = left;
		this.right = right;
		this.OP = OP;
	}

	public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{
	    /* binop  PLUS = 1
        MINUS 2
        TIMES 3
        DIVIDE 4
        EQ 5
        LT 6
        GT 7
        NE 8 */
		TYPE t1 = null;
		TYPE t2 = null;

		if (left  != null) t1 = left.SemantMe();
		if (right != null) t2 = right.SemantMe();

		if (t1.typeName != t2.typeName) {
		    // the expressions don't have the same type
		    System.out.format("binop -> the expressions don't have the same type: %s, %s", t1.typeName, t2.typeName);
            throw new SEMANTIC_EXCEPTION(lineNumber);
		}

		if (OP.OP == 1) { // binop is +
		    // check if both expressions are from type int or string
		    if (t1.typeName == "int")
            {
                return TYPE_INT.getInstance();
            }
            if (t1.typeName == "string")
            {
                return TYPE_STRING.getInstance();
            }
            // error if there is exp + exp and the expressions are not from the same type type int or string
            System.out.format("binop -> there is exp + exp and the expressions are not both int or string: %s, %s", t1.typeName, t2.typeName);
            throw new SEMANTIC_EXCEPTION(lineNumber);
		}

		if ((OP.OP != 1) && (OP.OP != 5) && (OP.OP != 8))
		{
		    if (t1.typeName == "int")
            {
                if (OP.OP == 4) { // check for divide in 0
                    TYPE_INT t2Int = (TYPE_INT) t2;
                    if (t2Int.isZero) {
                        System.out.print("binop -> divide in 0");
                        throw new SEMANTIC_EXCEPTION(lineNumber);
                    }
                }
                return TYPE_INT.getInstance();
            }
		    // the expressions are not both ints for binops -, *, /, <, >
		    System.out.format("binop -> the expressions are not both ints for binops -, *, /, <, >: %s, %s", t1.typeName, t2.typeName);
            throw new SEMANTIC_EXCEPTION(lineNumber);
		}

		return t1; // both left and right are from the same type
	}
}
