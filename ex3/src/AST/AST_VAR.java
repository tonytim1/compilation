package AST;
import TYPES.*;
import SYMBOL_TABLE.*;

public abstract class AST_VAR extends AST_Node
{
	public AST_VAR(int lineNumber){
        super(lineNumber);
    }
	
	public TYPE SemantMe()
	{
		return null;
	}
}
