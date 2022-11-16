/***************************/
/* FILE NAME: LEX_FILE.lex */
/***************************/

/*************/
/* USER CODE */
/*************/
import java_cup.runtime.*;

/******************************/
/* DOLAR DOLAR - DON'T TOUCH! */
/******************************/

%%

/************************************/
/* OPTIONS AND DECLARATIONS SECTION */
/************************************/

/*****************************************************/
/* Lexer is the name of the class JFlex will create. */
/* The code will be written to the file Lexer.java.  */
/*****************************************************/
%class Lexer

/********************************************************************/
/* The current line number can be accessed with the variable yyline */
/* and the current column number with the variable yycolumn.        */
/********************************************************************/
%line
%column

/*******************************************************************************/
/* Note that this has to be the EXACT same name of the class the CUP generates */
/*******************************************************************************/
%cupsym TokenNames

/******************************************************************/
/* CUP compatibility mode interfaces with a CUP generated parser. */
/******************************************************************/
%cup

/****************/
/* DECLARATIONS */
/****************/
/*****************************************************************************/
/* Code between %{ and %}, both of which must be at the beginning of a line, */
/* will be copied verbatim (letter to letter) into the Lexer class code.     */
/* Here you declare member variables and functions that are used inside the  */
/* scanner actions.                                                          */
/*****************************************************************************/
%{
	/*********************************************************************************/
	/* Create a new java_cup.runtime.Symbol with information about the current token */
	/*********************************************************************************/
	private Symbol symbol(int type)               {return new Symbol(type, yyline, yycolumn);}
	private Symbol symbol(int type, Object value) {return new Symbol(type, yyline, yycolumn, value);}

	/*******************************************/
	/* Enable line number extraction from main */
	/*******************************************/
	public int getLine() { return yyline + 1; }

	/**********************************************/
	/* Enable token position extraction from main */
	/**********************************************/
	public int getTokenStartPosition() { return yycolumn + 1; }
%}

/***********************/
/* MACRO DECALARATIONS */
/***********************/
LineTerminator	= \r|\n|\r\n
WhiteSpace		= {LineTerminator} | [ \t]
INTEGER			= 0 | [1-9][0-9]*
ID				= [a-zA-Z][a-zA-Z0-9]*
STRING          = \"[a-zA-Z]*\"
TABLE2 = [a-zA-Z0-9\(\)\[\]\{\}\?\!\+\-\*\/\.\; \t]
COMMENT_1 = \/\/{TABLE2}*{LineTerminator}
COMMENT_2 = \/\*({TABLE2} | {LineTerminator})*\*\/

/******************************/
/* DOLAR DOLAR - DON'T TOUCH! */
/******************************/

%%

/************************************************************/
/* LEXER matches regular expressions to actions (Java code) */
/************************************************************/

/**************************************************************/
/* YYINITIAL is the state at which the lexer begins scanning. */
/* So these regular expressions will only be matched if the   */
/* scanner is in the start state YYINITIAL.                   */
/**************************************************************/

<YYINITIAL> {

"("	    	   	        { return symbol(TokenNames.LPAREN, new String(yytext()));}
")"				{ return symbol(TokenNames.RPAREN, new String(yytext()));}
"["				{ return symbol(TokenNames.LBRACK, new String(yytext()));}
"]"				{ return symbol(TokenNames.RBRACK, new String(yytext()));}
"{"				{ return symbol(TokenNames.LBRACE, new String(yytext()));}
"}"				{ return symbol(TokenNames.RBRACE, new String(yytext()));}
"nil"				{ return symbol(TokenNames.NIL, new String(yytext()));}
"+"				{ return symbol(TokenNames.PLUS, new String(yytext()));}
"-"				{ return symbol(TokenNames.MINUS, new String(yytext()));}
"*" 				{ return symbol(TokenNames.TIMES, new String(yytext()));}
"/"				{ return symbol(TokenNames.DIVIDE, new String(yytext()));}
","				{ return symbol(TokenNames.COMMA, new String(yytext()));}
"."				{ return symbol(TokenNames.DOT, new String(yytext()));}
";"				{ return symbol(TokenNames.SEMICOLON, new String(yytext()));}
"int"				{ return symbol(TokenNames.TYPE_INT, new String(yytext()));}
"void"				{ return symbol(TokenNames.TYPE_VOID, new String(yytext()));}
"string"			{ return symbol(TokenNames.TYPE_STRING, new String(yytext()));}
":="				{ return symbol(TokenNames.ASSIGN, new String(yytext()));}
"="				{ return symbol(TokenNames.EQ, new String(yytext()));}
"<"				{ return symbol(TokenNames.LT, new String(yytext()));}
">"				{ return symbol(TokenNames.GT, new String(yytext()));}
"array"				{ return symbol(TokenNames.ARRAY, new String(yytext()));}
"class"				{ return symbol(TokenNames.CLASS, new String(yytext()));}
"extends"			{ return symbol(TokenNames.EXTENDS, new String(yytext()));}
"return"			{ return symbol(TokenNames.RETURN, new String(yytext()));}
"while"				{ return symbol(TokenNames.WHILE, new String(yytext()));}
"if"				{ return symbol(TokenNames.IF, new String(yytext()));}
"new"				{ return symbol(TokenNames.NEW, new String(yytext()));}
{INTEGER}			{ return symbol(TokenNames.INT, new Integer(yytext()));}
{ID}				{ return symbol(TokenNames.ID, new String( yytext()));}
{STRING}			{ return symbol(TokenNames.STRING, new String(yytext()));}
{WhiteSpace}			{ /* just skip what was found, do nothing */ }
{COMMENT_1} 			{return symbol(TokenNames.COMMENT_1, new String( yytext()));}
{COMMENT_2}	    		{return symbol(TokenNames.COMMENT_2, new String( yytext()));}
<<EOF>>				{ return symbol(TokenNames.EOF, new String(yytext()));}

}
