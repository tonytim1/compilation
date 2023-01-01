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
		if (!typeName.equals(t.typeName)) {
			return false;
		}
		if (typeName == "class") {
			TYPE_CLASS class1 = (TYPE_CLASS) this;
			TYPE_CLASS class2 = (TYPE_CLASS) t;
			boolean isOk = false;
			if (class1.name != class2.name) {
				// check if class1 is a father of class2 and then it's ok
				TYPE_CLASS father = class2.father;
				while (father != null) {
					if (father.name == class1.name) {
						isOk = true;
					}
					father = father.father;
				}
				if (!isOk) {
					System.out.format(">> can't assign class %s to class %s - class canAssign\n",class2.name, class1.name);
					return false;
				}
			}
		}

		if (typeName == "array") {
			TYPE_ARRAY array1 = (TYPE_ARRAY) this;
			TYPE_ARRAY array2 = (TYPE_ARRAY) t;
			if (array2.arrayName == null) {
				return true; // it can be in a case Array a := new int[];
			}
			return array1.arrayName.equals(array2.arrayName);
		}
		return true;
	}

	public boolean canCompare(TYPE t)
	{
		return (canAssign(t) || t.canAssign(this));
	}
}
