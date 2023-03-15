package AST;

import TYPES.TYPE_CLASS;

public abstract class AST_CLASS_DEC extends AST_Node {

	public String id;
	public TYPE_CLASS father;
	public AST_CFEILD_LIST data_members;

}
