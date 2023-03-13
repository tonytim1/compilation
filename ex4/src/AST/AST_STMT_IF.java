package AST;
import TYPES.*;
import SYMBOL_TABLE.*;
import IR.*;
import TEMP.*;


public class AST_STMT_IF extends AST_STMT
{
	public AST_EXP cond;
	public AST_STMT_LIST body;
	public boolean inFunc;

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

		inFunc = SYMBOL_TABLE.getInstance().inFuncScope();

		/*********************************************************/
		/* [4] Return value is irrelevant for class declarations */
		/*********************************************************/
		return null;
	}
	public TEMP IRme()
	{
		System.out.format("AST_STMT_IF" + "- IRme\n");

		/*******************************/
		/* [1] Allocate 2 fresh labels */
		/*******************************/
	
		String label_end = IRcommand.getFreshLabel("end");
		String label_start = IRcommand.getFreshLabel("start");
	
		/*********************************/
		/* [2] entry label for the while */
		/*********************************/
		IR.getInstance().Add_IRcommand(new IRcommand_Label(label_start));
	
		/********************/
		/* [3] cond.IRme(); */
		/********************/
		TEMP cond_temp = cond.IRme();
	
		/******************************************/
		/* [4] Jump conditionally to the loop end */
		/******************************************/
		IR.getInstance().Add_IRcommand(new IRcommand_Jump_beqz(cond_temp, label_end));
	
		/*******************/
		/* [5] body.IRme() */
		/*******************/
	
		if (infunc)
		  ifScope(body);
	
		else
		  body.IRme();
		// how do you store locals in if?
	
		/**********************/
		/* [7] Loop end label */
		/**********************/
		IR.getInstance().Add_IRcommand(new IRcommand_Label(label_end));
	
		/*******************/
		/* [8] return null */
		/*******************/
		return null;
	}
}