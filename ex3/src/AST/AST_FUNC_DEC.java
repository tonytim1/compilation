package AST;
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_FUNC_DEC extends AST_Node {

	public AST_TYPE type;
	public String id;
	public AST_TYPE_ID_LIST tid;
	public AST_STMT_LIST stmtList;

	public AST_FUNC_DEC(int lineNumber, AST_TYPE type, String id, AST_TYPE_ID_LIST tid, AST_STMT_LIST stmtList)
	{
		super(lineNumber);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
	     System.out.format("====================== funcDec -> type ID( %s ) LPAREN RPAREN LBRACE multiStmt RBRACE\n", id);
	     System.out.format("====================== funcDec -> type ID( %s ) LPAREN multiVar RPAREN LBRACE multiStmt RBRACE\n", id);

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		
		this.type = type;
		this.id = id;
		this.tid = tid;
		this.stmtList = stmtList;
	}

	public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{
		SYMBOL_TABLE s = SYMBOL_TABLE.getInstance();

		TYPE t;
		TYPE returnType = null;
		TYPE_LIST type_list = null;

		// check if function is defined in scope
		if (s.findInScope(id) != null) {
			System.out.format(">> ERROR [%d] id - %s isn't defined in scope - class AST_FUNC_DEC\n", lineNumber, id);
			throw new SEMANTIC_EXCEPTION(lineNumber);
		}

		/*******************/
		/* [0] return type */
		/*******************/
		returnType = type.SemantMe();
		if (returnType == null)
		{
			System.out.format(">> ERROR [%d] non existing return type %s - class AST_FUNC_DEC\n",lineNumber,returnType);
			throw new SEMANTIC_EXCEPTION(lineNumber);
		}

		/***************************************************/
		/* [1] Enter the Function Type to the Symbol Table */
		/***************************************************/
		SYMBOL_TABLE.getInstance().enter(id,new TYPE_FUNCTION(returnType,id,type_list));

		/****************************/
		/* [2] Begin Function Scope */
		/****************************/
		s.beginScope();

		/*********************************************************/
		/* [3] Update required return type */
		/*********************************************************/
		SYMBOL_TABLE.getInstance().required_return_type = type.SemantMe().typeName;

		/***************************/
		/* [4] Semant Input Params */
		/***************************/
		for (AST_TYPE_ID_LIST it = tid; it  != null; it = it.tail)
		{
			//t = SYMBOL_TABLE.getInstance().find(it.head.type.id);
			t = it.head.SemantMe();
			if (t == null)
			{
			    System.out.format(">> ERROR [%d] in type_id_list - class AST_FUNC_DEC\n",lineNumber);
				throw new SEMANTIC_EXCEPTION(lineNumber);
			}
			else
			{
				type_list = new TYPE_LIST(t,type_list);
				SYMBOL_TABLE.getInstance().enter(it.head.id,t);
			}
		}

		/*******************/
		/* [5] Semant Body */
		/*******************/
		stmtList.SemantMe();

		/*****************/
		/* [5] End Scope */
		/*****************/
		SYMBOL_TABLE.getInstance().endScope();

		/*****************/
		/* [6] reset return type */
		/*****************/
		SYMBOL_TABLE.getInstance().required_return_type = "reset";

		if (this.tid != null) {
		    return new TYPE_FUNCTION(returnType, this.id, (TYPE_LIST) this.tid.SemantMe());
        }
        else {
            return new TYPE_FUNCTION(returnType, this.id, null);
        }
	}
}
