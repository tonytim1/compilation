package TYPES;

public class TYPE_STRING extends TYPE
{
	/**************************************/
	/* USUAL SINGLETON IMPLEMENTATION ... */
	/**************************************/
	private static TYPE_STRING instance = null;

	/*****************************/
	/* PREVENT INSTANTIATION ... */
	/*****************************/
	public TYPE_STRING() {
		this.name = "string";
		this.typeName = "string";
	}

	public TYPE clone() {
		TYPE_STRING newType = new TYPE_STRING();
		newType.name = name;
		newType.typeName = typeName;
		newType.varName = varName;
		return newType;
	}

	/******************************/
	/* GET SINGLETON INSTANCE ... */
	/******************************/
	public static TYPE_STRING getInstance()
	{
		if (instance == null)
		{
			instance = new TYPE_STRING();
			instance.name = "string";
			instance.typeName = "string";
		}
		return instance;
	}
}
