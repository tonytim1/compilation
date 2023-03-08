package AST;import IR.*;
import TEMP.*;

public class AST_C_FIELD_VAR extends AST_C_FIELD {

	public AST_VAR_DEC varDec;

	public AST_C_FIELD_VAR(int lineNumber, AST_VAR_DEC varDec)
	{
		super(lineNumber);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
		
		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.print("====================== cField -> varDec\n");

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.varDec = varDec;
		this.typeCField = "varDec"; // For IR
	}

	public TYPE SemantMe() throws SEMANTIC_EXCEPTION 
	{
		TYPE t = varDec.SemantMe();
		
		// we do that so we can later search the class member based on its name (when we are out of the class scope)
		t.varName = varDec.id;

		return t;
	}
	public TEMP IRme(){
        if(varDec != null) {
            return varDec.IRme();
        }
        return null;
	}
}
