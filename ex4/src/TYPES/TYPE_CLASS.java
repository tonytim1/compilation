package TYPES;

import AST.*;

public class TYPE_CLASS extends TYPE {
	/*********************************************************************/
	/* If this class does not extend a father class this should be null */
	/*********************************************************************/
	public TYPE_CLASS father;

	/**************************************************/
	/* Gather up all data members in one place */
	/* Note that data members coming from the AST are */
	/* packed together with the class methods */
	/**************************************************/
	public AST_ARG_LIST fields;
	public AST_FUNC_LIST functions;

	/****************/
	/* CTROR(S) ... */
	/****************/
	public TYPE_CLASS(TYPE_CLASS father, String name, AST_ARG_LIST fields, AST_FUNC_LIST functions) {
		this.name = name;
		this.father = father;
		this.fields = fields;
		this.functions = functions;
		printClass();
	}

	public void printClass() {
		System.out.println("---------------------------");
		System.out.println("Class name is " + name);
		if (father != null) {
			System.out.println("extends class " + father.name);
		} else {
			System.out.println("extends no other class");
		}

		System.out.println("> class feilds are: ");
		if (fields != null)
			fields.printArgList();
		System.out.println("> class functions are: ");
		for (AST_FUNC_LIST it = functions; it != null; it = it.tail) {
			System.out.print(it.head.name + " ");
		}
		System.out.println("---------------------------");
	}

	public static void printFunc(TYPE_FUNCTION f) {
		System.out.print("\n" + f.returnType.name + " ");
		System.out.print(f.name + " ");
		for (TYPE_LIST it = f.params; it != null; it = it.tail) {
			System.out.print(it.head.name + " ");
		}
		System.out.println(" \n");
	}

	public boolean isClass() {
		return true;
	}

}
