package AST;
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_VAR_SUBSCRIPT extends AST_VAR
{
	public AST_VAR var;
	public AST_EXP subscript;
	
	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_VAR_SUBSCRIPT(AST_VAR var,AST_EXP subscript)
	{
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.print("====================== var -> var [ exp ]\n");

		/*******************************/
		/* COPY INPUT DATA NENBERS ... */
		/*******************************/
		this.var = var;
		this.subscript = subscript;
	}

	public TYPE SemantMe()
	{
	    if (var == null) {
	        return null;
	    }

	    TYPE varType = var.SemantMe();
	    if (varType.typeName != "array") {
	        //var is not an array
	        System.exit(0);
	    }
	    if (subscript != null)
	    {
	        TYPE varSubscript = subscript.SemantMe();
	    }
	    if (varSubscript.typeName != "int") {
	        //subscript is not integer
	        System.exit(0);
	    }
	    return null;
	}
}
