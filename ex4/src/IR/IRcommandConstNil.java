package IR;
import TEMP.*;
import MIPS.*;

public class IRcommandConstNil extends IRcommand {

	TEMP t;

	public IRcommandConstNil(TEMP t) {
	    this.t = t;
    }

    public void MIPSme() {
        MIPSGenerator.getInstance().mov(t.toString(),"$zero");
    }
}