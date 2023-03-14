package AST;
import TYPES.*;
import SYMBOL_TABLE.*;
import IR.*;
import TEMP.*;


public class AST_VAR_DEC extends AST_Node {

	public AST_TYPE type;
	public String id;
	public AST_EXP exp;
	public AST_NEW_EXP newExp;

	// New type for IR part
	String scope_type;
	public int index;
	public TYPE_CLASS var_class; //The nearest class in the tree that contains the field name

	public AST_VAR_DEC(int lineNumber, AST_TYPE type, String id, AST_EXP exp, AST_NEW_EXP newExp) {
		super(lineNumber);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
		
		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		if (exp == null) {
		    if (newExp == null) {
		        System.out.format("====================== varDec -> type:%s ID( %s ) SEMICOLON\n", type.id, id);
		    } else {
		        System.out.format("====================== varDec -> type:%s ID( %s ) ASSIGN new_exp SEMICOLON\n", type.id, id);
		    }
		} else {
			System.out.format("====================== varDec -> type:%s ID( %s ) ASSIGN exp SEMICOLON\n", type.id, id);
		}

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.type = type;
		this.id = id;
		this.exp = exp;
		this.newExp = newExp;
	}

	public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{
		TYPE t;
		TYPE expType;
	
		/****************************/
		/* [1] Check If Type exists */
		/****************************/
		t = type.SemantMe();
		if (t == null)
		{
			System.out.format(">> ERROR [%d] non existing type %s - class AST_VAR_DEC\n",lineNumber, type.id);
			throw new SEMANTIC_EXCEPTION(lineNumber);
		}
		if (t.typeName == "void")
		{
			System.out.format(">> ERROR [%d] the type void can be used only as a return type in a declaration of a func: %s - class AST_VAR_DEC\n",lineNumber, type.id);
			throw new SEMANTIC_EXCEPTION(lineNumber);
		}
		
		/**************************************/
		/* [2] Check That Name does NOT exist */
		/**************************************/
		if (SYMBOL_TABLE.getInstance().findInScope(id) != null)
		{
			System.out.format(">> ERROR [%d] variable %s already exists in scope - class AST_VAR_DEC\n",lineNumber,id);
			throw new SEMANTIC_EXCEPTION(lineNumber);
		}

		/**************************************/
		/* [3] Check That it can be assign to exp */
		/**************************************/
		if (exp != null) {
			TYPE t3 = exp.SemantMe();
			System.out.format(">> INFO [%d] trying to assign exp %s to var %s - class AST_VAR_DEC\n", lineNumber, t3.typeName, t.typeName);
			if (!t.canAssign(t3)) {
				System.out.format(">> ERROR [%d] can't assign exp %s to var %s - class AST_VAR_DEC\n", lineNumber, t3.typeName, t.typeName);
				throw new SEMANTIC_EXCEPTION(lineNumber);
			}
		}

		/**************************************/
		/* [3] Check That it can be assign to newExp */
		/**************************************/
		if (newExp != null) {
			TYPE t2 = newExp.SemantMe();
			System.out.format(">> INFO [%d] trying to assign new exp %s to var %s - class AST_VAR_DEC\n",lineNumber,t2.typeName, t.typeName);
			if (!t.canAssign(t2)) {
				System.out.format(">> ERROR [%d] can't assign new exp %s to var %s - class AST_VAR_DEC\n",lineNumber,t2.typeName, t.typeName);
				throw new SEMANTIC_EXCEPTION(lineNumber);
			}
		}

		/***************************************************/
		/* [4] Enter the Var Type to the Symbol Table */
		/***************************************************/
		SYMBOL_TABLE s = SYMBOL_TABLE.getInstance();
		s.enter(id,t);

		/****************/
		/* [4.5] For IR part
		/****************/
		this.scope_type = s.getVarScope(id);
		
		if(this.scope_type.equals("local_func"))
		{
			this.index = s.getLocalIndex(id);
		} 
		else if(this.scope_type.equals("local_class"))
		{
			this.index = s.getFieldIndex(id);
			System.out.format("local name %s index %d\n", this.id, this.index);
		}

		/*********************************************************/
		/* [5] Return value is irrelevant for class declarations */
		/*********************************************************/
		System.out.format("INFO [%d] ast var dec got var:%s with type name:%s typeName:%s - class AST_VAR_DEC\n", lineNumber, id, t.name, t.typeName);
		return t;
	}

	public TEMP IRme() {
		if (exp == null && newExp == null) {
			if (this.scope_type.equals("global")) {
				if (type.typeName == "string") {
					IR.getInstance().Add_IRcommand(new IRcommand_Declare_Global_String(id, "aaa"));
				} else if (type.typeName == "int") {
					IR.getInstance().Add_IRcommand(new IRcommand_Declare_Global_Int(id, 0));
				} else { // Gal - case 4? I'm not sure
					IR.getInstance().Add_IRcommand(new IRcommand_Declare_Global_Object(id));
				}
			}
			else {
				if (this.scope_type.equals("local_class")) {
					String namec = var_class.name + "_" + id; // Gal - maybe we need the class name
					IR.getInstance().Add_IRcommand(new IRcommand_Declare_Global_Object(namec));
				}
			}
		} else if (exp != null && newExp == null) {
			String TrueId = id;
			if (this.scope_type.equals("global") || this.scope_type.equals("local_class")) { // global or class field
				if (!this.scope_type.equals("local_func") && var_class.name != null)
					TrueId = var_class.name + "_" + id;
				if (type.typeName == "string") {
					String value = ((AST_EXP_STRING) exp).value;
					System.out.format("AST_VAR_DEC allocate string: %s", value);
					IR.getInstance().Add_IRcommand(new IRcommand_Declare_Global_String(TrueId, value));
					if (var_class.name != null)
						defaultFields.put(var_class.name + "_" + id, value);
				} else if (type.typeName == "int") {
					int value = ((AST_EXP_INT) exp).value;
					IR.getInstance().Add_IRcommand(new IRcommand_Declare_Global_Int(TrueId, value));
					if (var_class.name != null)
						defaultFields.put(var_class.name+"_"+id, String.valueOf(value));
				} else {
					IR.getInstance().Add_IRcommand(new IRcommand_Declare_Global_Object(TrueId));
				}
			}
			else { //local
				TEMP t = exp.IRme();
				IR.getInstance().Add_IRcommand(new IRcommand_Store_Local(id, t, GetOffset(id)));
			}
		} else if (exp == null && newExp != null) {
			TEMP t = newExp.IRme();
			IR.getInstance().Add_IRcommand(new IRcommand_Store_Local(id, t, GetOffset(id)));
		}

		return null;
	}
}
