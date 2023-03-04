package AST;
import TYPES.*;
import SYMBOL_TABLE.*;
import IR.*;
import TEMP.*;

public class AST_PROGRAM extends AST_Node {
	/****************/
	/* DATA MEMBERS */
	/****************/
	public AST_DEC_LIST decList;

	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_PROGRAM(int lineNumber, AST_DEC_LIST decList)
	{
		super(lineNumber);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.print("====================== Program -> decList\n");

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.decList = decList;
	}
	public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{
		decList.SemantMe();		
		return null;
	}

	public TYPE IRme(){
        decList.IRme();
        return null;
	}
}
