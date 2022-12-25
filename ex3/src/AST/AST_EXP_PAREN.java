package AST;
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_EXP_PAREN extends AST_EXP {
	
	public AST_EXP exp;

	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_EXP_PAREN(AST_EXP exp) 
	{		
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.print("====================== exp -> LPAREN exp RPAREN\n");
		
		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.exp = exp;
	}


	public TYPE SemantMe()
	{
	    return exp.SemantMe();
    }
}