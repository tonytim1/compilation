package AST;
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_DEC_FUNC extends AST_DEC {

	public AST_FUNC_DEC f;

	public AST_DEC_FUNC(int lineNumber, AST_FUNC_DEC f)
	{
		super(lineNumber);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
		
		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.format("====================== decFunc -> f");

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.f = f;
	}
	
	public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{
	    TYPE t = f.SemantMe();
	    System.out.format("ROY'S DEBUGGING - %s", t.typeName);
		return t;
	}
}
