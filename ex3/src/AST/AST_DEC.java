package AST;

public abstract class AST_DEC extends AST_Node {
	/*********************************************************/
	/* The default message for an unknown AST Dec node */
	/*********************************************************/
	public void PrintMe()
	{
		System.out.print("UNKNOWN AST DEC NODE");
	}
    public TYPE SemantMe()
	{
		return null;
	}
}
