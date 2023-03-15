/***********/
/* PACKAGE */
/***********/
package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;

public class AST_FUNC extends AST_Node {
	/****************/
	/* DATA MEMBERS */
	/****************/
	public TYPE type;
	public String name;

	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_FUNC(TYPE type, String name) {
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		this.type = type;
		this.name = name;
		this.typeName = type.name;
	}

	/*************************************************/
	/* The printing message for a type name AST node */
	/*************************************************/
	public void PrintMe() {
		/**************************************/
		/* AST NODE TYPE = AST TYPE NAME NODE */
		/**************************************/
		System.out.format("NAME(%s):TYPE(%s)\n", name, typeName);

		/***************************************/
		/* PRINT Node to AST GRAPHVIZ DOT file */
		/***************************************/
		AST_GRAPHVIZ.getInstance().logNode(
				SerialNumber,
				String.format("NAME:TYPE\n%s:%s", name, typeName));
	}

	/*****************/
	/* SEMANT ME ... */
	/*****************/

}
