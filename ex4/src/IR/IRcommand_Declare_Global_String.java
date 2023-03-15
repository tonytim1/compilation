package IR;


import TEMP.*;
import MIPS.*;

public class IRcommand_Declare_Global_String extends IRcommand_Assign_Non_Temp {
		
	public IRcommand_Declare_Global_String(String id, String value) {
		this.id = id;
		this.var = value;
		UpdateIRName("IRcommand_Assign_Non_Temp");
	}

	/***************/
	/* MIPS me !!! */
	/***************/
	public void MIPSme() {
		System.out.println("IRcommand_Declare_Global_String" + " - MIPSme");
		String label = IRcommand.getFreshLabel("const_string");
		MIPSGenerator.getInstance().declare_global_string(label, id, var);

	}
}
