package AST;

import TYPES.*;
import TEMP.*;

public class AST_DEC_LIST extends AST_Node {
	public AST_DEC head;
	public AST_DEC_LIST tail;

	public AST_DEC_LIST(AST_DEC head, AST_DEC_LIST tail) {
		this.head = head;
		this.tail = tail;
		SerialNumber = AST_Node_Serial_Number.getFresh();
		if (tail != null) {
			System.out.print("=============== decs -> dec decs\n");
			}
		if (tail == null) {
			System.out.print("=============== decs -> dec      \n");
			}


	}

	

	public TYPE SemantMe() {
		if (head != null) {
			head.SemantMe();
		}
		if (tail != null) {
			tail.SemantMe();
		}
		return null;
	}

	public TEMP IRme() {

		if (head != null)
			head.IRme();
		if (tail != null)
			tail.IRme();

		return null;
	}
}
