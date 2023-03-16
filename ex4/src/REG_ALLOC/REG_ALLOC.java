package REG_ALLOC;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

import REG_ALLOC.DependenciesGraph.node;
import TEMP.*;
import IR.*;

public class REG_ALLOC {
    int registersCount = 10;
    Vertex head;     Vertex tail;    HashMap<String, String> IRtoMIPS = new HashMap<String, String>();

    public REG_ALLOC() {
        IRcommand command = IR.getInstance().head;
        IRcommand_List commands_list = IR.getInstance().tail;
        this.head = new Vertex(0, command);
        Vertex curr_vertex = this.head;
        Vertex next;

        int line_count = 1;
        
        while (commands_list != null) {
            command = commands_list.head;
            commands_list = commands_list.tail;
            next = new Vertex(line_count, command);
            line_count++;
            curr_vertex.direction = next;
            next.father = curr_vertex;
            curr_vertex = next;
        }
        this.tail = curr_vertex;
                        curr_vertex = this.head;
        while (curr_vertex != null) {
            Boolean isBranch = (curr_vertex.line.name.equals("IRcommand_Conditional_Jump"));
            if (isBranch) {
                next = this.head;
                String l = ((IRcommand_Conditional_Jump) curr_vertex.line).label;
                while (next != null) {
                    Boolean isLabel = next.line.getClass().toString().equals("class IR.IRcommand_Label");
                    if (isLabel) {
                        if (((IRcommand_Label) next.line).labelName == l) {
                            curr_vertex.vertex2 = next;
                        }
                        break;
                    }
                    next = next.direction;
                }
            }
            curr_vertex = curr_vertex.direction;
        }
    }

    public void liveOpt() {
        Vertex curr = this.tail;
        Boolean finished = false;
                while (curr != null) {
            if (finished) {
                Iterator<String> d = curr.direction.inSet.iterator();
                while (d.hasNext()) {
                    String elem = d.next();
                    curr.outSet.add(elem);
                }
                if (curr.vertex2 != null) {
                    Iterator<String> w = curr.vertex2.inSet.iterator();
                    while (w.hasNext()) {
                        String elem = w.next();
                        curr.outSet.add(elem);
                    }
                }
            }
            finished = true;

            Iterator<String> it = curr.outSet.iterator();
            while (it.hasNext()) {
                curr.inSet.add(it.next());
            }
            if (curr.line.name.equals("IRcommand_Assign")) {
                curr.inSet.remove(("Temp_" + (Integer.toString(((IRcommand_Assign) curr.line).dst.getSerialNumber()))));
                if (curr.inFunc) {
                    curr.FuncScope.remove(("Temp_" + (Integer.toString(((IRcommand_Assign) curr.line).dst.getSerialNumber()))));
                }
            }
            if (curr.line.name.equals("IRcommand_Binop")) {
                curr.inSet.remove(("Temp_" + (Integer.toString(((IRcommand_Binop) curr.line).dst.getSerialNumber()))));
                curr.inSet.add(("Temp_" + (Integer.toString(((IRcommand_Binop) curr.line).t1.getSerialNumber()))));
                curr.inSet.add(("Temp_" + (Integer.toString(((IRcommand_Binop) curr.line).t2.getSerialNumber()))));
                if (curr.inFunc) {
                    curr.FuncScope.remove(("Temp_" + (Integer.toString(((IRcommand_Binop) curr.line).dst.getSerialNumber()))));
                    curr.FuncScope.add(("Temp_" + (Integer.toString(((IRcommand_Binop) curr.line).t1.getSerialNumber()))));
                    curr.FuncScope.add(("Temp_" + (Integer.toString(((IRcommand_Binop) curr.line).t2.getSerialNumber()))));
                }
            }
            if (curr.line.name.equals("IRcommand_Two_Temps")) {
                curr.inSet.remove(("Temp_" + (Integer.toString(((IRcommand_Two_Temps) curr.line).dst.getSerialNumber()))));
                curr.inSet.add(("Temp_" + (Integer.toString(((IRcommand_Two_Temps) curr.line).val.getSerialNumber()))));
                if (curr.inFunc) {
                    curr.FuncScope
                            .remove(("Temp_" + (Integer.toString(((IRcommand_Two_Temps) curr.line).dst.getSerialNumber()))));
                    curr.FuncScope.add(("Temp_" + (Integer.toString(((IRcommand_Two_Temps) curr.line).val.getSerialNumber()))));
                }
            }
            if (curr.line.getClass().toString().equals("class IR.IRcommand_Store_Local")) {
                curr.inSet.add(("Temp_" + (Integer.toString(((IRcommand_Store_Local) curr.line).src.getSerialNumber()))));
                if (curr.inFunc) {
                    curr.FuncScope.add(("Temp_" + (Integer.toString(((IRcommand_Store_Local) curr.line).src.getSerialNumber()))));
                }
            }
            if (curr.line.getClass().toString().equals("class IR.IRcommand_Store_Global")) {
                curr.inSet.add(("Temp_" + (Integer.toString(((IRcommand_Store_Global) curr.line).dst.getSerialNumber()))));
                if (curr.inFunc) {
                    curr.FuncScope
                            .add(("Temp_" + (Integer.toString(((IRcommand_Store_Global) curr.line).dst.getSerialNumber()))));
                }
            }
            if (curr.line.getClass().toString().equals("class IR.IRcommand_Call_Func")) {
                curr.inSet.remove(("Temp_" + (Integer.toString(((IRcommand_Call_Func) curr.line).t.getSerialNumber()))));
                TEMP h;
                TEMP_LIST t = ((IRcommand_Call_Func) curr.line).tempList;
                while (t != null) {
                    h = t.head;
                    curr.inSet.add(("Temp_" + (Integer.toString(h.getSerialNumber()))));
                    t = t.tail;
                }
                if (curr.inFunc) {
                    curr.FuncScope.remove(("Temp_" + (Integer.toString(((IRcommand_Call_Func) curr.line).t.getSerialNumber()))));
                    t = ((IRcommand_Call_Func) curr.line).tempList;
                    while (t != null) {
                        h = t.head;
                        curr.FuncScope.add(("Temp_" + (Integer.toString(h.getSerialNumber()))));
                        t = t.tail;
                    }
                }
            }
            if (curr.line.getClass().toString().equals("class IR.IRcommand_This_Dot_Field")) {
                if (((IRcommand_This_Dot_Field) curr.line).isAllocated == false) {
                    curr.inSet.add(("Temp_" + (Integer.toString(((IRcommand_This_Dot_Field) curr.line).dst.getSerialNumber()))));
                    if (curr.inFunc) {
                        curr.FuncScope
                                .add(("Temp_" + (Integer.toString(((IRcommand_This_Dot_Field) curr.line).dst.getSerialNumber()))));
                    }
                }
                else
                {
                    curr.inSet.remove(("Temp_" + (Integer.toString(((IRcommand_This_Dot_Field) curr.line).dst.getSerialNumber()))));
                    if (curr.inFunc) {
                        curr.FuncScope
                                .remove(("Temp_" + (Integer.toString(((IRcommand_This_Dot_Field) curr.line).dst.getSerialNumber()))));
                    }
                }
            }
            if (curr.line.getClass().toString().equals("class IR.IRcommand_Virtual_Call")) {
                curr.inSet.remove(("Temp_" + (Integer.toString(((IRcommand_Virtual_Call) curr.line).dst.getSerialNumber()))));
                curr.inSet
                        .add(("Temp_" + (Integer.toString(((IRcommand_Virtual_Call) curr.line).classTemp.getSerialNumber()))));
                TEMP h;
                TEMP_LIST t = ((IRcommand_Virtual_Call) curr.line).args;
                while (t != null) {
                    h = t.head;
                    curr.inSet.add(("Temp_" + (Integer.toString(h.getSerialNumber()))));
                    t = t.tail;
                }
                if (curr.inFunc) {
                    curr.FuncScope
                            .remove(("Temp_" + (Integer.toString(((IRcommand_Virtual_Call) curr.line).dst.getSerialNumber()))));
                    curr.FuncScope
                            .add(("Temp_" + (Integer.toString(((IRcommand_Virtual_Call) curr.line).classTemp.getSerialNumber()))));
                    t = ((IRcommand_Virtual_Call) curr.line).args;
                    while (t != null) {
                        h = t.head;
                        curr.FuncScope.add(("Temp_" + (Integer.toString(h.getSerialNumber()))));
                        t = t.tail;
                    }
                }
            }
            if (curr.line.name.equals("IRcommand_Conditional_Jump")) {
                if ((curr.line.getClass().toString().equals("class IR.IRcommand_Jump_bnez"))
                        || (curr.line.getClass().toString().equals("class IR.IRcommand_Jump_Beqz"))) {
                    curr.inSet
                            .add(("Temp_" + (Integer.toString(((IRcommand_Conditional_Jump) curr.line).op1.getSerialNumber()))));
                    if (curr.inFunc) {
                        curr.FuncScope
                                .add(("Temp_" + (Integer.toString(((IRcommand_Conditional_Jump) curr.line).op1.getSerialNumber()))));
                    }
                }
                else if ((curr.line.getClass().toString().equals("class IR.IRcommand_Jump_Label")))
                {
                }
                else
                {
                    curr.inSet
                            .add(("Temp_" + (Integer.toString(((IRcommand_Conditional_Jump) curr.line).op1.getSerialNumber()))));
                    curr.inSet
                            .add(("Temp_" + (Integer.toString(((IRcommand_Conditional_Jump) curr.line).op2.getSerialNumber()))));
                    if (curr.inFunc) {
                        curr.FuncScope
                                .add(("Temp_" + (Integer.toString(((IRcommand_Conditional_Jump) curr.line).op1.getSerialNumber()))));
                        curr.FuncScope
                                .add(("Temp_" + (Integer.toString(((IRcommand_Conditional_Jump) curr.line).op2.getSerialNumber()))));
                    }
                }
            }
            if (curr.line.getClass().toString().equals("class IR.IRcommand_Array_Set")) {
                curr.inSet.add(("Temp_" + (Integer.toString(((IRcommand_Array_Set) curr.line).array.getSerialNumber()))));
                curr.inSet.add(("Temp_" + (Integer.toString(((IRcommand_Array_Set) curr.line).index.getSerialNumber()))));
                curr.inSet.add(("Temp_" + (Integer.toString(((IRcommand_Array_Set) curr.line).value.getSerialNumber()))));
                if (curr.inFunc) {
                    curr.FuncScope.add(("Temp_" + (Integer.toString(((IRcommand_Array_Set) curr.line).array.getSerialNumber()))));
                    curr.FuncScope.add(("Temp_" + (Integer.toString(((IRcommand_Array_Set) curr.line).index.getSerialNumber()))));
                    curr.FuncScope.add(("Temp_" + (Integer.toString(((IRcommand_Array_Set) curr.line).value.getSerialNumber()))));
                }
            }
            if (curr.line.getClass().toString().equals("class IR.IRcommand_Prologue")) {
                curr.inFunc = false;
                if (curr.FuncScope != null) {
                    Iterator<String> iter = curr.FuncScope.iterator();
                    while (iter.hasNext()) {
                        String temp = iter.next();
                        curr.inSet.remove(temp);
                    }
                    curr.FuncScope = new HashSet<String>();
                }
            }
            if (curr.line.getClass().toString().equals("class IR.IRcommand_Epilogue")) {
                curr.inFunc = true;
            }
            if (curr.line.getClass().toString().equals("class IR.IRcommand_Array_Access")) {
                curr.inSet.remove(("Temp_" + (Integer.toString(((IRcommand_Array_Access) curr.line).dst.getSerialNumber()))));
                curr.inSet.add(("Temp_" + (Integer.toString(((IRcommand_Array_Access) curr.line).t1.getSerialNumber()))));
                curr.inSet.add(("Temp_" + (Integer.toString(((IRcommand_Array_Access) curr.line).t2.getSerialNumber()))));
                if (curr.inFunc) {
                    curr.FuncScope
                            .remove(("Temp_" + (Integer.toString(((IRcommand_Array_Access) curr.line).dst.getSerialNumber()))));
                    curr.FuncScope.add(("Temp_" + (Integer.toString(((IRcommand_Array_Access) curr.line).t1.getSerialNumber()))));
                    curr.FuncScope.add(("Temp_" + (Integer.toString(((IRcommand_Array_Access) curr.line).t2.getSerialNumber()))));
                }
            }
            if (curr.line.getClass().toString().equals("class IR.IRcommand_Field_Access")) {
                curr.inSet.remove(("Temp_" + (Integer.toString(((IRcommand_Field_Access) curr.line).dst.getSerialNumber()))));
                curr.inSet.add(("Temp_" + (Integer.toString(((IRcommand_Field_Access) curr.line).src.getSerialNumber()))));
                if (curr.inFunc) {
                    curr.FuncScope
                            .remove(("Temp_" + (Integer.toString(((IRcommand_Field_Access) curr.line).dst.getSerialNumber()))));
                    curr.FuncScope
                            .add(("Temp_" + (Integer.toString(((IRcommand_Field_Access) curr.line).src.getSerialNumber()))));
                }
            }
            if (curr.line.getClass().toString().equals("class IR.IRcommand_Load_Local")) {
                curr.inSet.remove(("Temp_" + (Integer.toString(((IRcommand_Load_Local) curr.line).dst.getSerialNumber()))));
                if (curr.inFunc) {
                    curr.FuncScope
                            .remove(("Temp_" + (Integer.toString(((IRcommand_Load_Local) curr.line).dst.getSerialNumber()))));
                }
            }
            if (curr.line.getClass().toString().equals("class IR.IRcommand_Store_Field")) {
                curr.inSet.remove(("Temp_" + (Integer.toString(((IRcommand_Store_Field) curr.line).val.getSerialNumber()))));
                if (curr.inFunc) {
                    curr.FuncScope
                            .remove(("Temp_" + (Integer.toString(((IRcommand_Store_Field) curr.line).val.getSerialNumber()))));
                }
            }
            if (curr.line.getClass().toString().equals("class IR.IRcommand_Field_Set")) {
                curr.inSet.add(("Temp_" + (Integer.toString(((IRcommand_Field_Set) curr.line).dst.getSerialNumber()))));
                curr.inSet.add(("Temp_" + (Integer.toString(((IRcommand_Field_Set) curr.line).val.getSerialNumber()))));
                if (curr.inFunc) {
                    curr.FuncScope.add(("Temp_" + (Integer.toString(((IRcommand_Field_Set) curr.line).dst.getSerialNumber()))));
                    curr.FuncScope.add(("Temp_" + (Integer.toString(((IRcommand_Field_Set) curr.line).val.getSerialNumber()))));
                }
            }
            if (curr.line.getClass().toString().equals("class IR.IRcommand_Return")) {
                curr.inFunc = true;
                if (((IRcommand_Return) curr.line).RetVal != null) {
                    curr.inSet.add(("Temp_" + (Integer.toString(((IRcommand_Return) curr.line).RetVal.getSerialNumber()))));
                }

            }
            if (curr.line.getClass().toString().equals("class IR.IRcommand_New_Array")) {
                curr.inSet.add(("Temp_" + (Integer.toString(((IRcommand_New_Array) curr.line).src.getSerialNumber()))));
                curr.inSet.add(("Temp_" + (Integer.toString(((IRcommand_New_Array) curr.line).dst.getSerialNumber()))));
                if (curr.inFunc) {
                    curr.FuncScope.add(("Temp_" + (Integer.toString(((IRcommand_New_Array) curr.line).src.getSerialNumber()))));
                    curr.FuncScope.add(("Temp_" + (Integer.toString(((IRcommand_New_Array) curr.line).dst.getSerialNumber()))));
                }
            }
            if (curr.father != null) {
                curr.father.inFunc = curr.inFunc;
                curr.father.FuncScope = curr.FuncScope;
            }
            curr = curr.father;
        }
    }

    public void allocationRegisters() {
        Stack<node> nodes_stack = new Stack<node>();
        DependenciesGraph graph = new DependenciesGraph(this.head);

                Iterator<node> simplify_nodes = graph.allNodes.iterator();
        while (simplify_nodes.hasNext()) {
            node curr = simplify_nodes.next();
            if (curr.neighborsCount < registersCount) {
                graph.graphNodes.remove(curr);
                nodes_stack.push(curr);
                Iterator<node> r = graph.allNodes.iterator();
                while (r.hasNext()) {
                    node remove = r.next();
                    if (remove.neighbors.contains(curr.name)) {
                        remove.neighborsCount--;
                    }
                }
            }
        }
                while (!nodes_stack.isEmpty()) {
            node toAdd = nodes_stack.pop();
            graph.graphNodes.add(toAdd);             HashSet<String> col = new HashSet<String>();
            col.add("0");
            col.add("1");
            col.add("2");
            col.add("3");
            col.add("4");
            col.add("5");
            col.add("6");
            col.add("7");
            col.add("8");
            Iterator<String> n = toAdd.neighbors.iterator();
            while (n.hasNext()) {
                String neighborsName = n.next();
                node actualNeig = graph.findNode(neighborsName);
                if (actualNeig != null) {
                    col.remove(actualNeig.register);                 }
            }
            toAdd.register = col.iterator().next();
        }
                        Iterator<node> lastOne = graph.graphNodes.iterator();
        while (lastOne.hasNext()) {
            node curr = lastOne.next();
            this.IRtoMIPS.put(curr.name, curr.register);
        }

        this.IRtoMIPS.put("dead", "9");
        changeIR();
    }

    public void changeIR() {
        IRcommand h = IR.getInstance().head;
        IRcommand_List t = IR.getInstance().tail;
        while (t != null) {
            if (h.name.equals("IRcommand_Binop")) {
                String theNum;
                if (((IRcommand_Binop) h).dst.changed == false) {
                    theNum = IRtoMIPS.get(("Temp_" + (Integer.toString(((IRcommand_Binop) h).dst.getSerialNumber()))));
                    if (theNum == null) {
                        theNum = IRtoMIPS.get("dead");
                    }
                    ((IRcommand_Binop) h).dst.serial = Integer.parseInt(theNum);
                    ((IRcommand_Binop) h).dst.changed = true;
                }
                if (((IRcommand_Binop) h).t1.changed == false) {
                    theNum = IRtoMIPS.get(("Temp_" + (Integer.toString(((IRcommand_Binop) h).t1.getSerialNumber()))));
                    if (theNum == null) {
                        theNum = IRtoMIPS.get("dead");
                    }
                    ((IRcommand_Binop) h).t1.serial = Integer.parseInt(theNum);
                    ((IRcommand_Binop) h).t1.changed = true;
                }
                if (((IRcommand_Binop) h).t2.changed == false) {
                    theNum = IRtoMIPS.get(("Temp_" + (Integer.toString(((IRcommand_Binop) h).t2.getSerialNumber()))));
                    if (theNum == null) {
                        theNum = IRtoMIPS.get("dead");
                    }
                    ((IRcommand_Binop) h).t2.serial = Integer.parseInt(theNum);
                    ((IRcommand_Binop) h).t2.changed = true;
                }
            }
            if (h.name.equals("IRcommand_Assign")) {
                if (((IRcommand_Assign) h).dst.changed == false) {
                    String theNum = IRtoMIPS.get(("Temp_" + (Integer.toString(((IRcommand_Assign) h).dst.getSerialNumber()))));
                    if (theNum == null) {
                        theNum = IRtoMIPS.get("dead");
                    }
                    ((IRcommand_Assign) h).dst.serial = Integer.parseInt(theNum);
                    ((IRcommand_Assign) h).dst.changed = true;
                }
            }
            if (h.name.equals("IRcommand_Two_Temps")) {
                String theNum;
                if (((IRcommand_Two_Temps) h).dst.changed == false) {
                    theNum = IRtoMIPS.get(("Temp_" + (Integer.toString(((IRcommand_Two_Temps) h).dst.getSerialNumber()))));
                    if (theNum == null) {
                        theNum = IRtoMIPS.get("dead");
                    }
                    ((IRcommand_Two_Temps) h).dst.serial = Integer.parseInt(theNum);
                    ((IRcommand_Two_Temps) h).dst.changed = true;
                }
                if (((IRcommand_Two_Temps) h).val.changed == false) {
                    theNum = IRtoMIPS.get(("Temp_" + (Integer.toString(((IRcommand_Two_Temps) h).val.getSerialNumber()))));
                    if (theNum == null) {
                        theNum = IRtoMIPS.get("dead");
                    }
                    ((IRcommand_Two_Temps) h).val.serial = Integer.parseInt(theNum);
                    ((IRcommand_Two_Temps) h).val.changed = true;
                }

            }
            if (h.getClass().toString().equals("class IR.IRcommand_Store_Local")) {
                if (((IRcommand_Store_Local) h).src.changed == false) {
                    String theNum = IRtoMIPS
                            .get(("Temp_" + (Integer.toString(((IRcommand_Store_Local) h).src.getSerialNumber()))));
                    if (theNum == null) {
                        theNum = IRtoMIPS.get("dead");
                    }
                    ((IRcommand_Store_Local) h).src.serial = Integer.parseInt(theNum);
                    ((IRcommand_Store_Local) h).src.changed = true;
                }

            }
            if (h.getClass().toString().equals("class IR.IRcommand_Call_Func")) {
                String theNum;
                if (((IRcommand_Call_Func) h).t.changed == false) {
                    theNum = this.IRtoMIPS.get(("Temp_" + (Integer.toString(((IRcommand_Call_Func) h).t.getSerialNumber()))));
                    if (theNum == null) {
                        theNum = this.IRtoMIPS.get("dead");
                    }
                    ((IRcommand_Call_Func) h).t.serial = Integer.parseInt(theNum);
                    ((IRcommand_Call_Func) h).t.changed = true;
                }
                TEMP x;
                TEMP_LIST y = ((IRcommand_Call_Func) h).tempList;
                while (y != null) {
                    x = y.head;
                    if (x.changed == false) {
                        theNum = IRtoMIPS.get(("Temp_" + (Integer.toString(x.getSerialNumber()))));
                        if (theNum == null) {
                            theNum = IRtoMIPS.get("dead");
                        }
                        x.serial = Integer.parseInt(theNum);
                        x.changed = true;
                    }
                    y = y.tail;

                }
            }

            if (h.name.equals("class IR.IRcommand_Conditional_Jump")) {
                if ((h.getClass().toString().equals("class IR.IRcommand_Jump_bnez"))
                        || (h.getClass().toString().equals("class IR.IRcommand_Jump_Beqz"))) {
                    if (((IRcommand_Conditional_Jump) h).op1.changed == false) {
                        String theNum = IRtoMIPS
                                .get(("Temp_" + (Integer.toString(((IRcommand_Conditional_Jump) h).op1.getSerialNumber()))));
                        if (theNum == null) {
                            theNum = IRtoMIPS.get("dead");
                        }
                        ((IRcommand_Conditional_Jump) h).op1.serial = Integer.parseInt(theNum);
                        ((IRcommand_Conditional_Jump) h).op1.changed = true;
                    }
                }
                else if ((h.getClass().toString().equals("class IR.IRcommand_Jump_Label")))
                {
                }
                else
                {
                    String theNum;
                    if (((IRcommand_Conditional_Jump) h).op1.changed == false) {
                        theNum = IRtoMIPS
                                .get(("Temp_" + (Integer.toString(((IRcommand_Conditional_Jump) h).op1.getSerialNumber()))));
                        if (theNum == null) {
                            theNum = IRtoMIPS.get("dead");
                        }
                        ((IRcommand_Conditional_Jump) h).op1.serial = Integer.parseInt(theNum);
                        ((IRcommand_Conditional_Jump) h).op1.changed = true;
                    }
                    if (((IRcommand_Conditional_Jump) h).op2.changed == false) {
                        theNum = IRtoMIPS
                                .get(("Temp_" + (Integer.toString(((IRcommand_Conditional_Jump) h).op2.getSerialNumber()))));
                        if (theNum == null) {
                            theNum = IRtoMIPS.get("dead");
                        }
                        ((IRcommand_Conditional_Jump) h).op2.serial = Integer.parseInt(theNum);
                        ((IRcommand_Conditional_Jump) h).op2.changed = true;

                    }

                }

            }
            if (h.getClass().toString().equals("class IR.IRcommand_Array_Set")) {
                String theNum;
                if (((IRcommand_Array_Set) h).array.changed == false) {
                    theNum = IRtoMIPS.get(("Temp_" + (Integer.toString(((IRcommand_Array_Set) h).array.getSerialNumber()))));
                    if (theNum == null) {
                        theNum = IRtoMIPS.get("dead");
                    }
                    ((IRcommand_Array_Set) h).array.serial = Integer.parseInt(theNum);
                    ((IRcommand_Array_Set) h).array.changed = true;
                }
                if (((IRcommand_Array_Set) h).index.changed == false) {
                    theNum = IRtoMIPS.get(("Temp_" + (Integer.toString(((IRcommand_Array_Set) h).index.getSerialNumber()))));
                    if (theNum == null) {
                        theNum = IRtoMIPS.get("dead");
                    }
                    ((IRcommand_Array_Set) h).index.serial = Integer.parseInt(theNum);
                    ((IRcommand_Array_Set) h).index.changed = true;
                }
                if (((IRcommand_Array_Set) h).value.changed == false) {
                    theNum = IRtoMIPS.get(("Temp_" + (Integer.toString(((IRcommand_Array_Set) h).value.getSerialNumber()))));
                    if (theNum == null) {
                        theNum = IRtoMIPS.get("dead");
                    }
                    ((IRcommand_Array_Set) h).value.serial = Integer.parseInt(theNum);
                    ((IRcommand_Array_Set) h).value.changed = true;
                }

            }
            if (h.getClass().toString().equals("class IR.IRcommand_Array_Access")) {
                String theNum;
                if (((IRcommand_Array_Access) h).dst.changed == false) {
                    theNum = IRtoMIPS.get(("Temp_" + (Integer.toString(((IRcommand_Array_Access) h).dst.getSerialNumber()))));
                    if (theNum == null) {
                        theNum = IRtoMIPS.get("dead");
                    }
                    ((IRcommand_Array_Access) h).dst.serial = Integer.parseInt(theNum);
                    ((IRcommand_Array_Access) h).dst.changed = true;
                }
                if (((IRcommand_Array_Access) h).t1.changed == false) {
                    theNum = IRtoMIPS.get(("Temp_" + (Integer.toString(((IRcommand_Array_Access) h).t1.getSerialNumber()))));
                    if (theNum == null) {
                        theNum = IRtoMIPS.get("dead");
                    }
                    ((IRcommand_Array_Access) h).t1.serial = Integer.parseInt(theNum);
                    ((IRcommand_Array_Access) h).t1.changed = true;
                }
                if (((IRcommand_Array_Access) h).t2.changed == false) {
                    theNum = IRtoMIPS.get(("Temp_" + (Integer.toString(((IRcommand_Array_Access) h).t2.getSerialNumber()))));
                    if (theNum == null) {
                        theNum = IRtoMIPS.get("dead");
                    }
                    ((IRcommand_Array_Access) h).t2.serial = Integer.parseInt(theNum);
                    ((IRcommand_Array_Access) h).t2.changed = true;
                }

            }
            if (h.getClass().toString().equals("class IR.IRcommand_Load_Local")) {
                if (((IRcommand_Load_Local) h).dst.changed == false) {
                    String theNum = IRtoMIPS
                            .get(("Temp_" + (Integer.toString(((IRcommand_Load_Local) h).dst.getSerialNumber()))));
                    if (theNum == null) {
                        theNum = IRtoMIPS.get("dead");
                    }
                    ((IRcommand_Load_Local) h).dst.serial = Integer.parseInt(theNum);
                    ((IRcommand_Load_Local) h).dst.changed = true;
                }
            }
            if (h.getClass().toString().equals("class IR.IRcommand_Field_Access")) {
                String theNum;
                if (((IRcommand_Field_Access) h).dst.changed == false) {
                    theNum = IRtoMIPS.get(("Temp_" + (Integer.toString(((IRcommand_Field_Access) h).dst.getSerialNumber()))));
                    if (theNum == null) {
                        theNum = IRtoMIPS.get("dead");
                    }
                    ((IRcommand_Field_Access) h).dst.serial = Integer.parseInt(theNum);
                    ((IRcommand_Field_Access) h).dst.changed = true;
                }
                if (((IRcommand_Field_Access) h).src.changed == false) {
                    theNum = IRtoMIPS.get(("Temp_" + (Integer.toString(((IRcommand_Field_Access) h).src.getSerialNumber()))));
                    if (theNum == null) {
                        theNum = IRtoMIPS.get("dead");
                    }
                    ((IRcommand_Field_Access) h).src.serial = Integer.parseInt(theNum);
                    ((IRcommand_Field_Access) h).src.changed = true;
                }
            }

            if (h.getClass().toString().equals("class IR.IRcommand_Field_Set")) {
                String theNum;
                if (((IRcommand_Field_Set) h).dst.changed == false) {
                    theNum = IRtoMIPS.get(("Temp_" + (Integer.toString(((IRcommand_Field_Set) h).dst.getSerialNumber()))));
                    if (theNum == null) {
                        theNum = IRtoMIPS.get("dead");
                    }
                    ((IRcommand_Field_Set) h).dst.serial = Integer.parseInt(theNum);
                    ((IRcommand_Field_Set) h).dst.changed = true;
                }
                if (((IRcommand_Field_Set) h).val.changed == false) {
                    theNum = IRtoMIPS.get(("Temp_" + (Integer.toString(((IRcommand_Field_Set) h).val.getSerialNumber()))));
                    if (theNum == null) {
                        theNum = IRtoMIPS.get("dead");
                    }
                    ((IRcommand_Field_Set) h).val.serial = Integer.parseInt(theNum);
                    ((IRcommand_Field_Set) h).val.changed = true;
                }
            }
            if (h.getClass().toString().equals("class IR.IRcommand_Store_Field")) {
                if (((IRcommand_Store_Field) h).val.changed == false) {
                    String theNum = IRtoMIPS
                            .get(("Temp_" + (Integer.toString(((IRcommand_Store_Field) h).val.getSerialNumber()))));
                    if (theNum == null) {
                        theNum = IRtoMIPS.get("dead");
                    }
                    ((IRcommand_Store_Field) h).val.serial = Integer.parseInt(theNum);
                    ((IRcommand_Store_Field) h).val.changed = true;
                }
            }
            if (h.getClass().toString().equals("class IR.IRcommand_Return")) {
                if (((IRcommand_Return) h).RetVal != null) {
                    if (((IRcommand_Return) h).RetVal.changed == false) {
                        String theNum = IRtoMIPS
                                .get(("Temp_" + (Integer.toString(((IRcommand_Return) h).RetVal.getSerialNumber()))));
                        if (theNum == null) {
                            theNum = IRtoMIPS.get("dead");
                        }
                        ((IRcommand_Return) h).RetVal.serial = Integer.parseInt(theNum);
                        ((IRcommand_Return) h).RetVal.changed = true;
                    }
                }

            }
            if (h.getClass().toString().equals("class IR.IRcommand_New_Array")) {
                if (((IRcommand_New_Array) h).dst.changed == false) {
                    String theNum = IRtoMIPS.get(("Temp_" + (Integer.toString(((IRcommand_New_Array) h).dst.getSerialNumber()))));
                    if (theNum == null) {
                        theNum = IRtoMIPS.get("dead");
                    }
                    ((IRcommand_New_Array) h).dst.serial = Integer.parseInt(theNum);
                    ((IRcommand_New_Array) h).dst.changed = true;
                }
                if (((IRcommand_New_Array) h).src.changed == false) {
                    String theNum = IRtoMIPS.get(("Temp_" + (Integer.toString(((IRcommand_New_Array) h).src.getSerialNumber()))));
                    if (theNum == null) {
                        theNum = IRtoMIPS.get("dead");
                    }
                    ((IRcommand_New_Array) h).src.serial = Integer.parseInt(theNum);
                    ((IRcommand_New_Array) h).src.changed = true;
                }

            }
            if (h.getClass().toString().equals("class IR.IRcommand_Store_Global")) {
                if (((IRcommand_Store_Global) h).dst.changed == false) {
                    String theNum = IRtoMIPS
                            .get(("Temp_" + (Integer.toString(((IRcommand_Store_Global) h).dst.getSerialNumber()))));
                    if (theNum == null) {
                        theNum = IRtoMIPS.get("dead");
                    }
                    ((IRcommand_Store_Global) h).dst.serial = Integer.parseInt(theNum);
                    ((IRcommand_Store_Global) h).dst.changed = true;
                }
            }
            if (h.getClass().toString().equals("class IR.IRcommand_This_Dot_Field")) {
                if (((IRcommand_This_Dot_Field) h).dst.changed == false) {
                    String theNum = IRtoMIPS
                            .get(("Temp_" + (Integer.toString(((IRcommand_This_Dot_Field) h).dst.getSerialNumber()))));
                    if (theNum == null) {
                        theNum = IRtoMIPS.get("dead");
                    }
                    ((IRcommand_This_Dot_Field) h).dst.serial = Integer.parseInt(theNum);
                    ((IRcommand_This_Dot_Field) h).dst.changed = true;
                }
            }
            if (h.getClass().toString().equals("class IR.IRcommand_Virtual_Call")) {
                String theNum;
                if (((IRcommand_Virtual_Call) h).dst.changed == false) {
                    theNum = this.IRtoMIPS
                            .get(("Temp_" + (Integer.toString(((IRcommand_Virtual_Call) h).dst.getSerialNumber()))));
                    if (theNum == null) {
                        theNum = this.IRtoMIPS.get("dead");
                    }
                    ((IRcommand_Virtual_Call) h).dst.serial = Integer.parseInt(theNum);
                    ((IRcommand_Virtual_Call) h).dst.changed = true;
                }
                if (((IRcommand_Virtual_Call) h).classTemp.changed == false) {
                    theNum = this.IRtoMIPS
                            .get(("Temp_" + (Integer.toString(((IRcommand_Virtual_Call) h).classTemp.getSerialNumber()))));
                    if (theNum == null) {
                        theNum = this.IRtoMIPS.get("dead");
                    }
                    ((IRcommand_Virtual_Call) h).classTemp.serial = Integer.parseInt(theNum);
                    ((IRcommand_Virtual_Call) h).classTemp.changed = true;
                }
                TEMP x;
                TEMP_LIST y = ((IRcommand_Virtual_Call) h).args;
                while (y != null) {
                    x = y.head;
                    if (x.changed == false) {
                        theNum = IRtoMIPS.get(("Temp_" + (Integer.toString(x.getSerialNumber()))));
                        if (theNum == null) {
                            theNum = IRtoMIPS.get("dead");
                        }
                        x.serial = Integer.parseInt(theNum);
                        x.changed = true;
                    }
                    y = y.tail;

                }
            }

            h = t.head;
            t = t.tail;
        }
    }
}