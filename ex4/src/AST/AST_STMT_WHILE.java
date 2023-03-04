package AST;
import TYPES.*;
import SYMBOL_TABLE.*;
import IR.*;
import TEMP.*;


public class AST_STMT_WHILE extends AST_STMT
{
	public AST_EXP cond;
	public AST_STMT_LIST body;

	/*******************/
	/*  CONSTRUCTOR(S) */
	/*******************/
	public AST_STMT_WHILE(int lineNumber, AST_EXP cond,AST_STMT_LIST body)
	{
		super(lineNumber);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
		
		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		
		System.out.format("====================== stmt -> WHILE LPAREN exp RPAREN LBRACE multiStmt RBRACE\n");

		
		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.cond = cond;
		this.body = body;
	}

	public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{
		/****************************/
		/* [0] Semant the Condition */
		/****************************/
		TYPE condType = cond.SemantMe();
		if (condType.typeName != "int")
		{
			System.out.format(">> ERROR [%d] condition inside WHILE is not integral - class AST_STMT_WHILE\n",lineNumber);
			throw new SEMANTIC_EXCEPTION(lineNumber);
		}

		/*************************/
		/* [1] Begin Class Scope */
		/*************************/
		SYMBOL_TABLE.getInstance().beginScope();

		/***************************/
		/* [2] Semant Data Members */
		/***************************/
		body.SemantMe();

		/*****************/
		/* [3] End Scope */
		/*****************/
		SYMBOL_TABLE.getInstance().endScope();

		/*********************************************************/
		/* [4] Return value is irrelevant for class declarations */
		/*********************************************************/
		return null;
	}

	public TEMP IRme()
	{
	    String loop_start = IRcommand.getFreshLabel("loop_start");
		String loop_end = IRcommand.getFreshLabel("loop_end");
		IR.getInstance().Add_IRcommand(new IRcommand_Label(loop_start));
		TEMP t = cond.IRme();
		IR.getInstance().Add_IRcommand(new IRcommand_Jump_If_Eq_To_Zero(t, loop_end));
		if(body != null) body.IRme();
		IR.getInstance().Add_IRcommand(new IRcommand_Jump_Label(loop_start));
		IR.getInstance().Add_IRcommand(new IRcommand_Label(loop_end));

		return null;
	}
}