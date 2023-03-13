package AST;
import TYPES.*;
import SYMBOL_TABLE.*;
import IR.*;
import TEMP.*;


public class AST_DEC_VAR extends AST_DEC {

	public AST_VAR_DEC v;

	public AST_DEC_VAR(int lineNumber, AST_VAR_DEC v)
	{
		super(lineNumber);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
		
		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.print("====================== decVar -> v");

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.v = v;
	}
	
	public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{	
		return v.SemantMe();
	}

	public TEMP IRme() {
	if (v != null) {
	    return v.IRme();
    }
    return null;
	}
}
