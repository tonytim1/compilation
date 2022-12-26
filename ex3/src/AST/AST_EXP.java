package AST;
import TYPES.*;
import SYMBOL_TABLE.*;

public abstract class AST_EXP extends AST_Node
{
	public int moish;

	public AST_EXP(int lineNumber){
        super(lineNumber);
    }

    public TYPE SemantMe()
	{
		return null;
	}
}