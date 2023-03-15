package AST;
import TYPES.*;
import TEMP.*;

public class AST_STMT_VAR_ID extends AST_STMT {
  public AST_VAR var;
  public String id;
  public TYPE_CLASS tl;

  /*******************/
  /* CONSTRUCTOR(S) */
  /*******************/
  public AST_STMT_VAR_ID(AST_VAR var, String id) {
    this.var = var;
    this.id = id;
    /******************************/
    /* SET A UNIQUE SERIAL NUMBER */
    /******************************/
    SerialNumber = AST_Node_Serial_Number.getFresh();

  }

  /****************** outside CONSTRUCTOR code *******************/

  /*************************************************/
  /* The printing message for a XXX node */
  /*************************************************/
  
  public TYPE SemantMe() {
		///a.g(); or a.g(a,b);
		System.out.println("STMT VARDOT - semant me");
		TYPE t1 = var.SemantMe();
		if (t1 == null || !(t1 instanceof TYPE_CLASS)) //not a class
		{
			System.out.println(">> ERROR ["+line+"] var.dot is of wrong class");
			printError(line);
		}
    tl = (TYPE_CLASS)t1;

		TYPE t2 = isFuncOfClass(t1.name,id,null,this.line);
		if (t2 == null)
		{
			System.out.println(">> ERROR ["+line+"] var.dot is wrong");
			printError(line);
		}
		
		return t2;
		
	}

  public TEMP IRme()
  {
    System.out.println("STMT VARDOT - IRme");
    return vardotIR(var, null, tl, id);
  }
}