package mapper;

import java.util.*;

/**
 * Created by Xorcist on 11-04-2017.
 */
public class PoorestNeighbour_LinkFaultTolerant {
    public static String algorithm = "poorest neighbour link fault tolerance";

    public static Topology generateTopology(Graph graph) {
        Topology topology = Native_GraphTopology.generateTopology(graph);
        HashMap<Integer, HashMap<Integer, Integer>> linkUtilMap = TopologyEvaluator.linkUtilizationGraph(graph, topology);
        HashMap<Integer, Integer> newLinkages = new HashMap<Integer, Integer>();
        Iterator itr = topology.connections.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry entry = (Map.Entry) itr.next();
            Integer a = (Integer) entry.getKey();
            Iterator itr2 = ((List) entry.getValue()).iterator();
            while (itr2.hasNext()) {
                Integer b = (Integer) itr2.next();
                if (b > a) {//so that each link is visited only once
                    boolean temp = false;
                    try {
                        temp = TopologyEvaluator.checkLinkFaultTolerance(a, b, topology);
                    } catch (Exception e) {
                    }
                    if (!temp) {
                        Integer lowestUtil = 2147483647;
                        Integer poorestNeighbour = 0;
                        Integer joinee = a;
                        if (linkUtilMap.get(b).size() > linkUtilMap.get(a).size()) {
                            Iterator itr_lum_b = linkUtilMap.get(b).entrySet().iterator();
                            while (itr_lum_b.hasNext()) {
                                Map.Entry entry_b = (Map.Entry) itr_lum_b.next();
                                Integer neighbour = (Integer) (entry_b).getKey();
                                Integer util = (Integer) (entry_b).getValue();
                                if (util < lowestUtil && neighbour != a) poorestNeighbour = neighbour;
                            }
                        } else {
                            joinee = b;
                            Iterator itr_lum_a = linkUtilMap.get(a).entrySet().iterator();
                            while (itr_lum_a.hasNext()) {
                                Map.Entry entry_a = (Map.Entry) itr_lum_a.next();
                                Integer neighbour = (Integer) (entry_a).getKey();
                                Integer util = (Integer) (entry_a).getValue();
                                if (util < lowestUtil && neighbour != b) poorestNeighbour = neighbour;
                            }
                        }
                        if (poorestNeighbour != 0) {
                            newLinkages.put(joinee, poorestNeighbour);
                            linkUtilMap.get(joinee).put(poorestNeighbour, 0);
                            linkUtilMap.get(poorestNeighbour).put(joinee, 0);
                        }
                    }
                }
            }
        }

        for (Map.Entry entry : newLinkages.entrySet()) {
            Integer joinee = (Integer) entry.getKey();
            Integer poorestNeighbour = (Integer) entry.getValue();
            topology.addConnection(joinee, poorestNeighbour);
        }
        return topology;
    }

}
