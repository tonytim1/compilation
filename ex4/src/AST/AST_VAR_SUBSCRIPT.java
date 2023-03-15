package AST;

import IR.*;
import TEMP.*;
import TYPES.*;

public class AST_VAR_SUBSCRIPT extends AST_VAR {
	public AST_VAR var;
	public AST_EXP subscript;

	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_VAR_SUBSCRIPT(AST_VAR var, AST_EXP subscript, int line) {
		this.var = var;
		this.subscript = subscript;
		this.line = line;
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.print("====================== var -> var [ exp ]\n");

		/*******************************/
		/* COPY INPUT DATA NENBERS ... */
		/*******************************/
	}

	/*****************************************************/
	/* The printing message for a subscript var AST node */
	/*****************************************************/
	

	public TYPE SemantMe() {
		System.out.println("VAR SUBSCRIPT - semant me");
		TYPE t1 = var.SemantMe();

		if (t1 == null) {
			System.out.format(">> ERROR [%d] var is not declared. (var_subscript)\n", line);
			printError(line);
		}

		if (!(t1.isArray())) {
			System.out.println(">> ERROR [" + line + "] var is not an array");
			printError(line);
		}

		TYPE t2 = subscript.SemantMe();
		if (!(t2.name.equals("int"))) {
			System.out.println(">> ERROR [" + line + "] array index is not int");
			printError(line);
		}

		return ((TYPE_ARRAY) t1).entryType;
	}

	public TEMP IRme() {
		System.out.println("VAR SUBSCRIPT- IRme");

		TEMP t1 = var.IRme();
		TEMP t2 = subscript.IRme();
		TEMP t3 = TEMP_FACTORY.getInstance().getFreshTEMP();
		IR.getInstance().Add_IRcommand(new IRcommand_Array_Access(t3, t1, t2));
		return t3;
	}
}
