package TYPES;

public class TYPE_VOID extends TYPE
{
	/**************************************/
	/* USUAL SINGLETON IMPLEMENTATION ... */
	/**************************************/
	private static TYPE_VOID instance = null;

	/*****************************/
	/* PREVENT INSTANTIATION ... */
	/*****************************/
	public TYPE_VOID() {
		this.typeName = "void";
	}

	public TYPE clone() {
		TYPE_VOID newType = new TYPE_VOID();
		newType.name = name;
		newType.typeName = typeName;
		newType.varName = varName;
		return newType;
	}

	/******************************/
	/* GET SINGLETON INSTANCE ... */
	/******************************/
	public static TYPE_VOID getInstance()
	{
		if (instance == null)
		{
			instance = new TYPE_VOID();
			instance.typeName = "void";
		}
		return instance;
	}
}
