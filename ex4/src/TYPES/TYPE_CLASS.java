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
			//if (member.typeName != "function" && ((TYPE_CLASS_VAR_DEC) member.head).varName.equals(id))
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

	public String getClassNameOfFunc(String func_name)
	{
		for (TYPE_LIST member=data_members; member!=null; member=member.tail){
			if (member.head != null && member.head.typeName == "function" && member.head.name.equals(name))
				return this.name;
		}
		return this.father.getClassNameOfFunc(func_name);
	}

	public int getMallocSize()
	{
		return 4 * (this.getFieldList().size() + 1);
	}

	public List<String> getFuncList()
	{
		List<String> func_list;
		if (father != null)
		{
			func_list = father.getFuncList();
		}
		else
		{
			func_list = new LinkedList<String>();
		}

		for (TYPE_LIST member=data_members; member!=null; member=member.tail){
			if (member.head != null && member.head.typeName == "function" && !func_list.contains(member.head.name))
			{
				func_list.add(member.head.name);
			}
		}

		return func_list;
	}

	public LinkedList<String> getFieldList()
	{
		LinkedList<String> field_list;
		if (father != null)
		{
			field_list = father.getFuncList();
		}
		else
		{
			field_list = new LinkedList<String>();
		}

		for (TYPE_LIST member=data_members; member!=null; member=member.tail){
			if (member.head != null && member.head.typeName != "function" && !field_list.contains(member.head.varName))
			{
				field_list.add(member.head.varName);
			}
		}

		return field_list;
	}

	public int getFieldIndex(String field_name)
	{
		return this.getFieldList().indexOf(field_name);
	}

	public int getFuncIndex(String func_name)
	{
		return this.getFuncList().indexOf(func_name);
	}
	//For IR part
	public boolean existInFatherScope(String name){
		if(this.father != null){
			TYPE_CLASS fatherClass = this.father;
			for (TYPE_CLASS_VAR_DEC_LIST e=fatherClass.data_members;e!=null;e=e.tail){
				if (e.head.name.equals(name)){
					return true;
				}
			}
			if (fatherClass.father != null)
				return fatherClass.father.existInFatherScope(name);
		}
		return false;
	}
}
