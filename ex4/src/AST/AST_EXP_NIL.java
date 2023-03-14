package AST;
import TYPES.*;
import SYMBOL_TABLE.*;
import IR.*;
import TEMP.*;


public class AST_EXP_NIL extends AST_EXP 
{
	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	
	public AST_EXP_NIL(int lineNumber)
	{
		super(lineNumber);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.print("====================== exp -> NIL\n");

	}

	public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{
		return new TYPE_NIL();
	}

	public TEMP IRme() {
	    TEMP t = TEMP_FACTORY.getInstance().getFreshTEMP();
	    IR.getInstance().Add_IRcommand(new IRcommand_Set_Nil(t));
	    return t;
	}
}
