lexer.getLine()

public class AST_DEC_ARRAY extends AST_DEC {

	public AST_ARRAY_TYPE_DEF a;

	public AST_DEC_ARRAY(int lineNumber, AST_ARRAY_TYPE_DEF a)
	{
		super(lineNumber);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
		
		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.format("====================== decArray -> c");

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.a = a;
    }
	
	public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{	
		return a.SemantMe();
	}
}
