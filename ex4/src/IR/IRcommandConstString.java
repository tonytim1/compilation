/***********/
/* PACKAGE */
/***********/
package IR;

/*******************/
/* GENERAL IMPORTS */
/*******************/

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TEMP.*;
import MIPS.*;

public class IRcommandConstString extends IRcommand  {
    TEMP t;
    String value;

    public IRcommandConstString(TEMP t, String value)
    {
        this.t = t;
        this.value = value;
    }

    public void MIPSme() {
		String str_name = getFreshLabel(value);
		MIPSGenerator.getInstance().allocate_string(str_name,value); // Allocating constant string on the data segment
		MIPSGenerator.getInstance().text_segment(); // Back to text segment
		MIPSGenerator.getInstance().la(t.toString(),str_name); // Loads the address of the string to register
	}
}