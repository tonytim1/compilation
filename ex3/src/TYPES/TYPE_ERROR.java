package TYPES;

public class TYPE_ERROR extends TYPE
{
	/****************/
	/* DATA MEMBERS */
	/****************/
	public int lineNumber;

	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public TYPE_ERROR(int lineNumber)
	{
		this.lineNumber = lineNumber;
		this.typeName = "error";
	}
}
