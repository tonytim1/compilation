package AST;
import TYPES.*;
import SYMBOL_TABLE.*;
import IR.*;
import TEMP.*;


public class AST_STMT_IF extends AST_STMT
{
	public AST_EXP cond;
	public AST_STMT_LIST body;

	/*******************/
	/*  CONSTRUCTOR(S) */
	/*******************/
	public AST_STMT_IF(int lineNumber, AST_EXP cond,AST_STMT_LIST body)
	{
		super(lineNumber);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
		
		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		
		System.out.format("====================== stmt -> IF LPAREN exp RPAREN LBRACE multiStmt RBRACK\n");
		
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
			// the expression it not int
            System.out.format(">> ERROR [%d] condition type is %s and not int - class AST_STMT_IF \n",lineNumber, condType.typeName);
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
	    TEMP t1 = cond.IRme();
		System.out.format("AST_STMT_WHILE ---------- %s ,%s\n", cond.getClass().getName(), t1);
	    String end_label = IRcommand.getFreshLabel("end_label");
	    IR.getInstance().Add_IRcommand(new IRcommand_Jump_If_Eq_To_Zero(t1,end_label));
		if(body != null) body.IRme();
		IR.getInstance().Add_IRcommand(new IRcommand_Label(end_label));
		return null;
	}
}