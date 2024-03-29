package AST;
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_VAR_SIMPLE extends AST_VAR
{
	/************************/
	/* simple variable name */
	/************************/
	public String name;

	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_VAR_SIMPLE(int lineNumber, String name)
	{
		super(lineNumber);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.format("====================== var -> ID( %s )\n",name);

		/*******************************/
		/* COPY INPUT DATA NENBERS ... */
		/*******************************/
		this.name = name;
	}
	public void PrintMe()
	{
		System.out.format("AST VAR SIMPLE %s\n", name);
	}

	public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{
	    TYPE t = SYMBOL_TABLE.getInstance().find(name);
	    if (t == null) { // In case we use undefined variable
	        System.out.format(">> ERROR [%d] field %s was not found - AST_VAR_SIMPLE\n",lineNumber,name);
		    throw new SEMANTIC_EXCEPTION(lineNumber);
	    }
	    return t;
    }
}
