package AST;

public class AST_DEC_VAR extends AST_DEC {

	public AST_VAR_DEC v;

	public AST_DEC_VAR(AST_VAR_DEC v)
	{
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
		
		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.format("====================== decVar -> v", id1);

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.v = v;
}
