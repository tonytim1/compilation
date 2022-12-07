package AST;

public class AST_VAR_ID_EXP extends AST_Node
{
	public AST_VAR_EXP_LIST varExp;

	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_VAR_ID_EXP(AST_VAR_EXP_LIST varExp)
	{
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.print("====================== var id exp;\n");

		/*******************************/
		/* COPY INPUT DATA NENBERS ... */
		/*******************************/
		this.varExp = varExp;
	}
}
