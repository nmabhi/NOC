package mapper;

import java.util.ListIterator;

/**
 * Created by Xorcist on 2/28/2017.
 */
public class Basic_NonFaultTolerant implements Topology_generator {

    public final String algorithm = "Basic non-fault tolerant";

    public Topology generateTopology(Graph inputGraph) {
        Topology outputTopology = new Topology();
        ListIterator<Edge> itr = inputGraph.edges.listIterator();
        while (itr.hasNext()) {
            Edge edge = itr.next();
            int hops = outputTopology.hops(edge);
            if (hops == -1) {
                outputTopology.addConnection(edge);
            }
        }
        return outputTopology;
    }


}
