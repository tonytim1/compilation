package IR;


import TEMP.*;
import MIPS.*;

public class IRcommand_Binop_Mul_Integers extends IRcommand_Binop {

	public IRcommand_Binop_Mul_Integers(TEMP dst, TEMP t1, TEMP t2) {
		this.dst = dst;
		this.t1 = t1;
		this.t2 = t2;
		UpdateIRName("IRcommand_Binop");
	}

	public void MIPSme() {
		MIPSGenerator.getInstance().mul(dst, t1, t2);
	}
}
