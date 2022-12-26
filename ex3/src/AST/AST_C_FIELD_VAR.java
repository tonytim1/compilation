package AST;
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_C_FIELD_VAR extends AST_C_FIELD {

	public AST_VAR_DEC varDec;

	public AST_C_FIELD_VAR(int lineNumber, AST_VAR_DEC varDec)
	{
		super(lineNumber);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
		
		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.print("====================== cField -> varDec\n");

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.varDec = varDec;
	}

	public TYPE SemantMe() throws SEMANTIC_EXCEPTION 
	{ 
		return varDec.SemantMe();
	}
}
