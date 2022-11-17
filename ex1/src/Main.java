	
import java.io.*;
import java.io.PrintWriter;

import java_cup.runtime.Symbol;


public class Main
{
	static public void main(String argv[])
	{
		Lexer l;
		Symbol s;
		FileReader file_reader;
		PrintWriter file_writer;
		String inputFilename = argv[0];
		String outputFilename = argv[1];
		boolean firstTokenConsole = true;
		boolean firstTokenFile = true;
		int i = 0;

		//TODO - Remove comments
		String [] tokenNames = {
  		"EOF", "PLUS", "MINUS", "TIMES", "DIVIDE", "LPAREN", "RPAREN", "LBRACK", "RBRACK", "LBRACE", "RBRACE", "NIL", "COMMA", "DOT", "SEMICOLON", "TYPE_INT",
  		"TYPE_VOID", "TYPE_STRING", "ASSIGN", "EQ", "LT", "GT", "ARRAY", "CLASS", "EXTENDS", "RETURN", "WHILE", "IF", "NEW", "INT", "STRING", "ID", "ERROR"
		};


		try
		{
			/********************************/
			/* [1] Initialize a file reader */
			/********************************/
			file_reader = new FileReader(inputFilename);

			/********************************/
			/* [2] Initialize a file writer */
			/********************************/
			file_writer = new PrintWriter(outputFilename);

			/******************************/
			/* [3] Initialize a new lexer */
			/******************************/
			l = new Lexer(file_reader);

			/***********************/
			/* [4] Read next token */
			/***********************/
			s = l.next_token();

			/********************************/
			/* [5] Main reading tokens loop */
			/********************************/
			while (s.sym != TokenNames.EOF)
			{
				/************************/
				/* [6] Print to console */
				/************************/
				/*
				if (firstTokenConsole == false) {
					System.out.print("\n");
				}
				firstTokenConsole = false;
				System.out.print(tokenNames[s.sym]);
				if (29 <= s.sym && s.sym <= 31) { // In case of ID, String and Int print (value)
					System.out.print("(");
					System.out.print(s.value);
					System.out.print(")");
				}
				System.out.print("[");
				System.out.print(l.getLine());
				System.out.print(",");
				System.out.print(l.getTokenStartPosition());
				System.out.print("]");
				*/
				/*********************/
				/* [7] Print to file */
				/*********************/
				if (firstTokenFile == false) {
					file_writer.print("\n");
				}
				firstTokenFile = false;
				file_writer.print(tokenNames[s.sym]);
				if (s.sym == 32) { //INVALID SYMBOL ERROR CASE
					file_writer.close();
					file_writer = new PrintWriter(outputFilename);
					file_writer.print("ERROR");
					break;
				}

				if (29 <= s.sym && s.sym <= 31) { // In case of ID, String and Int print (value)
					if (29 == s.sym) { //In case int not in range throw Exception
						try {
							i = (int)s.value;
						}
						catch (Exception e) {
							file_writer.close();
							file_writer = new PrintWriter(outputFilename);
							file_writer.print("ERROR");
							break;
						}
						if ((i < 0) || (i > 32767)) {
							file_writer.close();
							file_writer = new PrintWriter(outputFilename);
							file_writer.print("ERROR");
							break;
						}
					}

					file_writer.print("(");
					file_writer.print(s.value);
					file_writer.print(")");
				}


				file_writer.print("[");
				file_writer.print(l.getLine());
				file_writer.print(",");
				file_writer.print(l.getTokenStartPosition());
				file_writer.print("]");
				/***********************/
				/* [8] Read next token */
				/***********************/
				s = l.next_token();
			}

			/******************************/
			/* [9] Close lexer input file */
			/******************************/
			l.yyclose();

			/**************************/
			/* [10] Close output file */
			/**************************/
			file_writer.close();
			
    	}

		// In case of error - If it's possible write to file error else just print to screen.
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

