package AST;

public class AST_DEC extends AST_Node {

	public AST_VAR_DEC varDec;
	public AST_FUNC_DEC funcDec;
	public AST_CLASS_DEC classDec;
	public AST_ARRAY_TYPE_DEF arrayTypedef;
	
	public AST_DEC(AST_VAR_DEC varDec) 
	{
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
		
		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.print("====================== dec -> varDec\n");

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.varDec = varDec;
		this.funcDec = null;
		this.classDec = null;
		this.arrayTypedef = null;
	}
	
	public AST_DEC(AST_FUNC_DEC funcDec) 
	{
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
		
		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.print("====================== dec -> funcDec\n");

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.funcDec = funcDec;
		this.varDec = null;
		this.classDec = null;
		this.arrayTypedef = null;
	}
	
	public AST_DEC(AST_CLASS_DEC classDec) 
	{
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
		
		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.print("====================== dec -> classDec\n");

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.classDec = classDec;
		this.varDec = null;
		this.funcDec = null;
		this.arrayTypedef = null;
	}
	
	public AST_DEC(AST_ARRAY_TYPE_DEF arrayTypedef) 
	{
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
		
		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.print("====================== dec -> arrayTypedef\n");

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.arrayTypedef = arrayTypedef;
		this.varDec = null;
		this.funcDec = null;
		this.classDec = null;
	}
}
