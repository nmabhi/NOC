package mapper;

/**
 * Created by parth on 25-05-2017.
 */
public interface Topology_generator {

    public String algorithm = "Topology generator";

    public Topology generateTopology(Graph graph);
}
