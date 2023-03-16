package IR;


import TEMP.*;
import MIPS.*;

public class IRcommand_Jump_Label extends IRcommand_Conditional_Jump {
	public IRcommand_Jump_Label(String label) {
		this.label = label;
		UpdateIRName("IRcommand_Conditional_Jump");
	}
	public void MIPSme() {
		MIPSGenerator.getInstance().jump(label);
	}
}
