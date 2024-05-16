package webpackage;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.util.*;

public class ClosenessCentrality {

    // Takes in a graph and goes through every node in it, and finds its closeness
    // centrality value. Sorts it, and returns it with its node reference
    public Map<String, Double> CloseCentral(Graph graph) {
        Map<String, Double> toReturn = new HashMap<>();

        for (Node node : graph) {
            List<Integer> shortestPaths = ShortestPath(graph, node);
            double closeCent = calculateCloseCent(shortestPaths);
            toReturn.put(node.getId(), closeCent);
        }

        toReturn = Sort(toReturn);
        return toReturn;
    }

    // finds the shortest path from the passed in starting node, to every
    // other node in the graph, and puts it in a list to return. Should be
    // BFS
    public List<Integer> ShortestPath(Graph graph, Node start) {
        List<Integer> shortestPaths = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        Map<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(start, 0);
        queue.add(start);

        while (!queue.isEmpty()) {
            Node currNode = queue.poll();
            for (org.graphstream.graph.Edge edge : currNode.leavingEdges().toList()) {
                Node neighbor = edge.getTargetNode();
                if (!distanceMap.containsKey(neighbor)) {
                    distanceMap.put(neighbor, distanceMap.get(currNode) + 1);
                    queue.add(neighbor);
                }
            }
        }

        for (Node node : graph) {
            if (node.equals(start)) {
                shortestPaths.add(0);
            } else {
                shortestPaths.add(distanceMap.getOrDefault(node, Integer.MAX_VALUE));
            }
        }

        return shortestPaths;
    }

    // calculates the closeness from the distance values of node u to nodes v
    public double calculateCloseCent(List<Integer> vals) {
        double closeCentVal =  0.0;
        int total = 0;
        int noPath = 0;
        for (Integer val : vals) {
            if (val != Integer.MAX_VALUE) {
                total += val;
            } else {
                noPath++;
            }
        }

        if (noPath == vals.size() - 1) {
            return 0.0;
        } else if (noPath > 0) {
            if (noPath > (vals.size() / 4)* 3) {
                return 0.0;
            } else {
                closeCentVal = (double) ((vals.size() - 1) - noPath) / total;
            }
        } else {
            closeCentVal = (double) (vals.size() - 1) / total;
        }

        return closeCentVal;
    }

    // used to sort the map in descending order
    public Map<String, Double> Sort(Map<String, Double> toSort) {
        List<Map.Entry<String, Double>> list = new ArrayList<>(toSort.entrySet());
        list.sort(Map.Entry.<String, Double>comparingByValue().reversed());
        Map<String, Double> sortedCloseCentral = new LinkedHashMap<>();

        for (Map.Entry<String, Double> curr : list) {
            sortedCloseCentral.put(curr.getKey(), curr.getValue());
        }

        return sortedCloseCentral;
    }
}

