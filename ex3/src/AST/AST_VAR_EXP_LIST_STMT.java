package AST;
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_VAR_EXP_LIST_STMT extends AST_STMT
{
	public AST_VAR var;
	public AST_EXP_LIST expList;
	public String name;
	
	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_VAR_EXP_LIST_STMT(int lineNumber, AST_VAR var, AST_EXP_LIST expList, String name)
	{
		super(lineNumber);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		if(var == null & expList == null) System.out.print("====================== ID ();\n");
		if(var == null & expList != null) System.out.print("====================== ID ( exp );\n");
		if(var != null & expList == null) System.out.print("====================== var DOT ID ();\n");
		if(var != null & expList != null) System.out.print("====================== var DOT ID ( exp );\n");

		/*******************************/
		/* COPY INPUT DATA NENBERS ... */
		/*******************************/
		this.var = var;
		this.expList = expList;
		this.name = name;
	}

	public TYPE SemantMe() throws SEMANTIC_EXCEPTION {

        if (expList != null) expList.SemantMe();

	    TYPE nameType =  SYMBOL_TABLE.getInstance().find(name);
	    if (nameType == null || nameType.typeName != "function") {
	        // ERROR - Not found function of type isn't function
	        throw new SEMANTIC_EXCEPTION(lineNumber);
	    }

	    // In case var != null we want to check that id is a field in class var
        if (var != null) {
            TYPE varType = var.SemantMe();

            if (varType.typeName != "class") {
                //ERROR var isn't class but does exist
                throw new SEMANTIC_EXCEPTION(lineNumber);
            }
            TYPE_CLASS tc = (TYPE_CLASS) varType;
            for (TYPE_LIST it = tc.data_members; it != null; it = it.tail)
		    {
		    	if (it.head.name == name)
		    	{
		    		return it.head;
			    }
		    }
		    // ERROR - Didn't find a field that fits
		    throw new SEMANTIC_EXCEPTION(lineNumber);
        }
		return nameType;
	}
}