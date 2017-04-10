package mapper;

import java.util.*;

import static mapper.Topology.*;

/**
 * Created by Xorcist on 2/28/2017.
 */
public class Basic_NonFaultTolerant {

    public static Topology generateTopology(Graph inputGraph) {
        Topology outputTopology = new Topology();
        ListIterator<Edge> itr = inputGraph.edges.listIterator();
        while (itr.hasNext()) {
            Edge edge = itr.next();
            int hops = outputTopology.checkConnection(edge);
            if (hops == 0) {
                outputTopology.addConnection(edge);
            }
        }
        return outputTopology;
    }


}
