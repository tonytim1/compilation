package TYPES;

public class TYPE_FOR_SCOPE_BOUNDARIES extends TYPE
{
	/****************/
	/* CTROR(S) ... */
	/****************/
	public TYPE_FOR_SCOPE_BOUNDARIES(String name)
	{
		this.name = name;
	}

	public TYPE clone() {
		TYPE_FOR_SCOPE_BOUNDARIES newType = new TYPE_FOR_SCOPE_BOUNDARIES(this.name);
		newType.name = name;
		newType.typeName = typeName;
		newType.varName = varName;
		return newType;
	}
}
