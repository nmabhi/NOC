package mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xorcist on 10-04-2017.
 */
public class Graph {
    public List<Edge> edges = new ArrayList<>();

    public void insert(Edge edge) {
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

    public void printInputGraph() {
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            System.out.println(edge.node1 + " - " + edge.node2 + " : " + edge.weight);
        }
    }
}
