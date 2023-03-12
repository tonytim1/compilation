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
    public TEMP_LIST getLiveTemp(TEMP_LIST input){
        TEMP_LIST result = input.clone();
        result.remove(t);
        return result;
    }
}