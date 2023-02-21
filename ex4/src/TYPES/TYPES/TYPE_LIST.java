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

	public boolean canAssignList(TYPE_LIST other){
		TYPE_LIST p1 = this;
		TYPE_LIST p2 = other;
		System.out.format("comparing params of 2 lists \n");
		while(p1 != null && p2 != null){
			System.out.format("    comparing params: %s, %s\n", p1.head.typeName, p2.head.typeName);
			if(!(p1.head.canAssign(p2.head)))
				return false;

			p1 = p1.tail;
			p2 = p2.tail;
			
		}
		return (p1 == null && p2 == null);
	}
}
