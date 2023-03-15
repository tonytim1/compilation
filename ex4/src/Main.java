
import java.io.*;
import java.io.PrintWriter;
import java_cup.runtime.Symbol;
import AST.*;
import REG_ALLOC.*;
import IR.*;
import MIPS.*;

public class Main {
	static public void main(String argv[]) {
		Lexer l;
		Parser p;
		Symbol s;
		AST_PROGRAM AST;
		FileReader file_reader;
		PrintWriter file_writer;
		String inputFilename = argv[0];
		String outputFilename = argv[1];

		try {
			file_reader = new FileReader(inputFilename);

			file_writer = new PrintWriter(outputFilename);

			l = new Lexer(file_reader);

			p = new Parser(l, outputFilename);

			AST = (AST_PROGRAM) p.parse().value;

			AST.SemantMe();

			AST.IRme();

			REG_ALLOC g=new REG_ALLOC();
			g.liveOpt();
			g.allocationRegisters();
			IR.getInstance().MIPSme();

			AST_GRAPHVIZ.getInstance().finalizeFile();

			MIPSGenerator.getInstance().finalizeFile();

			file_writer.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
