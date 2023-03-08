package AST;
import TYPES.*;
import SYMBOL_TABLE.*;
import IR.*;
import TEMP.*;


public class AST_TYPE extends AST_Node {

	public int t;
	public String id;

	public AST_TYPE(int lineNumber, int t) {
		super(lineNumber);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
		
		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		switch(t) {
		case 1: id = "TYPE_INT"; break;
		case 2: id = "TYPE_STRING"; break;
		case 3: id = "TYPE_VOID"; break;
		}
		System.out.format("====================== type -> %s \n", id);
		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.t = t;
		this.id = id;
	}
	
	public AST_TYPE(int lineNumber, String id) {
		super(lineNumber);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
		
		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.format("====================== type -> ID( %s )\n", id);
		
		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.t = 4;
		this.id = id;
	}
	
	public void PrintMe()
	{
		/************************************/
		/* AST NODE TYPE = TYPE NODE */
		/************************************/
		System.out.format("AST NODE TYPE ( %s )\n", id);
		
		/*********************************/
		/* Print to AST GRAPHIZ DOT file */
		/*********************************/
		AST_GRAPHVIZ.getInstance().logNode(
			SerialNumber,
			"TYPE\n");
	}

	public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{
		SYMBOL_TABLE s = SYMBOL_TABLE.getInstance();
		System.out.format("AST_TYPE attempting to semantme type %d %s\n", t, id);
		switch(t){
			case 1: return new TYPE_INT();
			case 2: return new TYPE_STRING();
			case 3: return new TYPE_VOID();
			case 4: 
				TYPE type = s.find(id);
				if(type == null)
				{
					/**************************/
					/* ERROR: undeclared type */
					/**************************/
					System.out.format(">> ERROR [%d] undeclared type for %s - class AST_TYPE\n",lineNumber, id);
					throw new SEMANTIC_EXCEPTION(lineNumber);
				}
				else
				{
					return type.clone();
				}
		}
        return null;
	}
}