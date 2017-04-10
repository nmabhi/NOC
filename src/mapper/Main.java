package mapper;

import java.io.File;
import java.util.Scanner;


/**
 * Created by Xorcist on 10-04-2017.
 */
public class Main {

    public static void main(String[] args) {
        File dir = new File("../");
        System.out.print(dir.listFiles());
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

        Graph graph = new Graph();
        for (int m = 0; m < M; m++) {
            Integer n1 = input.nextInt();
            Integer n2 = input.nextInt();
            Integer w = input.nextInt();
            Edge edge = new Edge();
            edge.node1 = n1;
            edge.node2 = n2;
            edge.weight = w;
            graph.insert(edge);
        }
        graph.printInputGraph();

        /*input graph taken.
        * Now applying any of the algorithm classes to generate topology.*/

        Topology topology = Basic_NonFaultTolerant.generateTopology(graph);
        //print the topology:-
        topology.printTopology();

        //Test this topology:-
        TopologyEvaluator.commCost(graph, topology);
    }

}
