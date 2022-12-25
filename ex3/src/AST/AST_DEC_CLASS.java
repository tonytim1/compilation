package AST;
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_DEC_CLASS extends AST_DEC {

	public AST_CLASS_DEC c;

	public AST_DEC_CLASS(AST_CLASS_DEC c)
	{
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

	public TYPE SemantMe()
	{	
		return c.SemantMe();
	}
}
