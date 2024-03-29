package TYPES;

public class TYPE_INT extends TYPE
{
    public boolean isZero;
    public boolean isNegative;
	/**************************************/
	/* USUAL SINGLETON IMPLEMENTATION ... */
	/**************************************/
	private static TYPE_INT instance = null;
	private static TYPE_INT instanceZero = null;

	/*****************************/
	/* PREVENT INSTANTIATION ... */
	/*****************************/
	public TYPE_INT() {
	    this.isZero = false;
		this.name = "int";
		this.typeName = "int";
	}

	public TYPE_INT(boolean isZero) {
	    this.isZero = isZero;
		this.name = "int";
		this.typeName = "int";
	}

	public TYPE_INT(boolean isZero, boolean isNegative) {
		this.isZero = isZero;
		this.isNegative = isNegative;
		this.name = "int";
		this.typeName = "int";
	}

	public TYPE clone() {
		TYPE_INT newType = new TYPE_INT(this.isZero);
		newType.name = name;
		newType.typeName = typeName;
		newType.varName = varName;
		return newType;
	}

	/******************************/
	/* GET SINGLETON INSTANCE ... */
	/******************************/
	public static TYPE_INT getInstance()
	{
		if (instance == null)
		{
			instance = new TYPE_INT();
			instance.name = "int";
			instance.typeName = "int";
		}
		return instance;
	}

	public static TYPE_INT getInstanceZero()
	{
		if (instanceZero == null)
		{
			instanceZero = new TYPE_INT(true);
			instanceZero.name = "int";
			instanceZero.typeName = "int";
		}
		return instanceZero;
	}
}
