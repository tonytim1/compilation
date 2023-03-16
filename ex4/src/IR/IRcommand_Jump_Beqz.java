package IR;


import TEMP.*;
import MIPS.*;

public class IRcommand_Jump_Beqz extends IRcommand_Conditional_Jump {
	public IRcommand_Jump_Beqz(TEMP t, String label) {
		this.op1 = t;
		this.label = label;
		UpdateIRName("IRcommand_Conditional_Jump");
	}

	public void MIPSme() {
				MIPSGenerator.getInstance().beqz(op1, label);
	}
}
