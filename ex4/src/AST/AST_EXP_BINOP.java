package AST;
import TYPES.*;
import SYMBOL_TABLE.*;
import IR.*;
import TEMP.*;



public class AST_EXP_BINOP extends AST_EXP
{
	AST_BINOP OP;
	public AST_EXP left;
	public AST_EXP right;

	boolean stringConcat = false; // Used for IRme
	boolean stringEQ = false; // Used for IRme
	
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
		TYPE t1 = left.SemantMe();
		TYPE t2 = right.SemantMe();

		if (t1 == null || t2 == null)
		{
			System.out.format(">> ERROR [%d] binop -> at least one of the expressions type does not exist - class AST_EXP_BINOP", lineNumber);
            throw new SEMANTIC_EXCEPTION(lineNumber);
		}

		//Added section for IR
		if (OP.OP == 5)
		{
		    if ((t1.typeName == "string") && (t2.typeName == "string"))
		    {
		        this.stringEQ = true;
		    }

		}
		//End added section for IR

		if (OP.OP == 1) { // binop is +
		    // check if both expressions are from type int or string
		    if ((t1.typeName == "int") && (t2.typeName == "int"))
            {
                return new TYPE_INT();
            }
			if ((t1.typeName == "string") && (t2.typeName == "string"))
            {
                this.stringConcat = true; // Added for IR
                return new TYPE_STRING();
            }
            // error if there is exp + exp and the expressions are not from the same type type int or string
            System.out.format(">> ERROR [%d] binop -> there is exp + exp and the expressions are not both int or string: %s, %s - class AST_EXP_BINOP\n",lineNumber,t1.typeName, t2.typeName);
            throw new SEMANTIC_EXCEPTION(lineNumber);
		}

		if ((OP.OP != 1) && (OP.OP != 5) && (OP.OP != 8))
		{
		    if (t1.typeName == "int")
            {
                if (OP.OP == 4) { // check for divide in 0
                    TYPE_INT t2Int = (TYPE_INT) t2;
                    if (t2Int.isZero) {
                        System.out.format(">> ERROR [%d] binop -> divide in 0 - class AST_EXP_BINOP", lineNumber);
                        throw new SEMANTIC_EXCEPTION(lineNumber);
                    }
                }
                return new TYPE_INT();
            }
		    // the expressions are not both ints for binops -, *, /, <, >
		    System.out.format(">> ERROR [%d] binop -> the expressions are not both ints for binops -, *, /, <, >: %s, %s", lineNumber, t1.typeName, t2.typeName);
            throw new SEMANTIC_EXCEPTION(lineNumber);
		}

		// EQ or NE
		if (!(t1.canCompare(t2))) {
		    System.out.format(">> ERROR [%d] binop -> the expressions don't have the same type: %s, %s - class AST_EXP_BINOP", lineNumber, t1.typeName, t2.typeName);
            throw new SEMANTIC_EXCEPTION(lineNumber);
		}

		return new TYPE_INT();
	}

	public TEMP IRme()
	{
	    TEMP t1 = null;
	    TEMP t2 = null;
	    TEMP dst = TEMP_FACTORY.getInstance().getFreshTMP();

	    t1 = left.IRme();
	    t2 = right.IRme();
	    switch(OP) {
			case 1: {
				// case: addition
				if (this.stringConcat) {
				    IR.getInstance().Add_IRcommand(new IRcommand_Binop_String_Concat(dst, t1, t2));
				}
				else {
				    IR.getInstance().Add_IRcommand(new IRcommand_Binop_Add_Integers(dst,t1,t2));
                }
				break;
			}
			case 2: {
				// case: subtraction
				IR.getInstance().Add_IRcommand(new IRcommand_Binop_Sub_Integers(dst,t1,t2));
				break;
			}
			case 3: {
				// case: multiplication
				IR.getInstance().Add_IRcommand(new IRcommand_Binop_Mul_Integers(dst,t1,t2));
				break;
			}
			case 4: {
				// case: division
				IR.getInstance().Add_IRcommand(new IRcommand_Binop_Div_Integers(dst,t1,t2));
				break;
			}
			case 5: {
				// case: equality testing
				if(this.stringEQ) {
					// string equality testing
					IR.getInstance().Add_IRcommand(new IRcommand_Binop_EQ_Strings(dst,t1,t2));
				}
				else {
					// NOTE: equality testing between arrays and class instances is similar to integer equality testing
					IR.getInstance().Add_IRcommand(new IRcommand_Binop_EQ_Integers(dst,t1,t2));
				}
				break;
			}
			case 6: {
				// case: LT testing
				IR.getInstance().Add_IRcommand(new IRcommand_Binop_LT_Integers(dst,t1,t2));
				break;
			}
			case 7: {
				// case: GT testing (equivalent to LT testing with swapped parameters)
				IR.getInstance().Add_IRcommand(new IRcommand_Binop_LT_Integers(dst,t2,t1));
				break;
			}
			case 8: {
			    // case: NEQ testing
			    IR.getInstance().Add_IRcommand(new IRcommand_Binop_NEQ_Integers(dst,t2,t1));
			    break;
			}
		}
		return dst;
	}
}