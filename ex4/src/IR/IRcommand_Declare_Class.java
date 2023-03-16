package IR;

import MIPS.*;
import java.util.ArrayList;

public class IRcommand_Declare_Class extends IRcommand {
    String className;
    ArrayList<ArrayList<String>> functions;

    public IRcommand_Declare_Class(String name, ArrayList<ArrayList<String>> functions) {
        this.className = name;
        this.functions = functions;
    }

    public void MIPSme() {
        MIPSGenerator.getInstance().create_new_class(className, functions);
    }
}