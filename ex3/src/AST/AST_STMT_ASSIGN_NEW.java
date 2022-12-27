package AST;
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_STMT_ASSIGN_NEW extends AST_STMT 
{
	public AST_VAR var;
	public AST_NEW_EXP exp;

	/*******************/
	/*  CONSTRUCTOR(S) */
	/*******************/
	public AST_STMT_ASSIGN_NEW(int lineNumber, AST_VAR var, AST_NEW_EXP exp)
	{
		super(lineNumber);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.print("====================== stmt -> var ASSIGN newExp SEMICOLON\n");

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.var = var;
		this.exp = exp;
	}

	public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{
		TYPE t1 = null;
		TYPE t2 = null;

		if (var != null) t1 = var.SemantMe();
		if (exp != null) t2 = exp.SemantMe();

		if (t1 != t2)
		{
			System.out.format(">> ERROR [%d] type mismatch for var := exp\n",lineNumber);
			throw new SEMANTIC_EXCEPTION(lineNumber);
		}
		return null;
	}
}
