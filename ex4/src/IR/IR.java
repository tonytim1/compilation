/***********/
/* PACKAGE */
/***********/
package IR;
import TYPES.*;
import TEMP.*;
import MIPS.*;
import REGALLOC.*;

/*******************/
/* GENERAL IMPORTS */
/*******************/

/*******************/
/* PROJECT IMPORTS */
/*******************/

public class IR {
    private IRcommand head = null;
    private IRcommandList tail = null;

    /******************/
    /* Add IR command */

    /******************/
    public void Add_IRcommand(IRcommand cmd) {
        if ((head == null) && (tail == null)) {
            this.head = cmd;
        } else if ((head != null) && (tail == null)) {
            this.tail = new IRcommandList(cmd, null);
        } else {
            IRcommandList it = tail;
            while ((it != null) && (it.tail != null)) {
                it = it.tail;
            }
            it.tail = new IRcommandList(cmd, null);
        }
    }

    /***************/
    /* MIPS me !!! */

    /***************/
    public void MIPSme() {
        if (head != null) head.MIPSme();
        if (tail != null) tail.MIPSme();
    }
    /********************/
    /*	optimize me		*/

    /********************/
    public void OPTme() {
        IRcommandList commandList = new IRcommandList(head, tail);
        VertexList labels = null;
        VertexList jumps = null;
        VertexList functions = null;
        VertexList returns = null;

        Vertex prevVertex = null;
        Vertex curVertex = null;

        // go over the ir commands
        while (commandList != null && commandList.head != null) {
            prevVertex = curVertex;
            curVertex = new Vertex(commandList.head);
            if (commandList.head instanceof IRcommand_Allocate_Func) {
                // add the function vertex to functions
                functions = new VertexList(curVertex, functions);
            } else {
                if (prevVertex != null) prevVertex.addNext(curVertex);
                curVertex.addPrev(prevVertex);
                // if the command is type of jump then add edge to the label
                if (commandList.head instanceof IRcommand_Jump_Label) {
                    Vertex labelVertex = null;
                    if (labels != null) {
                        labelVertex = labels.getVertex(((IRcommand_Jump_Label) commandList.head).label_name);
                    }
                    if (labelVertex != null) {
                        labelVertex.addPrev(curVertex);
                        curVertex.addNext(labelVertex);
                    } else {
                        jumps = new VertexList(curVertex, jumps);
                    }
                    curVertex = null;

                    // if the command is type of conditional jump then add edge to the label without setting current to null
                } else if (commandList.head instanceof IRcommand_Jump_If_Eq_To_Zero) {
                    Vertex labelVertex = null;
                    if (labels != null)
                        labelVertex = labels.getVertex(((IRcommand_Jump_If_Eq_To_Zero) commandList.head).label_name);
                    if (labelVertex != null) {
                        labelVertex.addPrev(curVertex);
                        curVertex.addNext(labelVertex);
                    } else {
                        jumps = new VertexList(curVertex, jumps);
                    }

                    // if the command is return then invalidate current vertex
                } else if (commandList.head instanceof IRcommand_FuncReturn) {
                    returns = new VertexList(curVertex, returns);
                    curVertex = null;

                    // if the command is label then set a pointer to the right jump command
                } else if (commandList.head instanceof IRcommand_Label) {
                    Vertex returnVertex;
                    if (jumps != null)
                        returnVertex = jumps.getVertex(((IRcommand_Label) commandList.head).label_name);
                    else returnVertex = null;
                    if (returnVertex != null) {
                        returnVertex.addNext(curVertex);
                        curVertex.addPrev(returnVertex);
                    } else {
                        labels = new VertexList(curVertex, labels);
                    }
                }
            }
            commandList = commandList.tail;
        }
        for (VertexList e = returns; e != null; e = e.tail) {
            if (e.head != null) {

                // running liveness on each return command
                e.head.liveness();
            }
        }
        for (VertexList e = functions; e != null; e = e.tail) {
            if (e.head != null) {
                e.head.buildDependancyGraph();
            }
        }
        REG_ALLOC.getInstance().allocate_registers();
    }
    /**************************************/
    /* USUAL SINGLETON IMPLEMENTATION ... */
    /**************************************/
    private static IR instance;

    /*****************************/
    /* PREVENT INSTANTIATION ... */

    /*****************************/
    protected IR() {
    }

    /******************************/
    /* GET SINGLETON INSTANCE ... */

    /******************************/
    public static IR getInstance() {
        if (instance == null) {
            /*******************************/
            /* [0] The instance itself ... */
            /*******************************/
            instance = new IR();
        }
        return instance;
    }
}
