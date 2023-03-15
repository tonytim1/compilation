package IR;


import TEMP.*;
import MIPS.*;

public class IRcommand_Jump_Beq extends IRcommand_Conditional_Jump {
	public IRcommand_Jump_Beq(String label, TEMP op1, TEMP op2) {
		this.label = label;
		this.op1 = op1;
		this.op2 = op2;
		UpdateIRName("IRcommand_Conditional_Jump");
	}

	/***************/
	/* MIPS me !!! */
	/***************/
	public void MIPSme() {
				MIPSGenerator.getInstance().beq(op1, op2, label);
	}
}
