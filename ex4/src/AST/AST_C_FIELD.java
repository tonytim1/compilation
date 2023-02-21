package AST;
import TYPES.*;
import SYMBOL_TABLE.*;

public abstract class AST_C_FIELD extends AST_Node {
	/*********************************************************/
	/* The default message for an unknown AST c field node */
	/*********************************************************/
	public AST_C_FIELD(int lineNumber){
        super(lineNumber);
    }
	public void PrintMe()
	{
		System.out.print("UNKNOWN AST C FIELD NODE");
	}
	public TYPE SemantMe() throws SEMANTIC_EXCEPTION 
	{ 
		return null;
	}
}
