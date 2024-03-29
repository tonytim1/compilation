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
			System.out.format(">> ERROR [%d] function - %s already exists in scope - class AST_FUNC_DEC\n", lineNumber, id);
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

		// check if in not in global scope and not in a class
		if (!(s.isGlobalScope()) && (s.curr_class == null))
		{
			System.out.format(">> ERROR [%d] function declared not in global scope and not under class %s - class AST_FUNC_DEC\n",lineNumber,id);
			throw new SEMANTIC_EXCEPTION(lineNumber);
		}

		/****************************/
		/* [1] Begin Function Scope */
		/****************************/
		s.beginScope();

		/*********************************************************/
		/* [2] Update required return type */
		/*********************************************************/
		//System.out.format(">> INFO[%d] setting required_return_type to %s - class AST_FUNC_DEC\n",lineNumber,returnType);

//		//create return_type TYPE
//		TYPE returnTypeType = new TYPE_NIL();
//		if ((returnType == "string") || (returnType == "int") || (returnType == "void") || (returnType == "function")) {
//			returnTypeType.typeName = returnType;
//		} else {
//			returnTypeType = s.find(returnType);
//			if (returnTypeType == null) {
//				System.out.format(">> ERROR [%d] return type %s of func %s is not defined - class AST_FUNC_DEC\n",lineNumber,returnType, id);
//				throw new SEMANTIC_EXCEPTION(lineNumber);
//			}
//		}

		SYMBOL_TABLE.getInstance().required_return_type = returnType;

		/***************************/
		/* [3] Semant Input Params */
		/***************************/
		if (tid != null) {
			type_list = (TYPE_LIST) tid.SemantMe();
		}

		TYPE_FUNCTION type_function = new TYPE_FUNCTION(returnType,id,type_list);

		/**********************************************/
		/* [4] Check for overloading / overriding */
		/**********************************************/
		if (s.curr_class != null)
		{
			if (s.curr_class.findClassVar(id) != null)
			{
				System.out.format(">> ERROR [%d] function %s overrides a variable instead of a method - class AST_FUNC_DEC\n",lineNumber,id);
				throw new SEMANTIC_EXCEPTION(lineNumber);
			}
			TYPE overriden = s.curr_class.findClassFunc(id);
			if (overriden != null && !(type_function).isSignatureEqual((TYPE_FUNCTION) overriden)) 
			{
				System.out.format(">> ERROR [%d] function %s overrloads a method which in not legal - class AST_FUNC_DEC\n",lineNumber,id);
				throw new SEMANTIC_EXCEPTION(lineNumber);
			}
		}

		/***************************************************/
		/* [5] Enter the Function Type to the Symbol Table (inside the function scope in case of a recursive function) */
		/***************************************************/
		SYMBOL_TABLE.getInstance().enter(id,new TYPE_FUNCTION(returnType,id,type_list));

		/*******************/
		/* [6] Semant Body */
		/*******************/
		stmtList.SemantMe();

		/*****************/
		/* [7] End Scope */
		/*****************/
		SYMBOL_TABLE.getInstance().endScope();

		/***************************************************/
		/* [8] Enter the Function Type to the Symbol Table */
		/***************************************************/
		TYPE function = new TYPE_FUNCTION(returnType,id,type_list);
		SYMBOL_TABLE.getInstance().enter(id,function);

		/*****************/
		/* [9] reset return type */
		/*****************/
		//System.out.format(">> INFO[%d] reset required_return_type - class AST_FUNC_DEC\n",lineNumber);
		SYMBOL_TABLE.getInstance().required_return_type = new TYPE_NIL();
		SYMBOL_TABLE.getInstance().required_return_type.typeName = "";

		return function;
	}
}
