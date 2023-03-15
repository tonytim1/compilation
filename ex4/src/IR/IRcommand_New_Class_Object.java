package IR;

import java.util.ArrayList;
import AST.AST_Node;
import MIPS.MIPSGenerator;
import TEMP.*;

public class IRcommand_New_Class_Object extends IRcommand_Assign {
	public String className;
	
		public IRcommand_New_Class_Object(TEMP t1, String className) {
		this.dst = t1;
		this.className = className;
	}

	public void MIPSme() {
	    int curr_offset, size;
	    String currName;

		size = AST_Node.getClassSize(className);
		MIPSGenerator.getInstance().new_class(dst, className, size);
		ArrayList<String> fields = AST_Node.classfields.get(className);

		for (String field : fields) {
			currName = className + "_" + field;
			curr_offset = AST_Node.GetOffset(currName);
			MIPSGenerator.getInstance().set_field_default(dst, currName, curr_offset);
		}
	}
}