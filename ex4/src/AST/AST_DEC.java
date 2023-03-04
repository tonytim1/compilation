package AST;
import TYPES.*;
import SYMBOL_TABLE.*;
import IR.*;
import TEMP.*;

public abstract class AST_DEC extends AST_Node {
	/*********************************************************/
	/* The default message for an unknown AST Dec node */
	/*********************************************************/
	public AST_DEC(int lineNumber){
        super(lineNumber);
    }
	public void PrintMe()
	{
		System.out.print("UNKNOWN AST DEC NODE");
	}
    public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{
		return null;
	}
	public TEMP IRme(){
	    return null;
	}
}
