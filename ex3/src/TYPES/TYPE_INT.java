package TYPES;

public class TYPE_INT extends TYPE
{
    public boolean isZero;
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
	}

	public TYPE_INT(boolean isZero) {
	    this.isZero = isZero;
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
