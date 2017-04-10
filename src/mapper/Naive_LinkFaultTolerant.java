package mapper;

/**
 * Created by Xorcist on 11-04-2017.
 */
public class Naive_LinkFaultTolerant {
    public static String algorithm = "use neighbour for link fault tolerance";

    public Topology generateTopology(Graph inputGraph) {
        Topology outputTopology = Basic_NonFaultTolerant.generateTopology(inputGraph);
        //TODO outputTopology.connections.
        return outputTopology;
    }

}
