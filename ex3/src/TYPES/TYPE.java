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
		if ((typeName == "class") && (t.typeName == "class")) {
			TYPE_CLASS class1 = (TYPE_CLASS) this;
			TYPE_CLASS class2 = (TYPE_CLASS) t;
			System.out.format(">> INFO [%d] Assign to %s father: %s, %s father %s - class AST_STMT_ASSIGN_NEW\n",lineNumber, class1.name, class1.father, class2.name, class2.father);
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
					System.out.format(">> ERROR [%d] can't assign class %s to class %s - class AST_VAR_DEC\n",lineNumber,class2.name, class1.name);
					throw new SEMANTIC_EXCEPTION(lineNumber + 1);
				}
			}
		}
		return typeName.equals(t.typeName);
	}

	public boolean canCompare(TYPE t)
	{
		return (canAssign(t) || t.canAssign(this));
	}
}
