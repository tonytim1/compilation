package AST;

public class AST_EXP_VAR extends AST_EXP
{
	public AST_VAR var;

	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_EXP_VAR(AST_VAR var)
	{
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.print("====================== exp -> var\n");

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.var = var;
	}
}
