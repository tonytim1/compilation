package TYPES;

public class TYPE_FUNCTION extends TYPE
{
	/***********************************/
	/* The return type of the function */
	/***********************************/
	public TYPE returnType;

	/*************************/
	/* types of input params */
	/*************************/
	public TYPE_LIST params;
	
	/****************/
	/* CTROR(S) ... */
	/****************/
	public TYPE_FUNCTION(TYPE returnType,String name,TYPE_LIST params)
	{
	    this.typeName = "function";
		this.name = name;
		this.returnType = returnType;
		this.params = params;
	}

	public TYPE clone() {
		TYPE_FUNCTION newType = new TYPE_FUNCTION(this.returnType, this.name, this.params);
		newType.name = name;
		newType.typeName = typeName;
		newType.varName = varName;
		return newType;
	}

	public boolean isSignatureEqual(TYPE_FUNCTION other){
		if (!(returnType.typeName.equals(other.returnType.typeName))) {
			return false;
		}

		// TYPE_LIST p1 = this.params;
		// TYPE_LIST p2 = other.params;
		
		// while(p1 != null && p2 != null){
		// 	if(!(p1.head.canAssign(p2.head)))
		// 		return false;

		// 	p1 = p1.tail;
		// 	p2 = p2.tail;
			
		// }
		if ((this.params == null) && (other.params == null)) {
			return true;
		}
		return this.params.canAssignList(other.params);
	}
}
