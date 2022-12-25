package AST;

public class AST_TYPE_ID extends AST_Node {

	public AST_TYPE type;
	public String id;

	public AST_TYPE_ID(AST_TYPE type, String id)
	{
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
		
		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
        System.out.format("====================== type:t id:name \n", id);

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.type = type;
		this.id = id;
	}

	public TYPE SemantMe()
	{
		TYPE t = SYMBOL_TABLE.getInstance().find(type);
		if (t == null)
		{
			/**************************/
			/* ERROR: undeclared type */
			/**************************/
			System.exit(0);
			return null;
		}
		else
		{
			/*******************************************************/
			/* Enter var with name=name and type=t to symbol table */
			/*******************************************************/
			SYMBOL_TABLE.getInstance().enter(id,t);
		}

		/****************************/
		/* return (existing) type t */
		/****************************/
		return t;
	}	
}
