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

	public TYPE clone() {
		TYPE_ARRAY newType = new TYPE_ARRAY();
		newType.name = name;
		newType.typeName = typeName;
		newType.varName = varName;
		newType.type = type;
		return newType;
	}
}
