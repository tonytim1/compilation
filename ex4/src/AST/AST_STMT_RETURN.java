package AST;
import TYPES.*;
import SYMBOL_TABLE.*;
import IR.*;
import TEMP.*;


public class AST_STMT_RETURN extends AST_STMT {
	public AST_EXP exp;
	
	/*******************/
	/*  CONSTRUCTOR(S) */
	/*******************/
	public AST_STMT_RETURN(int lineNumber, AST_EXP exp)
	{
		super(lineNumber);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		if(exp != null) System.out.print("====================== stmt -> RETURN exp SEMICOLON\n");
		if(exp == null) System.out.print("====================== stmt -> RETURN SEMICOLON\n");

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.exp = exp;
	}
	public TYPE SemantMe() throws SEMANTIC_EXCEPTION {
	// Check that exp's type agrees with function return type
	TYPE expType;
	if (exp != null) {
	    expType = exp.SemantMe();
	    if (expType.typeName != SYMBOL_TABLE.getInstance().required_return_type) {
	        // Error required return type is wrong
            System.out.format(">> ERROR [%d] required return type is : %s but our return type is : %s - class AST_STMT_RETURN\n",lineNumber, SYMBOL_TABLE.getInstance().required_return_type, expType.typeName);
	        throw new SEMANTIC_EXCEPTION(lineNumber);
	    }
	}
	 return null;
	}

	public TEMP IRme()
	{
	    Temp t = null;
	    if (exp != null)
	    {
	        t = exp.IRme();
	    }
	    IR.getInstance().Add_IRcommand(new IRcommand_FuncReturn(t));
	    return null;
	}
}
