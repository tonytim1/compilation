package TYPES;

public abstract class TYPE
{
	/******************************/
	/*  Every type has a name ... */
	/******************************/
	public String name;
	public boolean isArray(){ return false;}
	public boolean isFunc(){ return false;}
	public boolean isClass(){ return false;}
}
