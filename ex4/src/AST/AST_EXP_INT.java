package AST;
import TYPES.*;
import SYMBOL_TABLE.*;
import IR.*;
import TEMP.*;



public class AST_EXP_INT extends AST_EXP
{
	public int value;
	public int isPositive;
	
	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_EXP_INT(int lineNumber, int value, int isPositive)
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
		this.isPositive = isPositive;
	}

	public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{
	    if (this.value == 0) {
	        return new TYPE_INT(true);
	    }
		return new TYPE_INT();
	}


	public TEMP IRme()
	{
		TEMP t = TEMP_FACTORY.getInstance().getFreshTemp();
		IR.getInstance().Add_IRcommand(new IRcommand_ConstInt(t,value));
		return t;
	}
}
