

import java_cup.runtime.*;
import AST.*;
import java.io.*;
import java.io.PrintWriter;
import java_cup.runtime.XMLElement;

/** CUP v0.11b 20160615 (GIT 4ac7450) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class Parser extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return TokenNames.class;
}

  /** Default constructor. */
  @Deprecated
  public Parser() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public Parser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public Parser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\102\000\002\002\004\000\002\006\003\000\002\017" +
    "\004\000\002\017\003\000\002\007\003\000\002\007\003" +
    "\000\002\007\003\000\002\007\003\000\002\005\004\000" +
    "\002\005\003\000\002\021\004\000\002\021\003\000\002" +
    "\015\003\000\002\015\003\000\002\010\003\000\002\010" +
    "\003\000\002\010\003\000\002\010\003\000\002\011\011" +
    "\000\002\012\005\000\002\012\007\000\002\012\007\000" +
    "\002\013\011\000\002\013\012\000\002\025\004\000\002" +
    "\024\003\000\002\024\005\000\002\014\007\000\002\014" +
    "\011\000\002\002\003\000\002\002\005\000\002\002\003" +
    "\000\002\002\010\000\002\002\007\000\002\002\006\000" +
    "\002\002\005\000\002\002\003\000\002\002\004\000\002" +
    "\002\003\000\002\002\003\000\002\023\005\000\002\023" +
    "\003\000\002\003\003\000\002\003\005\000\002\003\006" +
    "\000\002\004\006\000\002\004\003\000\002\004\006\000" +
    "\002\004\005\000\002\004\004\000\002\004\011\000\002" +
    "\004\011\000\002\004\011\000\002\004\010\000\002\004" +
    "\007\000\002\004\006\000\002\016\004\000\002\016\007" +
    "\000\002\022\005\000\002\022\005\000\002\022\005\000" +
    "\002\022\005\000\002\022\005\000\002\022\005\000\002" +
    "\022\005\000\002\022\005" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\226\000\016\030\005\031\010\036\015\037\021\042" +
    "\020\044\013\001\002\000\004\002\230\001\002\000\010" +
    "\017\ufff3\023\ufff3\044\ufff3\001\002\000\020\002\ufffc\030" +
    "\ufffc\031\ufffc\036\ufffc\037\ufffc\042\ufffc\044\ufffc\001\002" +
    "\000\020\002\ufffe\030\005\031\010\036\015\037\021\042" +
    "\020\044\013\001\002\000\004\044\221\001\002\000\020" +
    "\002\ufffd\030\ufffd\031\ufffd\036\ufffd\037\ufffd\042\ufffd\044" +
    "\ufffd\001\002\000\004\002\000\001\002\000\010\017\ufff0" +
    "\023\ufff0\044\ufff0\001\002\000\004\044\037\001\002\000" +
    "\010\017\ufff2\023\ufff2\044\ufff2\001\002\000\020\002\ufffa" +
    "\030\ufffa\031\ufffa\036\ufffa\037\ufffa\042\ufffa\044\ufffa\001" +
    "\002\000\020\002\ufffb\030\ufffb\031\ufffb\036\ufffb\037\ufffb" +
    "\042\ufffb\044\ufffb\001\002\000\010\017\ufff1\023\ufff1\044" +
    "\ufff1\001\002\000\004\044\022\001\002\000\006\021\023" +
    "\032\024\001\002\000\012\030\005\036\015\042\020\044" +
    "\013\001\002\000\004\044\025\001\002\000\004\021\026" +
    "\001\002\000\012\030\005\036\015\042\020\044\013\001" +
    "\002\000\014\022\ufff4\030\ufff4\036\ufff4\042\ufff4\044\ufff4" +
    "\001\002\000\004\022\034\001\002\000\014\022\ufff5\030" +
    "\ufff5\036\ufff5\042\ufff5\044\ufff5\001\002\000\014\022\ufff6" +
    "\030\005\036\015\042\020\044\013\001\002\000\004\022" +
    "\ufff7\001\002\000\020\002\uffe5\030\uffe5\031\uffe5\036\uffe5" +
    "\037\uffe5\042\uffe5\044\uffe5\001\002\000\004\022\036\001" +
    "\002\000\020\002\uffe6\030\uffe6\031\uffe6\036\uffe6\037\uffe6" +
    "\042\uffe6\044\uffe6\001\002\000\010\015\041\023\040\024" +
    "\042\001\002\000\030\002\uffee\004\uffee\013\uffee\022\uffee" +
    "\030\uffee\031\uffee\033\uffee\036\uffee\037\uffee\042\uffee\044" +
    "\uffee\001\002\000\014\016\130\030\005\036\015\042\020" +
    "\044\013\001\002\000\020\011\043\015\054\034\045\035" +
    "\047\040\052\043\044\044\053\001\002\000\004\043\126" +
    "\001\002\000\032\005\uffdd\006\uffdd\010\uffdd\011\uffdd\012" +
    "\uffdd\014\uffdd\016\uffdd\020\uffdd\023\uffdd\025\uffdd\026\uffdd" +
    "\027\uffdd\001\002\000\012\030\005\036\015\042\020\044" +
    "\013\001\002\000\032\005\uffe2\006\uffe2\010\uffe2\011\uffe2" +
    "\012\uffe2\014\uffe2\016\uffe2\020\uffe2\023\uffe2\025\uffe2\026" +
    "\uffe2\027\uffe2\001\002\000\032\005\uffdb\006\uffdb\010\uffdb" +
    "\011\uffdb\012\uffdb\014\uffdb\016\uffdb\020\uffdb\023\uffdb\025" +
    "\uffdb\026\uffdb\027\uffdb\001\002\000\004\023\121\001\002" +
    "\000\024\005\071\006\075\010\070\011\066\012\074\014" +
    "\067\023\120\025\072\026\076\001\002\000\032\005\uffda" +
    "\006\uffda\010\uffda\011\uffda\012\uffda\014\uffda\016\uffda\020" +
    "\uffda\023\uffda\025\uffda\026\uffda\027\uffda\001\002\000\040" +
    "\005\uffd7\006\uffd7\007\uffd7\010\uffd7\011\uffd7\012\uffd7\014" +
    "\uffd7\015\114\016\uffd7\017\uffd7\020\uffd7\023\uffd7\025\uffd7" +
    "\026\uffd7\027\uffd7\001\002\000\016\011\043\015\054\035" +
    "\047\040\052\043\044\044\053\001\002\000\036\005\uffe4" +
    "\006\uffe4\007\057\010\uffe4\011\uffe4\012\uffe4\014\uffe4\016" +
    "\uffe4\017\056\020\uffe4\023\uffe4\025\uffe4\026\uffe4\027\uffe4" +
    "\001\002\000\016\011\043\015\054\035\047\040\052\043" +
    "\044\044\053\001\002\000\004\044\060\001\002\000\040" +
    "\005\uffd6\006\uffd6\007\uffd6\010\uffd6\011\uffd6\012\uffd6\014" +
    "\uffd6\015\061\016\uffd6\017\uffd6\020\uffd6\023\uffd6\025\uffd6" +
    "\026\uffd6\027\uffd6\001\002\000\020\011\043\015\054\016" +
    "\063\035\047\040\052\043\044\044\053\001\002\000\026" +
    "\005\071\006\075\010\070\011\066\012\074\014\067\016" +
    "\uffd8\025\072\026\076\027\073\001\002\000\032\005\uffe0" +
    "\006\uffe0\010\uffe0\011\uffe0\012\uffe0\014\uffe0\016\uffe0\020" +
    "\uffe0\023\uffe0\025\uffe0\026\uffe0\027\uffe0\001\002\000\004" +
    "\016\065\001\002\000\032\005\uffe1\006\uffe1\010\uffe1\011" +
    "\uffe1\012\uffe1\014\uffe1\016\uffe1\020\uffe1\023\uffe1\025\uffe1" +
    "\026\uffe1\027\uffe1\001\002\000\016\011\043\015\054\035" +
    "\047\040\052\043\044\044\053\001\002\000\016\011\043" +
    "\015\054\035\047\040\052\043\044\044\053\001\002\000" +
    "\016\011\043\015\054\035\047\040\052\043\044\044\053" +
    "\001\002\000\016\011\043\015\054\035\047\040\052\043" +
    "\044\044\053\001\002\000\016\011\043\015\054\035\047" +
    "\040\052\043\044\044\053\001\002\000\016\011\043\015" +
    "\054\035\047\040\052\043\044\044\053\001\002\000\016" +
    "\011\043\015\054\035\047\040\052\043\044\044\053\001" +
    "\002\000\016\011\043\015\054\035\047\040\052\043\044" +
    "\044\053\001\002\000\016\011\043\015\054\035\047\040" +
    "\052\043\044\044\053\001\002\000\032\005\uffc2\006\uffc2" +
    "\010\070\011\066\012\074\014\067\016\uffc2\020\uffc2\023" +
    "\uffc2\025\uffc2\026\uffc2\027\uffc2\001\002\000\032\005\uffc0" +
    "\006\uffc0\010\070\011\066\012\074\014\067\016\uffc0\020" +
    "\uffc0\023\uffc0\025\072\026\076\027\uffc0\001\002\000\032" +
    "\005\uffc5\006\uffc5\010\uffc5\011\uffc5\012\uffc5\014\uffc5\016" +
    "\uffc5\020\uffc5\023\uffc5\025\uffc5\026\uffc5\027\uffc5\001\002" +
    "\000\004\016\uffd9\001\002\000\032\005\uffc3\006\uffc3\010" +
    "\070\011\066\012\074\014\067\016\uffc3\020\uffc3\023\uffc3" +
    "\025\uffc3\026\uffc3\027\uffc3\001\002\000\032\005\uffc1\006" +
    "\uffc1\010\070\011\066\012\074\014\067\016\uffc1\020\uffc1" +
    "\023\uffc1\025\072\026\076\027\uffc1\001\002\000\032\005" +
    "\uffc7\006\uffc7\010\uffc7\011\uffc7\012\074\014\067\016\uffc7" +
    "\020\uffc7\023\uffc7\025\uffc7\026\uffc7\027\uffc7\001\002\000" +
    "\032\005\uffc4\006\uffc4\010\uffc4\011\uffc4\012\uffc4\014\uffc4" +
    "\016\uffc4\020\uffc4\023\uffc4\025\uffc4\026\uffc4\027\uffc4\001" +
    "\002\000\032\005\uffc6\006\uffc6\010\uffc6\011\uffc6\012\074" +
    "\014\067\016\uffc6\020\uffc6\023\uffc6\025\uffc6\026\uffc6\027" +
    "\uffc6\001\002\000\024\005\071\006\075\010\070\011\066" +
    "\012\074\014\067\020\111\025\072\026\076\001\002\000" +
    "\040\005\uffd5\006\uffd5\007\uffd5\010\uffd5\011\uffd5\012\uffd5" +
    "\014\uffd5\016\uffd5\017\uffd5\020\uffd5\023\uffd5\024\uffd5\025" +
    "\uffd5\026\uffd5\027\uffd5\001\002\000\024\005\071\006\075" +
    "\010\070\011\066\012\074\014\067\016\113\025\072\026" +
    "\076\001\002\000\032\005\uffe3\006\uffe3\010\uffe3\011\uffe3" +
    "\012\uffe3\014\uffe3\016\uffe3\020\uffe3\023\uffe3\025\uffe3\026" +
    "\uffe3\027\uffe3\001\002\000\020\011\043\015\054\016\115" +
    "\035\047\040\052\043\044\044\053\001\002\000\032\005" +
    "\uffde\006\uffde\010\uffde\011\uffde\012\uffde\014\uffde\016\uffde" +
    "\020\uffde\023\uffde\025\uffde\026\uffde\027\uffde\001\002\000" +
    "\004\016\117\001\002\000\032\005\uffdf\006\uffdf\010\uffdf" +
    "\011\uffdf\012\uffdf\014\uffdf\016\uffdf\020\uffdf\023\uffdf\025" +
    "\uffdf\026\uffdf\027\uffdf\001\002\000\030\002\uffed\004\uffed" +
    "\013\uffed\022\uffed\030\uffed\031\uffed\033\uffed\036\uffed\037" +
    "\uffed\042\uffed\044\uffed\001\002\000\030\002\uffec\004\uffec" +
    "\013\uffec\022\uffec\030\uffec\031\uffec\033\uffec\036\uffec\037" +
    "\uffec\042\uffec\044\uffec\001\002\000\006\017\123\023\uffc9" +
    "\001\002\000\016\011\043\015\054\035\047\040\052\043" +
    "\044\044\053\001\002\000\024\005\071\006\075\010\070" +
    "\011\066\012\074\014\067\020\125\025\072\026\076\001" +
    "\002\000\004\023\uffc8\001\002\000\032\005\uffdc\006\uffdc" +
    "\010\uffdc\011\uffdc\012\uffdc\014\uffdc\016\uffdc\020\uffdc\023" +
    "\uffdc\025\uffdc\026\uffdc\027\uffdc\001\002\000\006\016\uffe8" +
    "\027\217\001\002\000\004\021\214\001\002\000\004\016" +
    "\134\001\002\000\004\044\133\001\002\000\006\016\uffe9" +
    "\027\uffe9\001\002\000\004\021\135\001\002\000\020\004" +
    "\142\013\136\030\005\033\145\036\015\042\020\044\143" +
    "\001\002\000\004\015\206\001\002\000\022\004\142\013" +
    "\136\022\ufff8\030\005\033\145\036\015\042\020\044\143" +
    "\001\002\000\022\004\uffd3\013\uffd3\022\uffd3\030\uffd3\033" +
    "\uffd3\036\uffd3\042\uffd3\044\uffd3\001\002\000\004\022\204" +
    "\001\002\000\004\015\176\001\002\000\014\007\uffd7\015" +
    "\170\017\uffd7\024\uffd7\044\ufff0\001\002\000\004\044\167" +
    "\001\002\000\020\011\043\015\054\023\165\035\047\040" +
    "\052\043\044\044\053\001\002\000\010\007\150\017\056" +
    "\024\147\001\002\000\020\011\043\015\054\034\045\035" +
    "\047\040\052\043\044\044\053\001\002\000\004\044\151" +
    "\001\002\000\012\007\uffd6\015\152\017\uffd6\024\uffd6\001" +
    "\002\000\020\011\043\015\054\016\153\035\047\040\052" +
    "\043\044\044\053\001\002\000\004\023\157\001\002\000" +
    "\004\016\155\001\002\000\004\023\156\001\002\000\022" +
    "\004\uffcd\013\uffcd\022\uffcd\030\uffcd\033\uffcd\036\uffcd\042" +
    "\uffcd\044\uffcd\001\002\000\022\004\uffcc\013\uffcc\022\uffcc" +
    "\030\uffcc\033\uffcc\036\uffcc\042\uffcc\044\uffcc\001\002\000" +
    "\004\023\163\001\002\000\024\005\071\006\075\010\070" +
    "\011\066\012\074\014\067\023\162\025\072\026\076\001" +
    "\002\000\022\004\uffd4\013\uffd4\022\uffd4\030\uffd4\033\uffd4" +
    "\036\uffd4\042\uffd4\044\uffd4\001\002\000\022\004\uffd2\013" +
    "\uffd2\022\uffd2\030\uffd2\033\uffd2\036\uffd2\042\uffd2\044\uffd2" +
    "\001\002\000\024\005\071\006\075\010\070\011\066\012" +
    "\074\014\067\023\166\025\072\026\076\001\002\000\022" +
    "\004\uffd0\013\uffd0\022\uffd0\030\uffd0\033\uffd0\036\uffd0\042" +
    "\uffd0\044\uffd0\001\002\000\022\004\uffd1\013\uffd1\022\uffd1" +
    "\030\uffd1\033\uffd1\036\uffd1\042\uffd1\044\uffd1\001\002\000" +
    "\006\023\040\024\042\001\002\000\020\011\043\015\054" +
    "\016\171\035\047\040\052\043\044\044\053\001\002\000" +
    "\004\023\175\001\002\000\004\016\173\001\002\000\004" +
    "\023\174\001\002\000\022\004\uffcb\013\uffcb\022\uffcb\030" +
    "\uffcb\033\uffcb\036\uffcb\042\uffcb\044\uffcb\001\002\000\022" +
    "\004\uffca\013\uffca\022\uffca\030\uffca\033\uffca\036\uffca\042" +
    "\uffca\044\uffca\001\002\000\016\011\043\015\054\035\047" +
    "\040\052\043\044\044\053\001\002\000\024\005\071\006" +
    "\075\010\070\011\066\012\074\014\067\016\200\025\072" +
    "\026\076\001\002\000\004\021\201\001\002\000\020\004" +
    "\142\013\136\030\005\033\145\036\015\042\020\044\143" +
    "\001\002\000\004\022\203\001\002\000\022\004\uffcf\013" +
    "\uffcf\022\uffcf\030\uffcf\033\uffcf\036\uffcf\042\uffcf\044\uffcf" +
    "\001\002\000\022\002\uffea\022\uffea\030\uffea\031\uffea\036" +
    "\uffea\037\uffea\042\uffea\044\uffea\001\002\000\004\022\ufff9" +
    "\001\002\000\016\011\043\015\054\035\047\040\052\043" +
    "\044\044\053\001\002\000\024\005\071\006\075\010\070" +
    "\011\066\012\074\014\067\016\210\025\072\026\076\001" +
    "\002\000\004\021\211\001\002\000\020\004\142\013\136" +
    "\030\005\033\145\036\015\042\020\044\143\001\002\000" +
    "\004\022\213\001\002\000\022\004\uffce\013\uffce\022\uffce" +
    "\030\uffce\033\uffce\036\uffce\042\uffce\044\uffce\001\002\000" +
    "\020\004\142\013\136\030\005\033\145\036\015\042\020" +
    "\044\143\001\002\000\004\022\216\001\002\000\022\002" +
    "\uffeb\022\uffeb\030\uffeb\031\uffeb\036\uffeb\037\uffeb\042\uffeb" +
    "\044\uffeb\001\002\000\012\030\005\036\015\042\020\044" +
    "\013\001\002\000\004\016\uffe7\001\002\000\004\005\222" +
    "\001\002\000\012\030\005\036\015\042\020\044\013\001" +
    "\002\000\004\017\224\001\002\000\004\020\225\001\002" +
    "\000\004\023\226\001\002\000\020\002\uffef\030\uffef\031" +
    "\uffef\036\uffef\037\uffef\042\uffef\044\uffef\001\002\000\004" +
    "\002\uffff\001\002\000\004\002\001\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\226\000\022\006\003\007\006\010\013\011\015\012" +
    "\010\013\005\014\016\017\011\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\020\007\006\010" +
    "\013\011\015\012\010\013\005\014\016\017\226\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\014\010\013\012\030\013\026" +
    "\015\031\021\034\001\001\000\002\001\001\000\002\001" +
    "\001\000\014\010\013\012\030\013\026\015\031\021\027" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\014\010\013\012\030\013\026\015\031\021\032" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\010\010\131\024\130\025\126\001\001\000\012\002" +
    "\050\003\054\016\047\022\045\001\001\000\002\001\001" +
    "\000\002\001\001\000\004\010\121\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\010\002\111\003" +
    "\054\022\045\001\001\000\002\001\001\000\010\002\107" +
    "\003\054\022\045\001\001\000\002\001\001\000\002\001" +
    "\001\000\012\002\061\003\054\022\045\023\063\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\010\002\106\003\054\022\045\001\001" +
    "\000\010\002\105\003\054\022\045\001\001\000\010\002" +
    "\104\003\054\022\045\001\001\000\010\002\103\003\054" +
    "\022\045\001\001\000\010\002\102\003\054\022\045\001" +
    "\001\000\012\002\061\003\054\022\045\023\101\001\001" +
    "\000\010\002\100\003\054\022\045\001\001\000\010\002" +
    "\077\003\054\022\045\001\001\000\010\002\076\003\054" +
    "\022\045\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\012\002\061\003\054\022\045\023" +
    "\115\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\010\002\123\003\054\022\045\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\014\003\145\004" +
    "\136\005\140\010\143\012\137\001\001\000\002\001\001" +
    "\000\014\003\145\004\136\005\204\010\143\012\137\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\010\002\163\003" +
    "\054\022\045\001\001\000\002\001\001\000\012\002\160" +
    "\003\054\016\157\022\045\001\001\000\002\001\001\000" +
    "\002\001\001\000\012\002\061\003\054\022\045\023\153" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\012\002\061\003\054\022\045\023\171\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\010\002\176\003" +
    "\054\022\045\001\001\000\002\001\001\000\002\001\001" +
    "\000\014\003\145\004\136\005\201\010\143\012\137\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\010\002\206\003\054\022\045\001" +
    "\001\000\002\001\001\000\002\001\001\000\014\003\145" +
    "\004\136\005\211\010\143\012\137\001\001\000\002\001" +
    "\001\000\002\001\001\000\014\003\145\004\136\005\214" +
    "\010\143\012\137\001\001\000\002\001\001\000\002\001" +
    "\001\000\010\010\131\024\217\025\126\001\001\000\002" +
    "\001\001\000\002\001\001\000\004\010\222\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$Parser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$Parser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$Parser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}


  /** Scan to get the next Symbol. */
  public java_cup.runtime.Symbol scan()
    throws java.lang.Exception
    {

	Symbol s;
	s = lexer.next_token();
		System.out.print("[");
	System.out.print(lexer.getLine());
	System.out.print(":");
	System.out.print(lexer.getCharPos());
	System.out.print("] ");
	System.out.print(TokenNames.terminalNames[s.sym]);
	if (s.value != null)
	{
		System.out.print("( ");
		System.out.print(s.value);
		System.out.print(" )");
	}
	System.out.print("\n");
	return s; 

    }


	public Lexer lexer;
	public String file;

	public Parser(Lexer lexer, String file)
	{
		super(lexer);
		this.lexer = lexer;
		this.file = file;
	}
	public void report_error(String message, Object info)
	{
		System.out.print("ERROR >> ");		
		System.out.print("[");
		String line = String.valueOf(lexer.getLine());
		System.out.print(lexer.getLine());
		System.out.print(":");
		String pos = String.valueOf(lexer.getCharPos());
		System.out.print(lexer.getCharPos());
		System.out.print("] ");	
		PrintWriter file_writer;
        try
        {
			String a = String.valueOf(info);
            if (a.equals("#30"))
			{
				file_writer = new PrintWriter(file);
				file_writer.print("ERROR");
            	file_writer.close();
			}
			else
			{
				file_writer = new PrintWriter(file);
				file_writer.print("ERROR");
				file_writer.print("(");
				file_writer.print(line);
								file_writer.print(")");
            	file_writer.close();
			}
        }   
        catch(Exception e){}
	
		System.exit(0);
	}


/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$Parser$actions {
  private final Parser parser;

  /** Constructor */
  CUP$Parser$actions(Parser parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$Parser$do_action_part00000000(
    int                        CUP$Parser$act_num,
    java_cup.runtime.lr_parser CUP$Parser$parser,
    java.util.Stack            CUP$Parser$stack,
    int                        CUP$Parser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$Parser$result;

      /* select the action based on the action number */
      switch (CUP$Parser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0:             {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		AST_PROGRAM start_val = (AST_PROGRAM)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		RESULT = start_val;
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$Parser$parser.done_parsing();
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1:             {
              AST_PROGRAM RESULT =null;
		int pleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int pright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		AST_DEC_LIST p = (AST_DEC_LIST)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_PROGRAM(p, parser.file); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("Program",4, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2:             {
              AST_DEC_LIST RESULT =null;
		int dleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int dright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		AST_DEC d = (AST_DEC)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		int lleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int lright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		AST_DEC_LIST l = (AST_DEC_LIST)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_DEC_LIST(d, l); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("decList",13, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3:             {
              AST_DEC_LIST RESULT =null;
		int dleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int dright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		AST_DEC d = (AST_DEC)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_DEC_LIST(d, null); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("decList",13, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4:             {
              AST_DEC RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int vright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		AST_VAR_DEC v = (AST_VAR_DEC)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_DEC_VAR_DEC(v); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("dec",5, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5:             {
              AST_DEC RESULT =null;
		int fleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int fright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		AST_FUNCDEC f = (AST_FUNCDEC)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_DEC_FUNC_DEC(f); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("dec",5, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6:             {
              AST_DEC RESULT =null;
		int cleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int cright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		AST_CLASS_DEC c = (AST_CLASS_DEC)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_DEC_CLASS_DEC(c); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("dec",5, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7:             {
              AST_DEC RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		AST_ATD a = (AST_ATD)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_DEC_ARRAY_DEC(a); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("dec",5, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8:             {
              AST_STMT_LIST RESULT =null;
		int sleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int sright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		AST_STMT s = (AST_STMT)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		int lleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int lright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		AST_STMT_LIST l = (AST_STMT_LIST)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_STMT_LIST(s,l);    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("stmtList",3, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9:             {
              AST_STMT_LIST RESULT =null;
		int sleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int sright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		AST_STMT s = (AST_STMT)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_STMT_LIST(s,null); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("stmtList",3, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10:             {
              AST_CFEILD_LIST RESULT =null;
		int cleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int cright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		AST_C_FIELD c = (AST_C_FIELD)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		int lleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int lright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		AST_CFEILD_LIST l = (AST_CFEILD_LIST)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_CFEILD_LIST(c,l);    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("cFieldList",15, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11:             {
              AST_CFEILD_LIST RESULT =null;
		int cleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int cright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		AST_C_FIELD c = (AST_C_FIELD)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_CFEILD_LIST(c,null);    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("cFieldList",15, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12:             {
              AST_C_FIELD RESULT =null;
		int vdleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int vdright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		AST_VAR_DEC vd = (AST_VAR_DEC)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_CFEILD_VARDEC(vd);  
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("cField",11, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13:             {
              AST_C_FIELD RESULT =null;
		int fdleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int fdright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		AST_FUNCDEC fd = (AST_FUNCDEC)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_CFEILD_FUNCDEC(fd); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("cField",11, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14:             {
              AST_TYPE RESULT =null;
		int tleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int tright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Object t = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT= new AST_TYPE_INT(tleft + 1)  ;
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("type",6, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15:             {
              AST_TYPE RESULT =null;
		int sleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int sright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Object s = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT= new AST_TYPE_STRING(sleft + 1) ;
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("type",6, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16:             {
              AST_TYPE RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int vright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Object v = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT= new AST_TYPE_VOID(vleft + 1) ;
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("type",6, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17:             {
              AST_TYPE RESULT =null;
		int idleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int idright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		String id = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT= new AST_TYPE_ID(id, idleft + 1) ;
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("type",6, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18:             {
              AST_ATD RESULT =null;
		int idleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)).left;
		int idright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)).right;
		String id = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-5)).value;
		int tleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).left;
		int tright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).right;
		AST_TYPE t = (AST_TYPE)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-3)).value;
		 RESULT= new AST_ATD(id, t, idleft + 1) ;
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("arrayTypedef",7, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-6)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19:             {
              AST_VAR_DEC RESULT =null;
		int tleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int tright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		AST_TYPE t = (AST_TYPE)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int idleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int idright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		String id = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		 RESULT= new AST_VAR_DEC_SIMPLE(t,id, tleft + 1) ;
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("varDec",8, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20:             {
              AST_VAR_DEC RESULT =null;
		int tleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).left;
		int tright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).right;
		AST_TYPE t = (AST_TYPE)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-4)).value;
		int idleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).left;
		int idright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).right;
		String id = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-3)).value;
		int eleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		AST_EXP e = (AST_EXP)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		 RESULT= new AST_VAR_DEC_EXP(t,id,e, tleft + 1)  ;
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("varDec",8, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21:             {
              AST_VAR_DEC RESULT =null;
		int tleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).left;
		int tright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).right;
		AST_TYPE t = (AST_TYPE)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-4)).value;
		int idleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).left;
		int idright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).right;
		String id = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-3)).value;
		int eleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		AST_NEW_EXP e = (AST_NEW_EXP)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		 RESULT= new AST_VAR_DEC_NEWEXP(t,id,e,tleft + 1) ;
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("varDec",8, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22:             {
              AST_FUNCDEC RESULT =null;
		int tleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-6)).left;
		int tright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-6)).right;
		AST_TYPE t = (AST_TYPE)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-6)).value;
		int idleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)).left;
		int idright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)).right;
		String id = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-5)).value;
		int sleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int sright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		AST_STMT_LIST s = (AST_STMT_LIST)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		 RESULT= new AST_FUNCDEC(t,id, null,s, tleft + 1 );
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("funcDec",9, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-6)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 23:             {
              AST_FUNCDEC RESULT =null;
		int tleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-7)).left;
		int tright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-7)).right;
		AST_TYPE t = (AST_TYPE)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-7)).value;
		int idleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-6)).left;
		int idright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-6)).right;
		String id = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-6)).value;
		int aleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).right;
		AST_ARG_LIST a = (AST_ARG_LIST)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-4)).value;
		int sleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int sright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		AST_STMT_LIST s = (AST_STMT_LIST)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		 RESULT= new AST_FUNCDEC(t,id,a,s, tleft + 1) ;
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("funcDec",9, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-7)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 24:             {
              AST_ARG RESULT =null;
		int tleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int tright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		AST_TYPE t = (AST_TYPE)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		int idleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int idright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		String id = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_ARG(t, id); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("arg",19, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 25:             {
              AST_ARG_LIST RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		AST_ARG a = (AST_ARG)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_ARG_LIST(a,null); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("argList",18, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 26:             {
              AST_ARG_LIST RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		AST_ARG a = (AST_ARG)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int lleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int lright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		AST_ARG_LIST l = (AST_ARG_LIST)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_ARG_LIST(a,l); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("argList",18, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 27:             {
              AST_CLASS_DEC RESULT =null;
		int idleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).left;
		int idright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).right;
		String id = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-3)).value;
		int clleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int clright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		AST_CFEILD_LIST cl = (AST_CFEILD_LIST)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		 RESULT= new AST_CLASS_DEC_SIMPLE(id, cl,idleft +1); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("classDec",10, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 28:             {
              AST_CLASS_DEC RESULT =null;
		int id1left = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)).left;
		int id1right = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)).right;
		String id1 = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-5)).value;
		int id2left = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).left;
		int id2right = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).right;
		String id2 = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-3)).value;
		int clleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int clright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		AST_CFEILD_LIST cl = (AST_CFEILD_LIST)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		 RESULT= new AST_CLASS_DEC_EXTENDS(id1, id2, cl,id1left +1);  
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("classDec",10, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-6)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 29:             {
              AST_EXP RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int vright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		AST_VAR v = (AST_VAR)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_EXP_VAR(v);          			   
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("exp",0, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 30:             {
              AST_EXP RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		AST_EXP e1 = (AST_EXP)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		 RESULT = new AST_EXP_SIMPLE(e1);        			   
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("exp",0, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 31:             {
              AST_EXP RESULT =null;
		int bleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int bright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		AST_BINOP b = (AST_BINOP)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_EXP_BINOP(b); 			   
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("exp",0, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 32:             {
              AST_EXP RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)).left;
		int vright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)).right;
		AST_VAR v = (AST_VAR)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-5)).value;
		int nameleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).left;
		int nameright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).right;
		String name = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-3)).value;
		int elleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int elright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		AST_EXPLIST el = (AST_EXPLIST)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		 RESULT = new AST_EXP_VAR_EXP_LIST(v,name,el,vleft+1);   
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("exp",0, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 33:             {
              AST_EXP RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).left;
		int vright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).right;
		AST_VAR v = (AST_VAR)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-4)).value;
		int nameleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int nameright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		String name = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		 RESULT = new AST_EXP_VAR_ID(v,name,vleft+1);   		   
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("exp",0, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 34:             {
              AST_EXP RESULT =null;
		int nameleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).left;
		int nameright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).right;
		String name = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-3)).value;
		int elleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int elright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		AST_EXPLIST el = (AST_EXPLIST)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		 RESULT = new AST_EXP_EXP_LIST(name,el,nameleft + 1);  		   
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("exp",0, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 35:             {
              AST_EXP RESULT =null;
		int nameleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int nameright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		String name = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		 RESULT = new AST_EXP_ID(name,nameleft+1);  				   
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("exp",0, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 36:             {
              AST_EXP RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Integer i = (Integer)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_EXP_INT(i);   			   	       
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("exp",0, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 37:             {
              AST_EXP RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Integer i = (Integer)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_EXP_MINUS_INT(i);   			   	       
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("exp",0, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 38:             {
              AST_EXP RESULT =null;
		 RESULT = new AST_EXP_NIL();   		   			   
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("exp",0, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 39:             {
              AST_EXP RESULT =null;
		int sleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int sright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Object s = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_EXP_STRING(s);   				   
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("exp",0, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 40:             {
              AST_EXPLIST RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		AST_EXP e1 = (AST_EXP)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		AST_EXPLIST e2 = (AST_EXPLIST)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_EXPLIST(e1,e2,e1left + 1);   	     	
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expList",17, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 41:             {
              AST_EXPLIST RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		AST_EXP e1 = (AST_EXP)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_EXPLIST(e1,null, e1left + 1);   	     	
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expList",17, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 42:             {
              AST_VAR RESULT =null;
		int nameleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int nameright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		String name = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_VAR_SIMPLE(name, nameleft + 1);       
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("var",1, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 43:             {
              AST_VAR RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int vright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		AST_VAR v = (AST_VAR)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int fieldNameleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int fieldNameright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		String fieldName = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_VAR_FIELD(v,fieldName, vleft + 1); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("var",1, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 44:             {
              AST_VAR RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).left;
		int vright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).right;
		AST_VAR v = (AST_VAR)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-3)).value;
		int eleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		AST_EXP e = (AST_EXP)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		 RESULT = new AST_VAR_SUBSCRIPT(v,e,vleft+1);     
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("var",1, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 45:             {
              AST_STMT RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).left;
		int vright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).right;
		AST_VAR v = (AST_VAR)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-3)).value;
		int eleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		AST_EXP e = (AST_EXP)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		 RESULT = new AST_STMT_ASSIGN(v,e,vleft + 1);		 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("stmt",2, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 46:             {
              AST_STMT RESULT =null;
		int vdleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int vdright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		AST_VAR_DEC vd = (AST_VAR_DEC)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_STMT_VARDEC(vd); 		 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("stmt",2, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 47:             {
              AST_STMT RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).left;
		int vright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).right;
		AST_VAR v = (AST_VAR)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-3)).value;
		int neleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int neright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		AST_NEW_EXP ne = (AST_NEW_EXP)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		 RESULT = new AST_STMT_NEWEXP(v,ne, vleft + 1);  	 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("stmt",2, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 48:             {
              AST_STMT RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		AST_EXP e = (AST_EXP)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		 RESULT = new AST_STMT_EXP(e, eleft + 1);  			 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("stmt",2, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 49:             {
              AST_STMT RESULT =null;
		int rleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int rright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		Object r = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		 RESULT = new AST_STMT_RETURN(rleft + 1); 			 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("stmt",2, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 50:             {
              AST_STMT RESULT =null;
		int condleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).left;
		int condright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).right;
		AST_EXP cond = (AST_EXP)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-4)).value;
		int bodyleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int bodyright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		AST_STMT_LIST body = (AST_STMT_LIST)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		 RESULT = new AST_STMT_IF(cond,body,condleft +1); 	 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("stmt",2, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-6)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 51:             {
              AST_STMT RESULT =null;
		int condleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).left;
		int condright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).right;
		AST_EXP cond = (AST_EXP)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-4)).value;
		int bodyleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int bodyright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		AST_STMT_LIST body = (AST_STMT_LIST)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		 RESULT = new AST_STMT_WHILE(cond,body,condleft +1); 	 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("stmt",2, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-6)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 52:             {
              AST_STMT RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-6)).left;
		int vright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-6)).right;
		AST_VAR v = (AST_VAR)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-6)).value;
		int nameleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).left;
		int nameright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).right;
		String name = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-4)).value;
		int elleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int elright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		AST_EXPLIST el = (AST_EXPLIST)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		 RESULT = new AST_STMT_VARDOT_EXPLIST(v,name,el);  
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("stmt",2, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-6)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 53:             {
              AST_STMT RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)).left;
		int vright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)).right;
		AST_VAR v = (AST_VAR)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-5)).value;
		int nameleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).left;
		int nameright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).right;
		String name = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-3)).value;
		 RESULT = new AST_STMT_VARDOT(v,name);     
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("stmt",2, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 54:             {
              AST_STMT RESULT =null;
		int nameleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).left;
		int nameright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).right;
		String name = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-4)).value;
		int elleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int elright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		AST_EXPLIST el = (AST_EXPLIST)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		 RESULT = new AST_STMT_EXPLIST(name,el, nameleft +1);   
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("stmt",2, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 55:             {
              AST_STMT RESULT =null;
		int nameleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).left;
		int nameright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).right;
		String name = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-3)).value;
		 RESULT = new AST_STMT_ID(name, nameleft+1);           
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("stmt",2, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 56:             {
              AST_NEW_EXP RESULT =null;
		int tleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int tright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		AST_TYPE t = (AST_TYPE)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_NEWEXP_TYPE(t,tleft+1); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("newExp",12, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 57:             {
              AST_NEW_EXP RESULT =null;
		int tleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).left;
		int tright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).right;
		AST_TYPE t = (AST_TYPE)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-3)).value;
		int eleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		AST_EXP e = (AST_EXP)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		 RESULT = new AST_NEWEXP_EXP(t,e, tleft + 1);
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("newExp",12, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 58:             {
              AST_BINOP RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		AST_EXP e1 = (AST_EXP)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		AST_EXP e2 = (AST_EXP)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_BINOP(0,e1,e2,e1left +1); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("BINOP",16, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 59:             {
              AST_BINOP RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		AST_EXP e1 = (AST_EXP)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		AST_EXP e2 = (AST_EXP)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_BINOP(1,e1,e2,e1left +1); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("BINOP",16, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 60:             {
              AST_BINOP RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		AST_EXP e1 = (AST_EXP)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		AST_EXP e2 = (AST_EXP)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_BINOP(2,e1,e2,e1left +1); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("BINOP",16, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 61:             {
              AST_BINOP RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		AST_EXP e1 = (AST_EXP)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		AST_EXP e2 = (AST_EXP)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_BINOP(3,e1,e2,e1left +1); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("BINOP",16, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 62:             {
              AST_BINOP RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		AST_EXP e1 = (AST_EXP)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		AST_EXP e2 = (AST_EXP)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_BINOP(4,e1,e2,e1left +1); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("BINOP",16, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 63:             {
              AST_BINOP RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		AST_EXP e1 = (AST_EXP)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		AST_EXP e2 = (AST_EXP)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_BINOP(5,e1,e2,e1left +1); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("BINOP",16, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 64:             {
              AST_BINOP RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		AST_EXP e1 = (AST_EXP)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		AST_EXP e2 = (AST_EXP)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_BINOP(6,e1,e2,e1left +1); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("BINOP",16, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 65:             {
              AST_BINOP RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		AST_EXP e1 = (AST_EXP)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		AST_EXP e2 = (AST_EXP)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new AST_BINOP(7,e1,e2,e1left +1); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("BINOP",16, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$Parser$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$Parser$do_action(
    int                        CUP$Parser$act_num,
    java_cup.runtime.lr_parser CUP$Parser$parser,
    java.util.Stack            CUP$Parser$stack,
    int                        CUP$Parser$top)
    throws java.lang.Exception
    {
              return CUP$Parser$do_action_part00000000(
                               CUP$Parser$act_num,
                               CUP$Parser$parser,
                               CUP$Parser$stack,
                               CUP$Parser$top);
    }
}

}
