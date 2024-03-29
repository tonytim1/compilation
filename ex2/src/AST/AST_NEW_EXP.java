package AST;

public class AST_NEW_EXP extends AST_Node {

	public AST_TYPE type;
	public AST_EXP exp;
	
	public AST_NEW_EXP(AST_TYPE type, AST_EXP exp) {
		
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
		
		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		if(exp == null) System.out.print("====================== newExp -> NEW type\n");
		if(exp != null) System.out.print("====================== newExp -> NEW type RBRACK exp LBRACK\n");

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.type = type;
		this.exp = exp;
	}

}
