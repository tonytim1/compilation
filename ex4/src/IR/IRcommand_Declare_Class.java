package IR;


import TEMP.*;
import MIPS.*;
import AST.*;
import TYPES.*;

import java.util.ArrayList;
public class IRcommand_Declare_Class extends IRcommand {
	String class_name;
	ArrayList<ArrayList<String>> funcs;
	ArrayList<ArrayList<ArrayList<String>>> fs;

	public IRcommand_Declare_Class(String name, ArrayList<ArrayList<String>> functions,
			ArrayList<ArrayList<ArrayList<String>>> fs) {
		this.class_name = name;
		this.funcs = functions;
		this.fs = fs;
	}

	public void MIPSme() {
		System.out.println("IRcommand_Declare_Class" + "- MIPSme");
		MIPSGenerator.getInstance().declare_class(class_name, funcs, fs);
	}
}