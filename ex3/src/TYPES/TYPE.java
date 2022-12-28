package TYPES;

public abstract class TYPE
{
	/******************************/
	/*  Every type has a name ... */
	/******************************/
	public String name;
	public String typeName;
	public String varName;

	public TYPE clone() { return this; }


	public boolean canAssign(TYPE t)
	{
		if (t.typeName.equals("nil"))
		{
			return (typeName.equals("array") || typeName.equals("class"));
		}
		return typeName.equals(t.typeName);
	}

	public boolean canCompare(TYPE t)
	{
		return (canAssign(t) || t.canAssign(this));
	}
}
