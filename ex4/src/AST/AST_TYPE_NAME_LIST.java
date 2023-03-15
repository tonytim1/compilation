package AST;

public class AST_TYPE_NAME_LIST extends AST_Node {
	public AST_TYPE_NAME head;
	public AST_TYPE_NAME_LIST tail;

	public AST_TYPE_NAME_LIST(AST_TYPE_NAME head, AST_TYPE_NAME_LIST tail) {
		this.head = head;
		this.tail = tail;
		SerialNumber = AST_Node_Serial_Number.getFresh();

	}

	

}
