/***********/
/* PACKAGE */
/***********/
package IR;
import TYPES.*;
import TEMP.*;
import MIPS.*;
import REGALLOC.*;

/*******************/
/* GENERAL IMPORTS */
/*******************/

/*******************/
/* PROJECT IMPORTS */
/*******************/

public class IRcommand_Allocate extends IRcommand
{
	String scope_type;
	String var_name = null;
	int var_value_word = 0;
	String var_value_string = null;

	
	public IRcommand_Allocate(String var_name)
	{
		this.var_name = var_name;
	}
	public IRcommand_Allocate(String scope_type,String var_name,int var_value)
	{
		this(scope_type);
		this.var_name = var_name;
		this.var_value_word = var_value;
	}
	public IRcommand_Allocate(String scope_type,String var_name,String var_value)
	{
		this(scope_type);
		this.var_name = var_name;
		this.var_value_string = var_value;
	}
	
	/***************/
	/* MIPS me !!! */
	/***************/
	public void MIPSme()
	{
		if(scope_type.equals("global")){
			if(var_value_string != null){
				MIPSGenerator.getInstance().allocate_string(String.format("global_%s",var_name),var_value_string);
			} else {
				MIPSGenerator.getInstance().allocate(var_name,var_value_word);
			}
		}
	}
}
