package AST;
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_VAR_FIELD extends AST_VAR
{
	public AST_VAR var;
	public String name;
	
	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_VAR_FIELD(int lineNumber, AST_VAR var,String name)
	{
		super(lineNumber);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.format("====================== var -> var DOT ID( %s )\n",name);

		/*******************************/
		/* COPY INPUT DATA NENBERS ... */
		/*******************************/
		this.var = var;
		this.name = name;
	}
	public void PrintMe()
	{
		System.out.format("AST VAR FIELD %s\n", name);
	}

	public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{
		TYPE t = null;
		TYPE_CLASS tc = null;

		/******************************/
		/* [1] Recursively semant var */
		/******************************/
		if (var != null) {
		    t = var.SemantMe();
        }

		/*********************************/
		/* [2] Make sure type is a class */
		/*********************************/
		if (t.typeName != "class")
		{
			System.out.format(">> ERROR [%d] access %s field of a non-class variable - class AST_VAR_FIELD\n",lineNumber,name);
			throw new SEMANTIC_EXCEPTION(lineNumber);
		}
		else
		{
			System.out.format("[%d] var name %s tc %s %s\n", lineNumber, name, t.name, t.typeName);
			tc = (TYPE_CLASS) t;
		}

		/************************************/
		/* [3] Look for fiedlName inside tc or its parents */
		/************************************/
		TYPE member = tc.findClassVar(name);

		if (member != null)
			return member;

		/*********************************************/
		/* [4] name does not exist in class var */
		/*********************************************/
		System.out.format(">> ERROR [%d] field %s does not exist in class - AST_VAR_FIELD\n",lineNumber,name);
		throw new SEMANTIC_EXCEPTION(lineNumber);
	}
}
