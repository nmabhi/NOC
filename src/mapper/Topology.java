package mapper;

import java.util.*;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

/**
 * Created by Xorcist on 10-04-2017.
 */
public class Topology {
    public int numberOfLinks = 0;
    public HashMap<Integer, HashMap<Integer, Integer>> connections = new HashMap<>();
    private List<Integer> visitedNodes = new ArrayList<>();
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
        Set<Integer> nodelist = connections.get(node1).keySet();
        if (nodelist.contains(node2)) {
            return hops;
        }
        Iterator itr = nodelist.iterator();
        while (itr.hasNext()) {
            Integer nextNode = Integer.valueOf(itr.next().toString());
            if (!visitedNodes.contains(nextNode)) {
                int temp = checkConnectionIterator(nextNode, node2, hops);
                if (temp > 0) ret = temp;
            }
        }
        return ret;
    }

    public void addConnection(Edge edge) {
        numberOfLinks++;
        if (connections.containsKey(edge.node1)) {
            connections.get(edge.node1).put(edge.node2, edge.weight);
        } else {
            HashMap<Integer, Integer> map = new HashMap<>(1);
            map.put(edge.node2, edge.weight);
            connections.put(edge.node1, map);
        }

        if (connections.containsKey(edge.node2)) {
            connections.get(edge.node2).put(edge.node1, edge.weight);
        } else {
            HashMap<Integer, Integer> map = new HashMap<>(1);
            map.put(edge.node1, edge.weight);
            connections.put(edge.node2, map);
        }
    }

    public HashMap<Integer, HashMap<Integer, Integer>> calculateLinkUsage(Graph graph) {
        HashMap<Integer, HashMap<Integer, Integer>> connections_1 = new HashMap<Integer, HashMap<Integer, Integer>>;
        ListIterator<Edge> itr = graph.edges.listIterator();
        while (itr.hasNext()) {
            Edge edge = itr.next();
            int a = min(edge.node1, edge.node2);
            int b = max(edge.node1, edge.node2);
            
        }
    }
/*
    //for finding the link with lowest commCost
    public Integer minCostLink(Integer node, Graph inputGraph) {
        for (int i = inputGraph.edges.size(); i > 0; i--) {

        }
    }*/

}
