package AST;
import TYPES.*;
import SYMBOL_TABLE.*;
import IR.*;
import TEMP.*;

public abstract class AST_C_FIELD extends AST_Node {
	/*********************************************************/
	/* The default message for an unknown AST c field node */
	/*********************************************************/

	public String typeCField; //new for IR

	public AST_C_FIELD(int lineNumber){
        super(lineNumber);
        self.typeCField = null;
    }
	public void PrintMe()
	{
		System.out.print("UNKNOWN AST C FIELD NODE");
	}
	public TYPE SemantMe() throws SEMANTIC_EXCEPTION 
	{ 
		return null;
	}
	public TEMP IRme()
	{
	    return null;
	}
}
