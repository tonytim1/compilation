package AST;

public class AST_C_FIELD_FUNC extends AST_C_FIELD {

	public AST_FUNC_DEC funcDec;
	
	public AST_C_FIELD_FUNC(AST_FUNC_DEC funcDec)
	{
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
		
		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.print("====================== cField -> funcDec\n");

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.funcDec = funcDec;
	}
}
