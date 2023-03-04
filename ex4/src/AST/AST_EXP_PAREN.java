lexer.getLine()
import IR.*;
import TEMP.*;

public class AST_EXP_PAREN extends AST_EXP {
	
	public AST_EXP exp;

	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_EXP_PAREN(int lineNumber, AST_EXP exp) 
	{
		super(lineNumber);		
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


	public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{
	    return exp.SemantMe();
    }

    public TEMP IRme() {
        return exp.IRme();
    }
}
