/***********/
/* PACKAGE */
/***********/
package REG_ALLOC;

/*******************/
/* GENERAL IMPORTS */
/*******************/

import java.util.HashSet;

import IR.*;
/*******************/
/* PROJECT IMPORTS */
/*******************/
public class Vertex {
    Vertex father;     Vertex direction;     Vertex vertex2;
    int time;    IRcommand line;
    HashSet<String> inSet;     HashSet<String> outSet;
    boolean inFunc;
    HashSet<String> FuncScope;

    public Vertex(int time, IRcommand line) {
        this.time = time;
        this.line = line;
                inSet = new HashSet<String>();         outSet = new HashSet<String>();
        FuncScope = new HashSet<String>();
        inFunc = false;
    }

}