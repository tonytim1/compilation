package IR;
import TYPES.*;
import TEMP.*;
import MIPS.*;
import REGALLOC.*;

public class IRcommand_ConstNil extends IRcommand {

	TEMP t;

	public IRcommand_ConstNil(TEMP t) {
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