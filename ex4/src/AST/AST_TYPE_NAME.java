package AST;
import TYPES.*;

public class AST_TYPE_NAME extends AST_Node {
	public TYPE type;
	public String name;

	public AST_TYPE_NAME(TYPE type, String name) {
		this.type = type;
		this.name = name;
		this.typeName = type.name;
		SerialNumber = AST_Node_Serial_Number.getFresh();

	}

	


}
