lexer.getLine()
import IR.*;
import TEMP.*;

public class AST_EXP_STRING extends AST_EXP {
	
	public String value;

	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_EXP_STRING(int lineNumber, String value) 
	{
		super(lineNumber);	
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.format("====================== exp -> STRING( %s )\n", value);
		
		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.value = value;
	}

	public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{
		return new TYPE_STRING();
	}
	public TEMP IRme()
	{
	    TEMP t = TEMP_FACTORY.getInstance().getFreshTEMP();
	    IR.getInstance().Add_IRcommand(new IRcommandConstString(t,value));
	    return t;
	}
}
