package mapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Created by Xorcist on 10-04-2017.
 */
public class Main_1 {

    public static void main(String[] args) {
        /*
        * Each file starts with an integer :- the number of nodes N. Each of the next N lines have N columns each, forming and NxN table.
        * The entry at ith column and jth row signifies the communication bandwidth requirements between the nodes i and j.
        * If the entry is 'INF' tht signifies there is no connection between the two nodes.
        * Eg.:-
16
0  70 INF INF INF INF INF INF INF INF INF INF INF INF INF INF
70   0 362 INF INF INF INF INF INF INF INF INF INF INF INF INF
INF 362   0 362 INF INF INF INF INF INF INF INF INF INF INF INF
INF INF 362   0 362 INF INF INF INF INF INF INF INF INF INF  49
INF INF INF 362   0 357 INF INF INF INF INF INF INF INF INF  27
INF INF INF INF 357   0 353 INF INF INF INF  16 INF INF INF INF
INF INF INF INF INF 353   0 300 INF INF INF INF INF INF INF INF
INF INF INF INF INF INF 300   0 313 500 INF INF INF INF INF INF
INF INF INF INF INF INF INF 313   0 407 INF  16 INF INF INF INF
INF INF INF INF INF INF INF 500 407   0 INF INF INF INF INF INF
INF INF INF INF INF INF INF INF INF INF   0  16 INF INF  16 INF
INF INF INF INF INF  16 INF INF  16 INF  16 0    16 INF INF INF
INF INF INF INF INF INF INF INF INF INF INF  16   0 157  16 INF
INF INF INF INF INF INF INF INF INF INF INF INF 157   0  16 INF
INF INF INF INF INF INF INF INF INF INF  16 INF  16  16   0 INF
INF INF INF  49  27 INF INF INF INF INF INF INF INF INF INF   0

          */
        for (int serialNo = 1; serialNo <= 43; serialNo++) {
            if (serialNo == 16 || serialNo == 24){
                continue;
            }
            //taking input from a file and output to a file:
            try {
                System.setIn(new FileInputStream(new File("resources/inputGraphs/Graph" + serialNo + ".txt")));
                File out = new File("resources/outputGraphs/Graph" + serialNo + "Output.txt");
                out.createNewFile();
                System.setOut(new PrintStream(out));
            } catch (Exception e) {
                String ex = e.getMessage();
            }
            //comment out the above code to not redirect stdIn/stdOut from/to a file.


            // taking input from stdIn:
            Scanner input = new Scanner(System.in);
            Graph graph = new Graph();

            int N = input.nextInt();
            for (int n = 1; n <= N; n++) {
                graph.numberOfNodes = N;
                for (int m = 1; m <= N; m++) {
                    if (input.hasNextDouble() && m < n) {
                        Double w = input.nextDouble();
                        Edge edge = new Edge(m, n, w);
                        graph.insert(edge);
                    }else input.next();

                }
            }

            //Input graph(s) taken.

            //to print all graphs:
       /* for (Graph graph : graphList) {
            graph.printInputGraph();
        }*/

            // Now applying any of the algorithm classes to generate topology.*/


            System.out.println("For graph " + serialNo);
            graph.printInputGraph();
            System.out.println();
            //Topology generator 1:
            System.out.println("Using topology generator: " + Native_GraphTopology.algorithm);
            Topology topology = Native_GraphTopology.generateTopology(graph);
            //print the topology:-
            System.out.print("Topology: ");
            topology.printTopology();
            //Test this topology:-
            System.out.println("Communication cost: " + TopologyEvaluator.commCost(graph, topology));
            System.out.println("Link utilization graph: " + TopologyEvaluator.linkUtilizationGraph(graph, topology));
            System.out.println("Average link utilization: " + TopologyEvaluator.avgLinkUtilization(graph, topology));
            System.out.println("Fault tolerant links: " + TopologyEvaluator.printFaultTolerantLinks(graph, topology));
            System.out.println("Link fault tolerance: " + TopologyEvaluator.linkFaultTolerance(graph, topology) + "%");
            System.out.println();

            //Topology generator 2:
            System.out.println("Using topology generator: " + Basic_NonFaultTolerant.algorithm);
            topology = Basic_NonFaultTolerant.generateTopology(graph);
            //print the topology:-
            System.out.print("Topology: ");
            topology.printTopology();
            //Test this topology:-
            System.out.println("Communication cost: " + TopologyEvaluator.commCost(graph, topology));
            System.out.println("Link utilization graph: " + TopologyEvaluator.linkUtilizationGraph(graph, topology));
            System.out.println("Average link utilization: " + TopologyEvaluator.avgLinkUtilization(graph, topology));
            System.out.println("Fault tolerant links: " + TopologyEvaluator.printFaultTolerantLinks(graph, topology));
            System.out.println("Link fault tolerance: " + TopologyEvaluator.linkFaultTolerance(graph, topology) + "%");
            System.out.println();

            //Topology generator 3:
            System.out.println("Using topology generator: " + PoorestNeighbour_LinkFaultTolerant.algorithm);
            topology = PoorestNeighbour_LinkFaultTolerant.generateTopology(graph);
            //print the topology:-
            System.out.print("Topology: ");
            topology.printTopology();
            //Test this topology:-
            System.out.println("Communication cost: " + TopologyEvaluator.commCost(graph, topology));
            System.out.println("Link utilization graph: " + TopologyEvaluator.linkUtilizationGraph(graph, topology));
            System.out.println("Average link utilization: " + TopologyEvaluator.avgLinkUtilization(graph, topology));
            System.out.println("Fault tolerant links: " + TopologyEvaluator.printFaultTolerantLinks(graph, topology));
            System.out.println("Link fault tolerance: " + TopologyEvaluator.linkFaultTolerance(graph, topology) + "%");
            System.out.println();

            //Topology generator 4:
            System.out.println("Using topology generator: " + DeBruijn_LinkFaultTolernt.algorithm);
            topology = DeBruijn_LinkFaultTolernt.generateTopology(graph);
            //print the topology:-
            System.out.print("Topology: ");
            topology.printTopology();
            //Test this topology:-
            System.out.println("Communication cost: " + TopologyEvaluator.commCost(graph, topology));
            System.out.println("Link utilization graph: " + TopologyEvaluator.linkUtilizationGraph(graph, topology));
            System.out.println("Average link utilization: " + TopologyEvaluator.avgLinkUtilization(graph, topology));
            System.out.println("Fault tolerant links: " + TopologyEvaluator.printFaultTolerantLinks(graph, topology));
            System.out.println("Link fault tolerance: " + TopologyEvaluator.linkFaultTolerance(graph, topology) + "%");
            System.out.println();
        }

    }

}
