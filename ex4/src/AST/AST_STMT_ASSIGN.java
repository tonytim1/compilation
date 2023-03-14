package AST;
import TYPES.*;
import SYMBOL_TABLE.*;
import IR.*;
import TEMP.*;
public class AST_STMT_ASSIGN extends AST_STMT
{
	/***************/
	/*  var := exp */
	/***************/
	public AST_VAR var;
	public AST_EXP exp;
	public TYPE_CLASS inclass;

	/*******************/
	/*  CONSTRUCTOR(S) */
	/*******************/
	public AST_STMT_ASSIGN(int lineNumber, AST_VAR var,AST_EXP exp)
	{
		super(lineNumber);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.print("====================== stmt -> var ASSIGN exp SEMICOLON\n");

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.var = var;
		this.exp = exp;
	}

	public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{
		TYPE t1 = var.SemantMe();
		TYPE t2 = exp.SemantMe();

		if (t1 == null || t2 == null)
		{
			System.out.format(">> ERROR [%d] -> at least one of the expressions type does not exist - class AST_STMT_ASSIGN", lineNumber);
            throw new SEMANTIC_EXCEPTION(lineNumber);
		}

		if (!(t1.canAssign(t2)))
		{
			System.out.format(">> ERROR [%d] type mismatch for var %s %s, exp %s %s - class AST_STMT_ASSIGN\n",lineNumber, t1.typeName, t1.name, t2.typeName, t2.name);
			throw new SEMANTIC_EXCEPTION(lineNumber);
		}
		inclass = SYMBOL_TABLE.getInstance().curr_class;
		return null;
	}

	public TEMP IRme(){
		TEMP exp_temp = exp.IRme();

		if(var instanceof AST_VAR_SIMPLE)
		{
			System.out.print("AST_STMT_ASSIGN IRME --- CASE:  AST_VAR_SIMPLE\n");
		    // old: IR.getInstance().Add_IRcommand(new IRcommand_Store(var.name, exp_temp ,var.scope_type ,var.index));
			
			if (var.scope_type == "global") {
				IR.getInstance().Add_IRcommand(new IRcommand_Store_Global(exp_temp, var.name));
			}
			else if (inclass != null) {
				if (var.scope_type != "local_class") {
					System.out.format("WEIRD!! AST_STMT_ASSIGN IRME AST_VAR_SIMPLE is in class but scope type isnt local_class, but: %s\n", var.scope_type);
				}
				String varName = inclass.name + "_" + ((AST_VAR_SIMPLE) var).name;
				IR.getInstance().Add_IRcommand(new IRcommand_Store_Field(inclass.name, varName, exp_temp, GetOffset(varName)));
			}
			else if (var.scope_type == "local_func") {
				String varName = ((AST_VAR_SIMPLE) var).name;
				IR.getInstance().Add_IRcommand(new IRcommand_Store_Local(varName, exp_temp, GetOffset(varName)));
			}
			else {
				System.out.format("BAD!!! AST_STMT_ASSIGN IRME AST_VAR_SIMPLE with unhandeled scope: %s\n", var.scope_type);
			}

	    }
	    else if(var instanceof AST_VAR_FIELD)
		{
			System.out.print("AST_STMT_ASSIGN IRME --- CASE:  AST_VAR_FIELD\n");
			AST_VAR_FIELD fieldVar = (AST_VAR_FIELD) var;

		    //old: dst = ((AST_VAR_FIELD) var).var.IRme(); // class pointer
		    //old: IR.getInstance().Add_IRcommand(new IRcommand_ClassFieldAssign(dst, var.name, exp_temp, var.index));

			TEMP dst = fieldVar.var.IRme();
			String f_name = fieldVar.fieldName;
			String c_name = fieldVar.classN;
			IRcommand r = IR.getInstance().Add_IRcommand(new IRcommand_field_set(dst, f_name, exp_temp, GetOffset(c_name + "_" + f_name)));
			if (fieldVar.var instanceof AST_VAR_SIMPLE) {
				((AST_VAR_SIMPLE) fieldVar.var).cfgVar = true;
			}
	    } 
	    else
	    {
			System.out.print("AST_STMT_ASSIGN IRME --- CASE:  AST_VAR_SUBSCRIPT\n");

			AST_VAR_SUBSCRIPT subVar = (AST_VAR_SUBSCRIPT) var;
			TEMP array = subVar.var.IRme();
			TEMP index = subVar.subscript.IRme();
			IR.getInstance().Add_IRcommand(new IRcommand_array_set(array, index, exp_temp));
	    }
	    return null;
	}
}
