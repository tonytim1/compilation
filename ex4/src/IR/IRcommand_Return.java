package IR;


import TEMP.*;
import MIPS.*;
public class IRcommand_Return extends IRcommand {
	public TEMP RetVal;

	public IRcommand_Return(TEMP retVal) {
		this.RetVal = retVal;
	}

	public void MIPSme() {
		MIPSGenerator.getInstance().ret(RetVal);
	}
}
