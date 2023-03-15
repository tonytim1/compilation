package IR;


import TEMP.*;
import MIPS.*;

public class IRcommand_Load_Global extends IRcommand_Assign {
	String label;

	public IRcommand_Load_Global(TEMP dst, String name) {
		this.dst = dst;
		this.label = name;
		UpdateIRName("IRcommand_Assign");
	}

	/***************/
	/* MIPS me !!! */
	/***************/
	public void MIPSme() {
		System.out.println("IRcommand_Load_Global" + "- MIPSme");
		MIPSGenerator.getInstance().load_label(dst, label);
	}
}
