package IR;
import TYPES.*;
import TEMP.*;
import MIPS.*;
import REGALLOC.*;

public class IRcommandConstNil extends IRcommand {

	TEMP t;

	public IRcommandConstNil(TEMP t) {
	    this.t = t;
    }

    public void MIPSme() {
        MIPSGenerator.getInstance().move(t.toString(),"$zero");
    }
}