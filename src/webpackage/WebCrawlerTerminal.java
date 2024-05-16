package webpackage;

import java.io.*;
import java.util.*;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

public class WebCrawlerTerminal extends ClosenessCentrality{

    private List<String> links = new ArrayList<>();
    private Graph graph;

    public static void main(String[] args) {
        WebCrawlerTerminal crawler = new WebCrawlerTerminal();
        crawler.start();
    }

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            // Prompt user for the links file
            System.out.println("Enter the path to the links file:");
            String linksFile = scanner.nextLine();

            // Read links from file
            readLinksFromFile(linksFile);

            // Prompt user for the depth
            System.out.println("Enter depth:");
            int depth = scanner.nextInt();

            // Compute closeness centrality
            Map<String, Double> closseness = crawl(depth);

            // Display the graph
            displayGraph();

            // Export results to CSV
            exportToCSV("output.csv", closseness);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readLinksFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                links.add(line);
            }
        }
    }

    private Map<String,Double> crawl(int depth) throws IOException {
        graph = new SingleGraph("Web Crawler Graph");

        List<List<String>> matrix = new ArrayList<>();
        List<String> nodes = new ArrayList<>();
        for (String url : links) {
            matrix = MakeMatrix.makeMatrix(0, depth, url, matrix, nodes);
        }

        Set<String> nodeIds = new HashSet<>();

        for (List<String> row : matrix) {
            String sourceNode = nodes.get(matrix.indexOf(row));

            if (!nodeIds.contains(sourceNode)) {
                graph.addNode(sourceNode);
                nodeIds.add(sourceNode);
            }

            for (String element : row) {
                if (!nodeIds.contains(element)) {
                    graph.addNode(element);
                    nodeIds.add(element);
                }

                if (!graph.getNode(sourceNode).hasEdgeBetween(element)) {
                    graph.addEdge(sourceNode + "-" + element, sourceNode, element, true);
                }
            }
        }
        // Returns the node and its closeness centrality value
        Map<String, Double> closeCentral = CloseCentral(graph);

        // Iterate over the entries of the map and print each entry's key-value pair
        for (Map.Entry<String, Double> entry : closeCentral.entrySet()) {
             System.out.println("Node: " + entry.getKey() + ", Closeness Centrality: " + entry.getValue());
        }
        return closeCentral;
    }

    private void displayGraph() {
        System.setProperty("org.graphstream.ui", "swing"); // Set the UI package
    
        System.out.println("Displaying the graph...");
    
        Viewer viewer = graph.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.CLOSE_VIEWER);
        graph.setAttribute("ui.quality");
        graph.setAttribute("ui.antialias");
    
        // Customize the color of the nodes
        for (Node node : graph) {
            node.setAttribute("ui.style", "fill-color: blue;");
        }
    
        System.out.println("Graph displayed successfully!");
    }

    private void exportToCSV(String filename, Map<String, Double> close) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("NodeID,ClosenessCentrality"); // Header for CSV file
            for (Map.Entry<String, Double> entry : close.entrySet()) {
                writer.println(entry.getKey() + "," + entry.getValue());
           }   
        }
    }
}
