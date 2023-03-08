package AST;
import TYPES.*;
import SYMBOL_TABLE.*;
import IR.*;
import TEMP.*;


public abstract class AST_EXP extends AST_Node
{
	public int moish;

	public AST_EXP(int lineNumber){
        super(lineNumber);
    }

    public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{
		return null;
	}

	public TEMP IRme() {
	    return null;
	}
}