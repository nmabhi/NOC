package mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static java.lang.Integer.min;

/**
 * Created by Xorcist on 10-04-2017.
 */
public class Topology {
    public int numberOfLinks = 0;
    public HashMap<Integer, List<Integer>> connections = new HashMap<>();
    private List<Integer> visitedNodes = new ArrayList<>();
    private List<Integer> visitedNodesFT = new ArrayList<>();
    private List<Integer> costList = new ArrayList<>();

    public void printTopology() {
        System.out.println(connections.entrySet().toString());
    }

    public int checkConnection(Edge edge) {
        Integer node1 = edge.node1;
        Integer node2 = edge.node2;
        if (!connections.containsKey(node1) || !connections.containsKey(node1)) {
            return 0;
        } else {
            visitedNodes.clear();
            return checkConnectionIterator(node1, node2, 0);
        }
    }

    private int checkConnectionIterator(Integer node1, Integer node2, int hops) {
        hops++;
        int ret = 0;
        visitedNodes.add(node1);
        List nodelist = connections.get(node1);
        if (nodelist.contains(node2)) {
            return hops;
        }
        Iterator itr = nodelist.iterator();
        while (itr.hasNext()) {
            Integer nextNode = Integer.valueOf(itr.next().toString());
            if (!visitedNodes.contains(nextNode)) {
                int temp = checkConnectionIterator(nextNode, node2, hops);
                if (temp > 0) {
                    if (ret > 0) ret = min(ret, temp);
                    else ret = temp;
                }
            }
        }
        return ret;
    }

    public void addConnection(Edge edge) {
        numberOfLinks++;
        Integer a = edge.node1;
        Integer b = edge.node2;
        if (connections.containsKey(a)) {
            connections.get(a).add(b);
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(b);
            connections.put(a, list);
        }

        if (connections.containsKey(b)) {
            connections.get(b).add(a);
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(a);
            connections.put(b, list);
        }
    }

    public boolean removeConnection(Edge edge) {
        numberOfLinks--;
        Integer a = edge.node1;
        Integer b = edge.node2;
        return connections.get(a).remove(b) && connections.get(b).remove(a);
    }
/*
    //for finding the link with lowest commCost
    public Integer minCostLink(Integer node, Graph inputGraph) {
        for (int i = inputGraph.edges.size(); i > 0; i--) {

        }
    }*/

}
