package TYPES;

public class TYPE_LIST extends TYPE
{
	/****************/
	/* DATA MEMBERS */
	/****************/
	public TYPE head;
	public TYPE_LIST tail;

	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public TYPE_LIST(TYPE head,TYPE_LIST tail)
	{
	    this.typeName = "list";
		this.head = head;
		this.tail = tail;
	}

	public TYPE clone() {
		TYPE_LIST newType = new TYPE_LIST(this.head, this.tail);
		newType.name = name;
		newType.typeName = typeName;
		newType.varName = varName;
		return newType;
	}

	public void printList() {
		System.out.format("print list %s\n", this.varName);
		TYPE_LIST currList = this;
		while (currList.head != null) {
			System.out.format(" - %s, %s: %s\n", currList.head.varName, currList.head.typeName, currList.head);
			if (currList.tail == null) {
				break;
			}
			currList = currList.tail;
		}
	}
}
