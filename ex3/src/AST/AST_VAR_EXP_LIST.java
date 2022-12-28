package AST;
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_VAR_EXP_LIST extends AST_EXP
{
	public AST_VAR var;
	public AST_EXP_LIST expList;
	public String name;
	
	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_VAR_EXP_LIST(int lineNumber, AST_VAR var, AST_EXP_LIST expList, String name)
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

	public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{
		TYPE_FUNCTION functionType = null;

        if (expList != null) expList.SemantMe();

		// In case var != null we want to check that id is a field in class var
		if (var != null) {
			TYPE varType = var.SemantMe();

			if (varType.typeName != "class") {
				//ERROR var isn't class but does exist
				System.out.format(">> ERROR [%d] varType : %s isn't class - class AST_VAR_EXP_LIST\n",lineNumber, varType.typeName);
				throw new SEMANTIC_EXCEPTION(lineNumber);
			}

			TYPE_CLASS tc = (TYPE_CLASS) varType;

			TYPE member = tc.findClassFunc(name);
			if (member != null) {
				if (member.typeName != "function") {
					System.out.format(">> ERROR [%d] member : %s isn't function - class AST_VAR_EXP_LIST\n", lineNumber, member.typeName);
					throw new SEMANTIC_EXCEPTION(lineNumber);
				}
				functionType = (TYPE_FUNCTION) member;
				return functionType.returnType;
			}

			// ERROR - Didn't find a field that fits
			System.out.format(">> ERROR [%d] didn't find the right field in class - class AST_VAR_EXP_LIST\n",lineNumber);
			throw new SEMANTIC_EXCEPTION(lineNumber);
		}

	    TYPE nameType =  SYMBOL_TABLE.getInstance().find(name);
		// ERROR - Not found function of type isn't function
		if (nameType == null) {
			System.out.format(">> ERROR [%d] name %s was not found - class AST_VAR_EXP_LIST\n",lineNumber, name);
			throw new SEMANTIC_EXCEPTION(lineNumber);
		}
		if (nameType.typeName != "function") {
			System.out.format(">> ERROR [%d] nameType : %s isn't function - class AST_VAR_EXP_LIST\n",lineNumber, nameType.typeName);
			throw new SEMANTIC_EXCEPTION(lineNumber);
		}

        functionType = (TYPE_FUNCTION) nameType;
		return functionType.returnType;
	}
}
