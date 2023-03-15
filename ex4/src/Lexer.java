

import java_cup.runtime.*;



class Lexer implements java_cup.runtime.Scanner {

  public static final int YYEOF = -1;

  private static final int ZZ_BUFFERSIZE = 16384;

  public static final int YYINITIAL = 0;

  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\3\1\2\1\45\1\3\1\1\22\0\1\3\1\31\1\21"+
    "\5\0\1\22\1\23\1\36\1\32\1\40\1\33\1\34\1\37\1\4"+
    "\1\13\1\7\1\6\2\5\1\11\1\10\2\12\1\43\1\35\1\44"+
    "\1\41\1\42\1\30\1\0\32\14\1\26\1\0\1\27\3\0\1\17"+
    "\1\14\1\15\1\51\1\56\1\63\1\64\1\62\1\50\2\14\1\16"+
    "\1\14\1\52\1\47\2\14\1\54\1\20\1\53\1\60\1\46\1\61"+
    "\1\57\1\55\1\14\1\24\1\0\1\25\7\0\1\45\u1fa2\0\1\45"+
    "\1\45\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\udfe6\0";

  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\2\2\4\3\4\4\1\1\1\5\1\6"+
    "\1\7\1\10\1\11\1\12\1\1\1\13\1\14\1\15"+
    "\1\16\1\17\1\20\1\21\1\22\1\23\1\1\1\24"+
    "\6\4\1\1\3\3\3\4\1\0\1\25\1\26\1\1"+
    "\1\0\1\27\2\4\1\30\5\4\3\3\3\4\4\0"+
    "\1\4\1\31\1\32\1\33\3\4\3\3\3\4\2\0"+
    "\1\1\1\34\3\4\1\0\1\35\1\36\3\4\1\37"+
    "\1\40\1\41\1\4\1\42";

  private static int [] zzUnpackAction() {
    int [] result = new int[99];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;    int j = offset;    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\65\0\152\0\65\0\237\0\324\0\u0109\0\u013e"+
    "\0\u0173\0\u01a8\0\u01dd\0\u0212\0\u0247\0\65\0\65\0\65"+
    "\0\65\0\65\0\65\0\u027c\0\65\0\65\0\65\0\65"+
    "\0\65\0\u02b1\0\65\0\65\0\65\0\u02e6\0\65\0\u031b"+
    "\0\u0350\0\u0385\0\u03ba\0\u03ef\0\u0424\0\237\0\u0459\0\u048e"+
    "\0\u04c3\0\u04f8\0\u052d\0\u0562\0\u0247\0\65\0\65\0\u0597"+
    "\0\u05cc\0\65\0\u0601\0\u0636\0\u0173\0\u066b\0\u06a0\0\u06d5"+
    "\0\u070a\0\u073f\0\u0774\0\u07a9\0\u07de\0\u0813\0\u0848\0\u087d"+
    "\0\u0597\0\u08b2\0\u08e7\0\u091c\0\u0951\0\u0173\0\u0173\0\u0173"+
    "\0\u0986\0\u09bb\0\u09f0\0\u0a25\0\u0a5a\0\u0a8f\0\u0ac4\0\u0af9"+
    "\0\u0b2e\0\u0b63\0\u0b98\0\u0bcd\0\u0173\0\u0c02\0\u0c37\0\u0c6c"+
    "\0\237\0\u0173\0\u0173\0\u0ca1\0\u0cd6\0\u0d0b\0\u0173\0\u0173"+
    "\0\u0173\0\u0d40\0\u0173";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[99];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;    int j = offset;    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\2\4\1\5\1\6\1\7\1\10\3\6"+
    "\1\10\1\11\1\12\1\11\1\13\1\14\1\15\1\16"+
    "\1\17\1\20\1\21\1\22\1\23\1\2\1\24\1\25"+
    "\1\26\1\27\1\30\1\31\1\32\1\33\1\34\1\35"+
    "\1\36\1\37\1\0\1\40\1\11\1\41\1\11\1\42"+
    "\1\11\1\43\1\11\1\44\2\11\1\45\3\11\67\0"+
    "\1\4\66\0\10\46\55\0\10\47\55\0\1\50\2\47"+
    "\1\51\3\47\1\50\55\0\10\50\55\0\15\11\25\0"+
    "\17\11\4\0\12\11\1\52\2\11\25\0\17\11\4\0"+
    "\15\11\25\0\6\11\1\53\10\11\4\0\15\11\25\0"+
    "\5\11\1\54\11\11\14\0\5\55\1\56\24\0\17\55"+
    "\41\0\1\57\61\0\1\60\1\61\66\0\1\62\27\0"+
    "\15\11\25\0\1\11\1\63\15\11\4\0\15\11\25\0"+
    "\4\11\1\64\10\11\1\65\1\11\4\0\15\11\25\0"+
    "\2\11\1\66\5\11\1\67\6\11\4\0\15\11\25\0"+
    "\10\11\1\70\6\11\4\0\15\11\25\0\11\11\1\71"+
    "\5\11\4\0\15\11\25\0\14\11\1\72\2\11\4\0"+
    "\10\73\55\0\10\74\55\0\4\74\1\75\1\74\1\73"+
    "\1\74\55\0\13\11\1\76\1\11\25\0\17\11\4\0"+
    "\15\11\25\0\6\11\1\77\10\11\4\0\15\11\25\0"+
    "\6\11\1\100\10\11\1\0\20\101\1\102\14\101\1\103"+
    "\1\101\5\102\1\0\17\101\1\0\1\3\1\4\16\61"+
    "\1\104\16\61\5\104\1\0\17\61\4\0\15\11\25\0"+
    "\2\11\1\105\14\11\4\0\15\11\25\0\5\11\1\106"+
    "\11\11\4\0\12\11\1\107\2\11\25\0\17\11\4\0"+
    "\15\11\25\0\13\11\1\110\3\11\4\0\15\11\25\0"+
    "\5\11\1\111\11\11\4\0\15\11\25\0\5\11\1\112"+
    "\11\11\4\0\15\11\25\0\2\11\1\113\14\11\4\0"+
    "\10\114\55\0\10\115\55\0\4\115\1\114\1\116\1\114"+
    "\1\115\55\0\14\11\1\117\25\0\17\11\4\0\13\11"+
    "\1\120\1\11\25\0\17\11\4\0\15\11\25\0\2\11"+
    "\1\121\14\11\1\0\20\122\1\102\14\122\1\123\1\122"+
    "\5\102\1\0\17\122\1\0\20\101\1\0\14\101\1\103"+
    "\1\4\6\0\17\101\1\0\1\124\1\2\42\104\1\0"+
    "\17\104\4\0\15\11\25\0\3\11\1\125\13\11\4\0"+
    "\15\11\25\0\12\11\1\126\4\11\4\0\15\11\25\0"+
    "\10\11\1\127\6\11\4\0\12\11\1\130\2\11\25\0"+
    "\17\11\4\0\10\131\55\0\10\5\55\0\6\5\1\46"+
    "\1\5\55\0\14\11\1\132\25\0\17\11\4\0\15\11"+
    "\25\0\7\11\1\133\7\11\4\0\15\11\25\0\4\11"+
    "\1\134\12\11\1\0\20\122\1\0\14\122\1\123\1\122"+
    "\6\0\17\122\1\0\20\122\1\0\14\122\1\123\1\2"+
    "\6\0\17\122\2\0\1\2\66\0\15\11\25\0\6\11"+
    "\1\135\10\11\4\0\15\11\25\0\4\11\1\136\12\11"+
    "\4\0\15\11\25\0\10\11\1\137\6\11\4\0\15\11"+
    "\25\0\16\11\1\140\4\0\15\11\25\0\4\11\1\141"+
    "\12\11\4\0\15\11\25\0\3\11\1\142\13\11\4\0"+
    "\14\11\1\143\25\0\17\11";

  private static int [] zzUnpackTrans() {
    int [] result = new int[3445];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;    int j = offset;    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\1\1\1\11\11\1\6\11\1\1\5\11"+
    "\1\1\3\11\1\1\1\11\15\1\1\0\2\11\1\1"+
    "\1\0\1\11\16\1\4\0\15\1\2\0\5\1\1\0"+
    "\12\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[99];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;    int j = offset;    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  private java.io.Reader zzReader;

  private int zzState;

  private int zzLexicalState = YYINITIAL;

  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  private int zzMarkedPos;

  private int zzCurrentPos;

  private int zzStartRead;

  private int zzEndRead;

  private int yyline;

  private int yychar;

  private int yycolumn;

  private boolean zzAtBOL = true;

  private boolean zzAtEOF;

  private boolean zzEOFDone;
  
  private int zzFinalHighSurrogate = 0;

	private Symbol symbol(int type)               {return new Symbol(type, yyline, yycolumn);}
	private Symbol symbol(int type, Object value) {return new Symbol(type, yyline, yycolumn, value);}

	public int getLine() { return yyline + 1; } 

	public int getTokenStartPosition() { return yycolumn + 1; }
	public int getCharPos() { return yycolumn; } 
 


  Lexer(java.io.Reader in) {
    this.zzReader = in;
  }


  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;    int j = 0;    while (i < 176) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  private boolean zzRefill() throws java.io.IOException {

    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      return false;
    }

    return true;
  }

    
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;    zzEndRead = zzStartRead;
    if (zzReader != null)
      zzReader.close();
  }


  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  public final int yystate() {
    return zzLexicalState;
  }


  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

        int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':          case '\u000C':          case '\u0085':          case '\u2028':          case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
                boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

            int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
                        zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
                        zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

            zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
            switch (zzLexicalState) {
            case YYINITIAL: {
              return symbol(TokenNames.EOF);
            }              case 100: break;
            default:
          { return new java_cup.runtime.Symbol(TokenNames.EOF); }
        }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { return symbol(TokenNames.ERROR);
            } 
                      case 35: break;
          case 2: 
            {            } 
                      case 36: break;
          case 3: 
            { return symbol(TokenNames.INT,  new Integer(yytext()));
            } 
                      case 37: break;
          case 4: 
            { return symbol(TokenNames.ID,  new String( yytext()));
            } 
                      case 38: break;
          case 5: 
            { return symbol(TokenNames.LPAREN);
            } 
                      case 39: break;
          case 6: 
            { return symbol(TokenNames.RPAREN);
            } 
                      case 40: break;
          case 7: 
            { return symbol(TokenNames.LBRACE);
            } 
                      case 41: break;
          case 8: 
            { return symbol(TokenNames.RBRACE);
            } 
                      case 42: break;
          case 9: 
            { return symbol(TokenNames.LBRACK);
            } 
                      case 43: break;
          case 10: 
            { return symbol(TokenNames.RBRACK);
            } 
                      case 44: break;
          case 11: 
            { return symbol(TokenNames.PLUS);
            } 
                      case 45: break;
          case 12: 
            { return symbol(TokenNames.MINUS);
            } 
                      case 46: break;
          case 13: 
            { return symbol(TokenNames.DOT);
            } 
                      case 47: break;
          case 14: 
            { return symbol(TokenNames.SEMICOLON);
            } 
                      case 48: break;
          case 15: 
            { return symbol(TokenNames.TIMES);
            } 
                      case 49: break;
          case 16: 
            { return symbol(TokenNames.DIVIDE);
            } 
                      case 50: break;
          case 17: 
            { return symbol(TokenNames.COMMA);
            } 
                      case 51: break;
          case 18: 
            { return symbol(TokenNames.EQ);
            } 
                      case 52: break;
          case 19: 
            { return symbol(TokenNames.GT);
            } 
                      case 53: break;
          case 20: 
            { return symbol(TokenNames.LT);
            } 
                      case 54: break;
          case 21: 
            { return symbol(TokenNames.STRING,  new String( yytext()));
            } 
                      case 55: break;
          case 22: 
            { return symbol(TokenNames.NE);
            } 
                      case 56: break;
          case 23: 
            { return symbol(TokenNames.ASSIGN);
            } 
                      case 57: break;
          case 24: 
            { return symbol(TokenNames.IF);
            } 
                      case 58: break;
          case 25: 
            { return symbol(TokenNames.TYPE_INT);
            } 
                      case 59: break;
          case 26: 
            { return symbol(TokenNames.NIL);
            } 
                      case 60: break;
          case 27: 
            { return symbol(TokenNames.NEW);
            } 
                      case 61: break;
          case 28: 
            { return symbol(TokenNames.TYPE_VOID);
            } 
                      case 62: break;
          case 29: 
            { return symbol(TokenNames.CLASS);
            } 
                      case 63: break;
          case 30: 
            { return symbol(TokenNames.ARRAY);
            } 
                      case 64: break;
          case 31: 
            { return symbol(TokenNames.WHILE);
            } 
                      case 65: break;
          case 32: 
            { return symbol(TokenNames.TYPE_STRING);
            } 
                      case 66: break;
          case 33: 
            { return symbol(TokenNames.RETURN);
            } 
                      case 67: break;
          case 34: 
            { return symbol(TokenNames.EXTENDS);
            } 
                      case 68: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
