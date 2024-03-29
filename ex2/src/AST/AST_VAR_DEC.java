package AST;

public class AST_VAR_DEC extends AST_Node {

	public AST_TYPE type;
	public String id;
	public AST_EXP exp;
	public AST_NEW_EXP newExp;

	public AST_VAR_DEC(AST_TYPE type, String id, AST_EXP exp, AST_NEW_EXP newExp) {
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
		
		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		if (exp == null) {
		    if (newExp == null) {
		        System.out.format("====================== varDec -> type ID( %s ) SEMICOLON\n", id);
		    } else {
		        System.out.format("====================== varDec -> type ID( %s ) ASSIGN new_exp SEMICOLON\n", id);
		    }
		} else {
			System.out.format("====================== varDec -> type ID( %s ) ASSIGN exp SEMICOLON\n", id);
		}

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.type = type;
		this.id = id;
		this.exp = exp;
		this.newExp = newExp;
	}
}
