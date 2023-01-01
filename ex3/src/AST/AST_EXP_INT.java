package AST;
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_EXP_INT extends AST_EXP
{
	public int value;
	public int isNegative;
	
	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_EXP_INT(int lineNumber, int value, int isNegative)
	{
		super(lineNumber);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.format("====================== exp -> INT( %d )\n", value);

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.value = value;
		this.isNegative = isNegative;
	}

	public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{
		boolean intNegative = false;
		boolean intZero = false;

		if (isNegative == 1) {
			intNegative = true;
		}
	    if (this.value == 0) {
			intZero = true;
	    }
		return new TYPE_INT(intZero, intNegative);
	}
}
