/***********/
/* PACKAGE */
/***********/
package CFG;

/*******************/
/* GENERAL IMPORTS */
/*******************/

import java.util.HashSet;
import IR.*;
/*******************/
/* PROJECT IMPORTS */
/*******************/
public class BasicBlock{
	BasicBlock father; //the liveness needs to go buttom up
	BasicBlock direct; //the building is done top down
	BasicBlock condWorked;
	int time;// will be our n in liveness analysis
	IRcommand line;
	HashSet<String> inSet; //the in\out sets for liveness, are empty
	HashSet<String> outSet; 
	boolean inFunc; 
	HashSet<String> FuncScope; 

	public BasicBlock(int time, IRcommand line) {
		this.time=time;
		this.line=line;
		//sons will be assigned once we lookahead to them
		inSet = new HashSet<String> (); // sets sould start empty
		outSet = new HashSet<String> ();
		FuncScope = new HashSet<String> ();
		inFunc = false;
	}

}