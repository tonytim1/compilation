package AST;
import TYPES.*;
import SYMBOL_TABLE.*;

public abstract class AST_STMT extends AST_Node
{
	/*********************************************************/
	/* The default message for an unknown AST statement node */
	/*********************************************************/
	public AST_STMT(int lineNumber){
        super(lineNumber);
    }
	public void PrintMe()
	{
		System.out.print("UNKNOWN AST STATEMENT NODE");
	}
    public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{
		return null;
	}
}
