package mapper;

import java.util.*;

import mapper.Edge;

/**
 * Created by Abhishek on 2/28/2017.
 */
public class Basic_Mapper {
    private static List<Edge> edges = new ArrayList<>();
    private static HashMap<Integer, List<Integer>> connections = new HashMap<>();
    private static List<Integer> visitedNodes = new ArrayList<>();
    private static List<Integer> costList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        /*
        * input format:-
        * First line contains two integers N(number of nodes) and M(number of edges).
        * For next M lines, each line contains three integers n1, n2 and w[n1, n2] where
        * w[n1, n2] is the weight or the communication requirement between the two nodes n1 and n2
        *
        4 4
        1 2 2
        1 3 7
        2 3 5
        2 4 1
        */
        int N = input.nextInt();
        int M = input.nextInt();

        for (int m = 0; m < M; m++) {
            Integer n1 = input.nextInt();
            Integer n2 = input.nextInt();
            Integer w = input.nextInt();
            Edge edge = new Edge();
            edge.node1 = n1;
            edge.node2 = n2;
            edge.weight = w;
            insert(edge);
        }
        printList(edges);

        /*for(int c = 0; c < M; c++){
            addConnection(edges.get(c));
        }
        printMapOfMap(connections);
        System.out.println(checkConnection(1, 4));*/

        ListIterator<Edge> itr = edges.listIterator();
        while (itr.hasNext()) {
            Edge edge = itr.next();
            int hops = checkConnection(edge);
            if (hops > 0) {
                edge.hops = hops;
            }
            else{
                addConnection(edge);
                edge.hops = 1;
            }
            costList.add(edge.hops * edge.weight);
        }
        printMapOfMap(connections);
        System.out.println(commCost());
    }

    private static void insert(Edge edge) {
        int i = edges.size();
        while (i > 0) {
            i--;
            if (edge.weight > edges.get(i).weight) {
                break;
            } else {
                i++;
                break;
            }
        }
        edges.add(i, edge);
    }

    private static void printList(List<Edge> edges) {
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            System.out.println(edge.node1 + " - " + edge.node2 + " : " + edge.weight);
        }
    }

    private static void printMapOfMap(HashMap<Integer, List<Integer>> map) {
        System.out.println(map.entrySet().toString());
    }

    private static void addConnection(Edge edge) {
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

    private static int checkConnection(Edge edge) {
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

    private static Integer commCost(){
        Integer sum = 0;
        for (int i = 0; i < costList.size(); i++){
            sum += costList.get(i);
        }
        return sum;
    }
}
