package AST;

public class AST_ARRAY_TYPE_DEF extends AST_Node {
	public String id;
	public AST_TYPE type;
	
	
	public AST_ARRAY_TYPE_DEF(String id, AST_TYPE type) {
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
		
		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.format("====================== arrayTypedef -> ARRAY ID( %s ) EQ type LBRACK RBRACK SEMICOLON", id);

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.id = id;
		this.type = type;
	}

	public TYPE SemantMe()
	{
		TYPE t;

		/****************************/
		/* [1] Check If Type exists */
		/****************************/
		t = SYMBOL_TABLE.getInstance().find(type);
		if (t == null)
		{
			System.out.format(">> ERROR [%d:%d] non existing type %s\n",2,2,type);
			System.exit(0);
		}

		/**************************************/
		/* [2] Check That Name does NOT exist */
		/**************************************/
		if (SYMBOL_TABLE.getInstance().find(id) != null)
		{
			System.out.format(">> ERROR [%d:%d] variable %s already exists in scope\n",2,2,id);
		}

		/***************************************************/
		/* [3] Enter the Function Type to the Symbol Table */
		/***************************************************/
		SYMBOL_TABLE.getInstance().enter(id, new TYPE_ARRAY(t));

		/*********************************************************/
		/* [4] Return value is irrelevant for class declarations */
		/*********************************************************/
		return null;
	}
}
