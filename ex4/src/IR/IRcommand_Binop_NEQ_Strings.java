/***********/
/* PACKAGE */
/***********/
package IR;

/*******************/
/* GENERAL IMPORTS */
/*******************/

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TEMP.*;
import MIPS.*;

public class IRcommand_Binop_NEQ_Strings extends IRcommand_Binop {
	//public TEMP t1;
	//public TEMP t2;
	//public TEMP dst;

	public IRcommand_Binop_NEQ_Strings(TEMP dst, TEMP t1, TEMP t2) {
		this.dst = dst;
		this.t1 = t1;
		this.t2 = t2;
		changeName("IRcommand_Binop");
	}

	/***************/
	/* MIPS me !!! */
	/***************/
	public void MIPSme() {
		System.out.println("IRcommand_Binop_NEQ_Strings" + "- MIPSme");

		/*******************************/
		/* [1] Allocate 3 fresh labels */
		/*******************************/
		String label_end = getFreshLabel("end");
		String label_AssignOne = getFreshLabel("AssignOne");
		String label_AssignZero = getFreshLabel("AssignZero");

		MIPSGenerator.getInstance().bne(t1, t2, label_AssignOne);
		MIPSGenerator.getInstance().beq(t1, t2, label_AssignZero);

		MIPSGenerator.getInstance().label(label_AssignOne);
		MIPSGenerator.getInstance().li(dst, 1);
		MIPSGenerator.getInstance().jump(label_end);

		MIPSGenerator.getInstance().label(label_AssignZero);
		MIPSGenerator.getInstance().li(dst, 0);
		MIPSGenerator.getInstance().jump(label_end);

		MIPSGenerator.getInstance().label(label_end);
	}
}
