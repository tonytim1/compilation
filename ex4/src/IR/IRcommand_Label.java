package IR;


import TEMP.*;
import MIPS.*;

public class IRcommand_Label extends IRcommand {
	public String labelName;

	public IRcommand_Label(String labelName) {
		this.labelName = labelName;
	}

	public void MIPSme() {
		MIPSGenerator.getInstance().label(labelName);
	}
}
