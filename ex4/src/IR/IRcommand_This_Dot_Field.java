package IR;


import TEMP.*;
import MIPS.*;

public class IRcommand_This_Dot_Field extends IRcommand {
	public TEMP dst;
	public boolean regalloc;
	String name;

		public IRcommand_This_Dot_Field(String Fname, TEMP dst) {
	    this.regalloc = false;
		this.dst = dst;
		this.name = Fname;

	}

	public void MIPSme() {
		System.out.println("IRcommand_This_Dot_Field" + "- MIPSme");
		MIPSGenerator.getInstance().load_field_in_func(dst, offset);
	}
}