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
		return null;
	}

	public TEMP IRme(){
		TEMP exp_temp = exp.IRme();
		TEMP dst;
		if(var instanceof AST_VAR_SIMPLE)
		{
			System.out.print("AST_STMT_ASSIGN IRME --- CASE:  AST_VAR_SIMPLE\n");

		    //IR.getInstance().Add_IRcommand(new IRcommand_Store(var.name, exp_temp ,var.scope_type ,var.index));
			
			if (var.scope_type == "global") {
				IR.getInstance().Add_IRcommand(new IRcommand_Store_Global(exp_temp, var.name));
			}
			else if (var.scope_type == "local_class") {
				String varName = inclass + "_" + ((AST_VAR_SIMPLE) var).name;
				IRcommand c = new IRcommand_store_field(inclass, varName, value);
				c.offset = GetOffset(varName);
				IR.getInstance().Add_IRcommand(c);
			}

	    }
	    else if(var instanceof AST_VAR_FIELD)
		{
			System.out.print("AST_STMT_ASSIGN IRME --- CASE:  AST_VAR_FIELD\n");

		    dst = ((AST_VAR_FIELD) var).var.IRme(); // class pointer
		    IR.getInstance().Add_IRcommand(new IRcommand_ClassFieldAssign(dst, var.name, exp_temp, var.index));
	    } 
	    else
	    {
			System.out.print("AST_STMT_ASSIGN IRME --- CASE:  AST_VAR_SUBSCRIPT\n");

		    dst = ((AST_VAR_SUBSCRIPT) var).var.IRme(); 
		    TEMP index = ((AST_VAR_SUBSCRIPT) var).subscript.IRme();
		    IR.getInstance().Add_IRcommand(new IRcommand_ArraySet(dst, index, exp_temp));
	    }
	    return null;
	}
}
