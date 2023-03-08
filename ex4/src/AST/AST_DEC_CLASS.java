package AST;
import TYPES.*;
import SYMBOL_TABLE.*;
import IR.*;
import TEMP.*;



public class AST_DEC_CLASS extends AST_DEC {

	public AST_CLASS_DEC c;

	public AST_DEC_CLASS(int lineNumber, AST_CLASS_DEC c)
	{
		super(lineNumber);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
		
		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.format("====================== decClass -> c");

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.c = c;
	}

	public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{	
		return c.SemantMe();
	}

	public TEMP IRme() {
	    return c.IRme();
	}
}
