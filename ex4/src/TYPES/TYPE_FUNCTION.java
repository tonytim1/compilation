package TYPES;

public class TYPE_FUNCTION extends TYPE {
	public TYPE returnType;

	public TYPE_LIST params;

	public String startLabel;
	public TYPE_FUNCTION(TYPE returnType, String name, TYPE_LIST params) {
		this.name = name;
		this.returnType = returnType;
		this.params = params;
	}

	public boolean isFunc() {
		return true;
	}
}
