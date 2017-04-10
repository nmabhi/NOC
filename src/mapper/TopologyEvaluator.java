package mapper;


import java.util.ListIterator;

/**
 * Created by Xorcist on 10-04-2017.
 */
public class TopologyEvaluator {

    public static int commCost(Graph graph, Topology topology) {
        ListIterator<Edge> itr = graph.edges.listIterator();
        int sum = 0;
        while (itr.hasNext()) {
            Edge edge = itr.next();
            int hops = topology.checkConnection(edge);
            if (hops <= 0) {
                System.out.print("Invalid topology! ");
                System.out.println("Nodes" + edge.node1 + " & " + edge.node2 + " have no connection!");
                sum = 0;
                break;
            }
            sum += hops * edge.weight;
        }
        return sum;
    }

    public static float linkUtilization(Graph graph, Topology topology) {
        float commCost = commCost(graph, topology);
        float lu = commCost / topology.numberOfLinks;
        return lu;
    }
}
