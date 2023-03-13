package AST;
import TYPES.*;
import SYMBOL_TABLE.*;
import IR.*;
import TEMP.*;


public class AST_VAR_EXP_LIST extends AST_EXP
{
	public AST_VAR var;
	public AST_EXP_LIST expList;
	public String name;
	public TYPE_CLASS tc;
	public TYPE_FUNCTION func; // for IRme
	
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
		TYPE_LIST paramTypes = null;

        if (expList != null) 
			paramTypes = (TYPE_LIST) expList.SemantMe();

		// In case var != null we want to check that name is a field in class var
		if (var != null) {
			TYPE varType = var.SemantMe();

			if (varType.typeName != "class") {
				//ERROR var isn't class but does exist
				System.out.format(">> ERROR [%d] varType : %s isn't class - class AST_VAR_EXP_LIST\n",lineNumber, varType.typeName);
				throw new SEMANTIC_EXCEPTION(lineNumber);
			}

			this.tc = (TYPE_CLASS) varType;

			TYPE member = tc.findClassFunc(name);
			if (member != null) {
				if (member.typeName != "function") {
					System.out.format(">> ERROR [%d] member : %s isn't function - class AST_VAR_EXP_LIST\n", lineNumber, member.typeName);
					throw new SEMANTIC_EXCEPTION(lineNumber);
				}
				functionType = (TYPE_FUNCTION) member;

				//check params type match
				if (!(functionType.canAssignParams(paramTypes))) {
					System.out.format(">> ERROR [%d] mismatch in params number/type in function %s call - class AST_VAR_EXP_LIST\n", lineNumber, member.name);
					throw new SEMANTIC_EXCEPTION(lineNumber);
				}
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

		//check params type match
		if (!(functionType.canAssignParams(paramTypes))) {
			System.out.format(">> ERROR [%d] mismatch in params number/type in function %s call - class AST_VAR_EXP_LIST\n", lineNumber, nameType.name);
			throw new SEMANTIC_EXCEPTION(lineNumber);
		}

		return functionType.returnType;
	}

	public TEMP_LIST IRme(int ignore){
		TEMP t = TEMP_FACTORY.getInstance().getFreshTEMP();

		if(var != null & expList != null) 
		{
			System.out.print("AST_VAR_EXP_LIST IRME (temp_list) --- CASE:  var DOT ID ( exp );\n");

			if (expList == null) {
				return new TEMP_LIST(var.IRme(), null);
			}
			return new TEMP_LIST(var.IRme(), expList.IRme(0));
		}
	}
	
	public TEMP IRme(){
		TEMP t = TEMP_FACTORY.getInstance().getFreshTEMP();

		if(var == null & expList == null) 
		{
			System.out.print("AST_VAR_EXP_LIST IRME --- CASE:  ID ();\n");

			String startLabel = null;
			if (name.equals("PrintInt")) {
			startLabel = "PrintInt";
			} else if (name.equals("PrintString")) {
			startLabel = "PrintString";
			} else {
			startLabel = this.func.name;
			}

			IR.getInstance().Add_IRcommand(new IRcommand_Call_Func(t, startLabel, null));
			return t;
		}
		else if(var == null & expList != null) 
		{
			System.out.print("AST_VAR_EXP_LIST IRME --- CASE:  ID ( exp );\n");

			TEMP_LIST resTempsList = null;

			// set resTempList
			for (AST_EXPLIST it = expList; it != null; it = it.tail) {
			  TEMP curr = it.head.IRme();
			  resTempsList = new TEMP_LIST(curr, resTempsList);
			}
		
			// reverse expList
			if (resTempsList != null) {
			  resTempsList = resTempsList.reverseList();
			}
		
			String startLabel = null;
			if (name.equals("PrintInt")) {
			  startLabel = "PrintInt";
			} else if (name.equals("PrintString")) {
			  startLabel = "PrintString";
			} else {
			  startLabel = this.func.name;
			}
			IR.getInstance().Add_IRcommand(new IRcommand_Call_Func(t, startLabel, resTempsList));
			return t;
		}
		else if(var != null & expList == null) 
		{
			System.out.print("AST_VAR_EXP_LIST IRME --- CASE:  var DOT ID ();\n");

			return vardotIR(var, null, tc, name);
		}
		else if(var != null & expList != null) 
		{
			System.out.print("AST_VAR_EXP_LIST IRME --- CASE:  var DOT ID ( exp );\n");

			return vardotIR(var, expList, tc, name);
		}
	}
}
