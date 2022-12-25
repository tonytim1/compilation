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

	public TYPE findInClass(String name)
	{
		for (TYPE_CLASS_VAR_DEC_LIST member=data_members; member!=null; member=member.tail){
			if (member.head.name.equals(name)){
				return member.head.t;
			}
		}

		if (this.father != null)
			return this.father.findInClassScope(name);
			
		return null;
	}
}
