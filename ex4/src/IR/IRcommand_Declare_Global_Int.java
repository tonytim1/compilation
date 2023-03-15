package IR;


import TEMP.*;
import MIPS.*;

public class IRcommand_Declare_Global_Int extends IRcommand_Assign_Non_Temp {
		int value;

	public IRcommand_Declare_Global_Int(String id, int value) {
		this.id = id;
		this.value = value;
		UpdateIRName("IRcommand_Assign_Non_Temp");
	}

	/***************/
	/* MIPS me !!! */
	/***************/
	public void MIPSme() {
		System.out.println("IRcommand_Declare_Global_Int" + " - MIPSme");
		MIPSGenerator.getInstance().declare_global_int(id, value);

	}
}
