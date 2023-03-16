package IR;


import TEMP.*;
import MIPS.*;

public class IRcommand_Binop_LT_Integers extends IRcommand_Binop {

	public IRcommand_Binop_LT_Integers(TEMP dst, TEMP t1, TEMP t2) {
		this.dst = dst;
		this.t1 = t1;
		this.t2 = t2;
		UpdateIRName("IRcommand_Binop");
	}

	public void MIPSme() {

		String label_end = getFreshLabel("end");
		String label_assign_one = getFreshLabel("assign_one");
		String label_assign_zero = getFreshLabel("assign_zero");

		MIPSGenerator.getInstance().blt(t1, t2, label_assign_one);
		MIPSGenerator.getInstance().bge(t1, t2, label_assign_zero);
		MIPSGenerator.getInstance().label(label_assign_one);
		MIPSGenerator.getInstance().li(dst, 1);
		MIPSGenerator.getInstance().jump(label_end);
		MIPSGenerator.getInstance().label(label_assign_zero);
		MIPSGenerator.getInstance().li(dst, 0);
		MIPSGenerator.getInstance().jump(label_end);
		MIPSGenerator.getInstance().label(label_end);
	}
}
