package AST;

import TYPES.*;
import TEMP.*;

public class AST_STMT_LIST extends AST_Node {
	public AST_STMT head;
	public AST_STMT_LIST tail;

	public AST_STMT_LIST(AST_STMT head, AST_STMT_LIST tail) {
		this.head = head;
		this.tail = tail;
		SerialNumber = AST_Node_Serial_Number.getFresh();

		if (tail != null) {
			System.out.print("====================== stmts -> stmt stmts\n");
			}
		if (tail == null) {
			System.out.print("====================== stmts -> stmt      \n");
			}

	}

	

	public TYPE SemantMe() {
		System.out.println("STMT LIST - semant me");
		if (head != null)
			head.SemantMe();
		if (tail != null)
			tail.SemantMe();

		return null;
	}
	public TEMP IRme()
	{
		if (head != null) head.IRme();
		if (tail != null) tail.IRme();
		
		return null;
	}

}
