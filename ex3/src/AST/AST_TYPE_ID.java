package AST;
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_TYPE_ID extends AST_Node {

	public AST_TYPE type;
	public String id;

	public AST_TYPE_ID(int lineNumber, AST_TYPE type, String id)
	{
		super(lineNumber);
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

	public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{
		SYMBOL_TABLE s = SYMBOL_TABLE.getInstance();
		
		//TYPE t = SYMBOL_TABLE.getInstance().find(type.id);
		TYPE t = type.SemantMe();
		
		// check if type is undeclared or void (void isnt allowed for ids) or name exists in scope
		if (t == null || t == TYPE_VOID.getInstance() || s.findInScope(id) != null)
		{
		    System.out.format(">> ERROR [%d] type of %s is null or void or not exists in the scope - class AST_TYPE_ID\n" ,lineNumber, type.typeName);
			throw new SEMANTIC_EXCEPTION(lineNumber);
		}
		else
		{
			/*******************************************************/
			/* Enter var with name=name and type=t to symbol table */
			/*******************************************************/
			SYMBOL_TABLE.getInstance().enter(id, t);
		}

		/****************************/
		/* return (existing) type t */
		/****************************/
		return t;
	}	
}
