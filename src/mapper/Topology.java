package mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Xorcist on 10-04-2017.
 */
public class Topology {
    private static HashMap<Integer, List<Integer>> connections = new HashMap<>();
    private static List<Integer> visitedNodes = new ArrayList<>();
    private static List<Integer> costList = new ArrayList<>();

    public void printTopology() {
        System.out.println(connections.entrySet().toString());
    }

    public static int checkConnection(Edge edge) {
        Integer node1 = edge.node1;
        Integer node2 = edge.node2;
        if (!connections.containsKey(node1) || !connections.containsKey(node1)) {
            return 0;
        } else {
            visitedNodes.clear();
            return checkConnectionIterator(node1, node2, 0);
        }
    }

    private static int checkConnectionIterator(Integer node1, Integer node2, int hops) {
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
                if (temp > 0) ret = temp;
            }
        }
        return ret;
    }

    public static void addConnection(Edge edge) {
        if (connections.containsKey(edge.node1)) {
            connections.get(edge.node1).add(edge.node2);
        } else {
            List<Integer> list = new ArrayList<>(1);
            list.add(edge.node2);
            connections.put(edge.node1, list);
        }

        if (connections.containsKey(edge.node2)) {
            connections.get(edge.node2).add(edge.node1);
        } else {
            List<Integer> list = new ArrayList<>(1);
            list.add(edge.node1);
            connections.put(edge.node2, list);
        }
    }
}
