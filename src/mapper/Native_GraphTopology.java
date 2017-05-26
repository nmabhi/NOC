package mapper;

import java.util.ListIterator;

/**
 * Created by Xorcist on 11-04-2017.
 */
public class Native_GraphTopology implements Topology_generator {
    public final String algorithm = "Copy as it is from input graph";

    public Topology generateTopology(Graph inputGraph) {
        Topology topology = new Topology();
        ListIterator<Edge> itr = inputGraph.edges.listIterator();
        while(itr.hasNext()) {
            Edge edge = itr.next();
            topology.addConnection(edge);
        }
        return topology;
    }
}
