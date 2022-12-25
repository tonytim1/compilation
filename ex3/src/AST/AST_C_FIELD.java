package AST;
import TYPES.*;
import SYMBOL_TABLE.*;

public abstract class AST_C_FIELD extends AST_Node {
	/*********************************************************/
	/* The default message for an unknown AST c field node */
	/*********************************************************/
	public void PrintMe()
	{
		System.out.print("UNKNOWN AST C FIELD NODE");
	}
}
