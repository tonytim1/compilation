package AST;
import TYPES.*;
import SYMBOL_TABLE.*;
import IR.*;
import TEMP.*;


public class AST_FUNC_DEC extends AST_Node {

	public AST_TYPE type;
	public String id;
	public AST_TYPE_ID_LIST tid;
	public AST_STMT_LIST stmtList;

    // New type for IR part
    String scope_type;
    String class_name; //The nearest class in the tree that contains the function name
    int varNum;

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

		/*********************************************************/
		/* [2] Update required return type */
		/*********************************************************/
		SYMBOL_TABLE.getInstance().required_return_type = returnType.typeName;

		/*******************/
		/* [6] Semant Body */
		/*******************/
		stmtList.SemantMe();

		/*****************/
		/* [7] End Scope */
		/*****************/
		SYMBOL_TABLE.getInstance().endScope();

		/****************/
		/* [7.5] For IR part
		/****************/
		this.varNum = s.func_local_index;

		/*****************/
		/* [8] reset return type */
		/*****************/
		SYMBOL_TABLE.getInstance().required_return_type = "";

		/***************************************************/
		/* [9] Enter the Function Type to the Symbol Table */
		/***************************************************/
		TYPE function = new TYPE_FUNCTION(returnType,id,type_list);
		SYMBOL_TABLE.getInstance().enter(id,function);

		/****************/
		/* [10] For IR part
		/****************/
		this.scope_type = s.getVarScope(id);
		if (this.scope_type.equals("local_class"))
		{
			this.class_name = s.curr_class.name;
		}

		return function;
	}

	public TEMP IRme() {
		if (id.equals("main")) {
			this.id = "user_main";
		}

		int argCnt = 0; // number of arguments
		if (class_name != "")
			argCnt += 1; // this

		/////////// arguments ///////////////////////
		for (AST_TYPE_ID_LIST it = tid; it != null; it = it.tail) {
			String off = String.valueOf(8 + 4 * argCnt);
			offsets.put(it.head.id, off);
			argCnt += 1;
		}

		/////////// local variables///////////////////////
		int varCnt = 0; // number of local variables
		for (AST_STMT_LIST it = stmtList; it != null; it = it.tail) {
			if (it.head instanceof AST_STMT_VAR_DEC) {
				varCnt += 1;
				continue;
			}
			if (it.head instanceof AST_STMT_IF || it.head instanceof AST_STMT_WHILE) {
				varCnt += localsInIfOrWhile(it.head);
			}
		}

		///////////////////////////////////////////////////////////////////////////////////////
		// prologue
		String labelStart = null;
		if (id.equals("user_main")) {
			labelStart = id;
		} else {
			if (class_name != null)
				labelStart = class_name + "_" + id;
			else {
				labelStart = IRcommand.getFreshLabel("start_" + id);
				offsets.put(id, labelStart);
			}
		}

		this.func.startLabel = labelStart;

		IR.getInstance().Add_IRcommand(new IRcommand_Label(labelStart));
		IR.getInstance().Add_IRcommand(new IRcommand_Prologue(varCnt));

		// body
		varCnt = 0;
		for (AST_STMT_LIST it = stmtList; it != null; it = it.tail) {
			if (it.head instanceof AST_STMT_VAR_DEC) {
				varCnt += 1;
				AST_STMT_VAR_DEC a = (AST_STMT_VAR_DEC) (it.head);
				AST_VAR_DEC b = (AST_VAR_DEC) (a.varDec);
				String off = String.valueOf(varCnt * (-4) + -40);
				offsets.put(b.id, off);
			}
			if (it.head instanceof AST_STMT_IF || it.head instanceof AST_STMT_WHILE) {
				varsInFunc = varCnt;
				varCnt += localsInIfOrWhile(it.head);
			}
			it.head.IRme();
		}

		// epilogue
		IR.getInstance().Add_IRcommand(new IRcommand_Epilogue());

		System.out.println(offsets);
		return null;
	}
}
