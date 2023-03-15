package IR;


import TEMP.*;
import MIPS.*;

public class IRcommand_Const_Int extends IRcommand_Assign {
	public int val;

	public IRcommand_Const_Int(TEMP t, int val) {
		this.dst = t;
		this.val = val;
		UpdateIRName("IRcommand_Assign");
	}

	public void MIPSme() {
		MIPSGenerator.getInstance().li(dst, val);
	}
}
