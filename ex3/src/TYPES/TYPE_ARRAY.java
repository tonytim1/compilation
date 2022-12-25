package TYPES;

public class TYPE_ARRAY extends TYPE
{
	/****************/
	/* DATA MEMBERS */
	/****************/
	public TYPE type;
	//public int size;

	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public TYPE_ARRAY(TYPE type)
	{
		this.type = type;
		this.typeName = "array";
		//this.size = size;
	}
}
