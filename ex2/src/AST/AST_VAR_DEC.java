package AST;
import SYMBOL_TABLE.*

public class AST_VAR_DEC extends AST_DEC {

	public AST_TYPE type;
	public String id;
	public AST_EXP exp;
	public AST_newExp newExp;

	public AST_VAR_DEC(AST_VAR_DEC varDec) {
	    /******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.print("====================== varDec\n");

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.type = varDec.type;
		this.id = varDec.id;
		this.exp = varDec.exp;
		this.newExp = varDec.newExp;
	}

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
