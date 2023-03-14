package AST;
import TYPES.*;
import SYMBOL_TABLE.*;
import IR.*;
import TEMP.*;
public class AST_VAR_EXP_LIST_STMT extends AST_STMT
{
	public AST_VAR var;
	public AST_EXP_LIST expList;
	public String name;
	public TYPE_CLASS tc;
	public TYPE_FUNCTION functionType; // for IRme
	
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

		TYPE_LIST paramTypes = null;

        if (expList != null) 
			paramTypes = (TYPE_LIST) expList.SemantMe();

		// In case var != null we want to check that id is a field in class var
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
				this.functionType = (TYPE_FUNCTION) member;

				//check params type match
				if (!(this.functionType.canAssignParams(paramTypes))) {
					System.out.format(">> ERROR [%d] mismatch in params number/type in function %s call - class AST_VAR_EXP_LIST\n", lineNumber, member.name);
					throw new SEMANTIC_EXCEPTION(lineNumber);
				}

				return this.functionType.returnType;
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

		this.functionType = (TYPE_FUNCTION) nameType;

		//check params type match
		if (!(this.functionType.canAssignParams(paramTypes))) {
			System.out.format(">> ERROR [%d] mismatch in params number/type in function %s call - class AST_VAR_EXP_LIST\n", lineNumber, nameType.name);
			throw new SEMANTIC_EXCEPTION(lineNumber);
		}

		return this.functionType.returnType;
	}
	
	public TEMP IRme(){
		TEMP t = TEMP_FACTORY.getInstance().getFreshTEMP();

		if(var == null & expList == null) 
		{
			System.out.print("AST_VAR_EXP_LIST_STMT IRME --- CASE:  ID ();\n");

			String startLabel = null;
			if (name.equals("PrintInt")) {
			startLabel = "PrintInt";
			} else if (name.equals("PrintString")) {
			startLabel = "PrintString";
			} else {
			startLabel = this.functionType.name;
			}

			IR.getInstance().Add_IRcommand(new IRcommand_Call_Func(t, startLabel, null));
			return t;
		}
		else if(var == null & expList != null) 
		{
			System.out.print("AST_VAR_EXP_LIST_STMT IRME --- CASE:  ID ( exp );\n");

			TEMP_LIST resTempsList = null;

			// set resTempList
			for (AST_EXP_LIST it = expList; it != null; it = it.tail) {
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
			  startLabel = (AST_VAR_EXP_LIST) this.functionType.name;
			}
			IR.getInstance().Add_IRcommand(new IRcommand_Call_Func(t, startLabel, resTempsList));
			return t;
		}
		else if(var != null & expList == null) 
		{
			System.out.print("AST_VAR_EXP_LIST_STMT IRME --- CASE:  var DOT ID ();\n");

			return vardotIR(var, null, tc, name);
		}
		else if(var != null & expList != null) 
		{
			System.out.print("AST_VAR_EXP_LIST_STMT IRME --- CASE:  var DOT ID ( exp );\n");

			return vardotIR(var, expList, tc, name);
		}
	}
}