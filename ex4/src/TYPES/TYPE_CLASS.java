package TYPES;

import AST.*;

public class TYPE_CLASS extends TYPE {
	public TYPE_CLASS father;

	public AST_ARG_LIST dataMembers;
	public AST_TYPE_NAME_LIST functions;

	public TYPE_CLASS(TYPE_CLASS father, String name, AST_ARG_LIST dataMembers, AST_TYPE_NAME_LIST funcs) {
		this.name = name;
		this.father = father;
		this.dataMembers = dataMembers;
		this.functions = funcs;
	}

	public boolean isClass() {
		return true;
	}

}
