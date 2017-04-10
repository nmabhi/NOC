package mapper;

import java.util.ListIterator;

/**
 * Created by Xorcist on 11-04-2017.
 */
public class Native_GraphTopology {
    public static String algorithm = "Copy as it is from input graph";

    public static Topology generateTopology(Graph inputGraph) {
        Topology outputTopology = new Topology();
        ListIterator<Edge> itr = inputGraph.edges.listIterator();
        while(itr.hasNext()) {
            Edge edge = itr.next();
            outputTopology.addConnection(edge);
        }
        return outputTopology;
    }
}
