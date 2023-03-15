package AST;
import TYPES.*;

public class AST_TYPE_NAME extends AST_Node {
	/****************/
	/* DATA MEMBERS */
	/****************/
	public TYPE type;
	public String name;

	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_TYPE_NAME(TYPE type, String name) {
		this.type = type;
		this.name = name;
		this.typeName = type.name;
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

	}

	/*************************************************/
	/* The printing message for a type name AST node */
	/*************************************************/
	

	/*****************/
	/* SEMANT ME ... */
	/*****************/

}
