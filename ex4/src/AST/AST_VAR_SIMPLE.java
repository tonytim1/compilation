package AST;
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_VAR_SIMPLE extends AST_VAR
{
	/************************/
	/* simple variable name */
	/************************/
	public String name;
	// For IRME
	Boolean is_string = false;

	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_VAR_SIMPLE(int lineNumber, String name)
	{
		super(lineNumber);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.format("====================== var -> ID( %s )\n",name);

		/*******************************/
		/* COPY INPUT DATA NENBERS ... */
		/*******************************/
		this.name = name;
	}
	public void PrintMe()
	{
		System.out.format("AST VAR SIMPLE %s\n", name);
	}

	public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{
		SYMBOL_TABLE s = SYMBOL_TABLE.getInstance();
	    TYPE t = s.find(name);
	    if (t == null) { // In case we use undefined variable
	        System.out.format(">> ERROR [%d] field %s was not found - AST_VAR_SIMPLE\n",lineNumber,name);
		    throw new SEMANTIC_EXCEPTION(lineNumber);
	    }
		
		// For IRME
		this.scope_type = s.getVarScope(name);
		if (this.scope_type.equals("local_class"))
		{
			this.index = s.getFieldIndex(name);
		}
		else
		{
			this.index = s.getLocalIndex(name);
		}
		if (id_type == TYPE_STRING.getInstance()) 
		{
			this.is_string = true;
		}

	    return t;
    }
	
	public TEMP IRme(){
		TEMP dst = TEMP_FACTORY.getInstance().getFreshTEMP();
		IR.getInstance().Add_IRcommand(new IRcommand_Load(dst, name, scope_type, index, is_string));
		return dst;
	}
}
