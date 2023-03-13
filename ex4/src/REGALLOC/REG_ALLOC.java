package REGALLOC;

import java.util.*;
import TEMP.*;
import IR.*;

public class REG_ALLOC {
    private TEMP_LIST temps = new TEMP_LIST(null, null); // a list of all created temporaries in a sorted order (graph nodes)

    private final String[] colors = {"$t0", "$t1", "$t2", "$t3", "$t4", "$t5", "$t6", "$t7", "$t8", "$t9"}; // a list of register names to "color" the graph with

    public void addTemp(TEMP t) {
        this.temps.add(t);
    }

    public void allocate_registers() {
        //System.out.println("begin allocate registers");
        Stack<TEMP> stack = new Stack<TEMP>();
        while (this.temps.length > 0) {
            //System.out.format("begin while registers, %d, %s", this.temps.length, this.temps);
            for (TEMP_LIST e = this.temps; e != null && e.value != null; e = e.next) {
                //System.out.format("%d neighbors allocate registers", e.value.numOfNeighbors());
                if (e.value.numOfNeighbors() < 10) {
                    stack.push(e.value);
                    e.value.disconnectFromNeighbors();
                    this.temps.remove(e.value);
                    break;
                }
            }
            if (this.temps.value == null)
            {
                break;
            }
        }
        //System.out.println("done building stack for allocate registers");
        while (!stack.isEmpty()) {
            TEMP t = stack.pop();
            Boolean hasColorNeighbor;
            for (int i = 0; i < colors.length; i++) {
                hasColorNeighbor = false;
                for (TEMP_LIST e = t.neighbors; e.value != null; e = e.next) {
                    if (colors[i].equals(e.value.color)) {
                        hasColorNeighbor = true;
                        break;
                    }
                }
                if (!hasColorNeighbor) {
                    t.color = colors[i];
                    break;
                }
            }
        }
        System.out.println("done allocate registers");
    }

    /**************************************/
    /* USUAL SINGLETON IMPLEMENTATION ... */
    /**************************************/
    private static REG_ALLOC instance = null;

    /*****************************/
    /* PREVENT INSTANTIATION ... */

    /*****************************/
    protected REG_ALLOC() {
    }

    /******************************/
    /* GET SINGLETON INSTANCE ... */

    /******************************/
    public static REG_ALLOC getInstance() {
        if (instance == null) {
            /*******************************/
            /* [0] The instance itself ... */
            /*******************************/
            instance = new REG_ALLOC();
        }
        return instance;
    }
}