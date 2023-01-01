package TYPES;

public class TYPE_CLASS extends TYPE
{
	/*********************************************************************/
	/* If this class does not extend a father class this should be null  */
	/*********************************************************************/
	public TYPE_CLASS father;

	/**************************************************/
	/* Gather up all data members in one place        */
	/* Note that data members coming from the AST are */
	/* packed together with the class methods         */
	/**************************************************/
	public TYPE_LIST data_members;
	
	/****************/
	/* CTROR(S) ... */
	/****************/
	public TYPE_CLASS(TYPE_CLASS father,String name,TYPE_LIST data_members)
	{
		this.typeName = "class";
		this.name = name;
		this.father = father;
		this.data_members = data_members;
	}

	public TYPE clone() {
		TYPE_CLASS newType = new TYPE_CLASS(this.father, this.name, this.data_members);
		newType.name = name;
		newType.typeName = typeName;
		newType.varName = varName;
		return newType;
	}

	public TYPE findClassFunc(String id)
	{
		System.out.format("Looking for func %s in class %s\n",id, name);
		for (TYPE_LIST member=data_members; member!=null; member=member.tail){
			if ((member == null) || member.head == null) {
				break;
			}
			if (member.head.typeName == "function" && member.head.name.equals(id))
			{
				return member.head;
			}
		}

		if (this.father != null)
			return this.father.findClassFunc(id);
			
		return null;
	}

	public TYPE findClassVar(String id)
	{
		System.out.format("Looking for var %s in class %s\n",id, name);
		for (TYPE_LIST member=data_members; member!=null; member=member.tail){
			if ((member == null) || member.head == null) {
				break;
			}
			if (member.head.typeName != "function" && member.head.varName.equals(id))
			{
				return member.head;
			}
		}

		if (this.father != null)
			return this.father.findClassVar(id);
			
		return null;
	}

	public TYPE findinClass(String id)
	{
		TYPE result = findClassFunc(id);
		if (result != null)
		{
			return result;
		}
		return findClassVar(id);
	}

}
