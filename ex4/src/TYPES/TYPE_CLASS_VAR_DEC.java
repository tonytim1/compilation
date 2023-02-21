package TYPES;

public class TYPE_CLASS_VAR_DEC extends TYPE
{
	public String varName;
	public TYPE type;
	
	public TYPE_CLASS_VAR_DEC(TYPE t,String varName)
	{
		this.type = t;
		this.name = t.name;
		this.typeName = t.typeName;
		this.varName = varName;
	}

	public TYPE clone() {
		TYPE_CLASS_VAR_DEC newType = new TYPE_CLASS_VAR_DEC(this.type, this.varName);
		newType.name = name;
		newType.typeName = typeName;
		newType.varName = varName;
		return newType;
	}
}
