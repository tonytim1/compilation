package IR;


import TEMP.*;
import MIPS.*;

public class IRcommand_Store_Local extends IRcommand{
	public TEMP src;
	public String var;

	public IRcommand_Store_Local(String var, TEMP src) {
		this.src = src;
		this.var = var;
	}

	public void MIPSme() {
		System.out.println("IRcommand_Store_Local" + "- MIPSme");
		MIPSGenerator.getInstance().store_local(src, offset);
	}
}