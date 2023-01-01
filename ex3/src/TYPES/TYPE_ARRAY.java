package TYPES;

public class TYPE_ARRAY extends TYPE
{
	/****************/
	/* DATA MEMBERS */
	/****************/
	public TYPE type;
	public String arrayName;

	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public TYPE_ARRAY(TYPE type)
	{
		this.type = type;
		this.typeName = "array";
	}

	public TYPE_ARRAY(TYPE type, String arrayName)
	{
		this.type = type;
		this.typeName = "array";
		this.arrayName = arrayName;
	}

	public TYPE clone() {
		TYPE_ARRAY newType = new TYPE_ARRAY(this.type);
		newType.name = name;
		newType.typeName = typeName;
		newType.varName = varName;
		newType.type = type;
		newType.arrayName = arrayName;
		return newType;
	}
}
