package TYPES;

public abstract class TYPE
{
	/******************************/
	/*  Every type has a name ... */
	/******************************/
	public String name;
	public String typeName;

	/*************/
	/* isClass() */
	/*************/
	public boolean isClass(){ return false;}

	/*************/
	/* isArray() */
	/*************/
	public boolean isArray(){ return false;}

	//TODO - isInteger
}
