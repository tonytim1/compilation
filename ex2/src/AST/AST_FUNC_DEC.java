package AST;

public class AST_FUNC_DEC extends AST_DEC {

	public AST_TYPE type;
	public String id;
	public AST_TYPE_ID tid;
	public AST_STMT_LIST stmtList;
	
	public AST_FUNC_DEC(AST_FUNC_DEC funcDec)
	{
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.print("====================== funcDec\n");

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/

		this.type = funcDec.type;
		this.id = funcDec.id;
		this.tid = funcDec.tid;
		this.stmtList = funcDec.stmtList;
	}

	public AST_FUNC_DEC(AST_TYPE type, String id, AST_TYPE_ID tid, AST_STMT_LIST stmtList)
	{
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		if (multiVar == null) System.out.format("====================== funcDec -> type ID( %s ) LPAREN RPAREN LBRACE multiStmt RBRACE\n", id);
		if (multiVar != null) System.out.format("====================== funcDec -> type ID( %s ) LPAREN multiVar RPAREN LBRACE multiStmt RBRACE\n", id);

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		
		this.type = type;
		this.id = id;
		this.tid = tid;
		this.stmtList = stmtList;
	}
}
