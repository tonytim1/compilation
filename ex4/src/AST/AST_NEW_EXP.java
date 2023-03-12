package AST;
import TYPES.*;
import SYMBOL_TABLE.*;
import IR.*;
import TEMP.*;
public class AST_NEW_EXP extends AST_Node {

	public AST_TYPE type;
	public AST_EXP exp;
	
	public AST_NEW_EXP(int lineNumber, AST_TYPE type, AST_EXP exp) {
		super(lineNumber);
		
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
		
		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		if(exp == null) System.out.print("====================== newExp -> NEW type\n");
		if(exp != null) System.out.print("====================== newExp -> NEW type RBRACK exp LBRACK\n");

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.type = type;
		this.exp = exp;
	}

	public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{
		TYPE varType = type.SemantMe();
		if (exp != null) {
			// the derivation is NEW type:t LBRACK exp:e RBRACK - so it's an array
			//check that exp is int
			TYPE expType = exp.SemantMe();
			if (expType.typeName != "int") {
				System.out.format(">> ERROR [%d] type of exp is not int and is: %s for rule NEW type:t LBRACK exp:e RBRACK - class AST_NEW_EXP\n",lineNumber,expType.typeName);
				throw new SEMANTIC_EXCEPTION(lineNumber);
			}
			TYPE_INT expInt = (TYPE_INT) expType;
			if (expInt.isZero) {
				System.out.format(">> ERROR [%d] exp is 0 for rule NEW type:t LBRACK exp:e RBRACK - class AST_NEW_EXP\n",lineNumber);
				throw new SEMANTIC_EXCEPTION(lineNumber);
			}
			return new TYPE_ARRAY(varType);
		}

		return varType;
	}

    public TEMP IRme(){
		TEMP dst = TEMP_FACTORY.getInstance().getFreshTemp();
		if(exp != null){
			TEMP size = exp.IRme();
			IR.getInstance().Add_IRcommand(new IRcommand_ArrayNew(dst, size));
		} else {
			// Exp -> null than type -> Identifier
			IR.getInstance().Add_IRcommand(new IRcommand_ClassNew(dst, type.id));
		}
		return dst;
    }
}
