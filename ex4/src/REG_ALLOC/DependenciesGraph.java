package REG_ALLOC;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;
/*******************/
/* PROJECT IMPORTS */

/*******************/
public class DependenciesGraph {
    public class node {
        String name;
        HashSet<String> neighbors;
        int neighborsCount;
        String register;

        public node(String name) {
            this.name = name;
            this.neighbors = new HashSet<String>();
            this.neighborsCount = 0;
            this.register = null;
        }
    }

    HashSet<node> graphNodes = new HashSet<node>();
    HashSet<node> allNodes = new HashSet<node>();

    public DependenciesGraph(Vertex vertex) {
        Vertex curr = vertex;
        node res;
        while (curr != null) {
            Iterator<String> it = curr.inSet.iterator();
            while (it.hasNext()) {
                String name = it.next();
                res = findNode(name);
                if (res == null) {
                    res = new node(name);
                    this.allNodes.add(res);
                    this.graphNodes.add(res);
                }
                Iterator<String> o = curr.inSet.iterator();
                while (o.hasNext()) {
                    String s = o.next();
                    if (!s.equals(res.name)) {
                        res.neighbors.add(s);
                    }
                }
                res.neighborsCount = res.neighbors.size();
            }
            curr = curr.direction;
        }
    }

    public node findNode(String name) {
        Iterator<node> nodes = this.graphNodes.iterator();
        while (nodes.hasNext()) {
            node check = nodes.next();
            if (check.name == name) {
                return check;
            }
        }
        return null;
    }
}