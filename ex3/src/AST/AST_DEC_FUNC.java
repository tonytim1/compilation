package AST;

public class AST_DEC_FUNC extends AST_DEC {

	public AST_FUNC_DEC f;

	public AST_DEC_FUNC(AST_FUNC_DEC f)
	{
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
	
	public TYPE SemantMe()
	{	
		return f.SemantMe();
	}
}
