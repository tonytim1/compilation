package AST;
import TYPES.*;
import SYMBOL_TABLE.*;
import IR.*;
import TEMP.*;

public abstract class AST_VAR extends AST_Node
{
	// For IR	
	public String name; // label name of the variable
	public String scope_type; // global local or param
	public int index; // index of the variable for the offset calculation

	public AST_VAR(int lineNumber){
        super(lineNumber);
    }

	public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{
		return null;
	}
	public void PrintMe()
	{
	}
	public TEMP IRme() {
	    return null;
	}
}
