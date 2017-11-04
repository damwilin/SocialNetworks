/**
 *
 */
package graph;

import java.util.*;

/**
 * @author Damian Wilinski.
 *         <p>
 *         For the warm up assignment, you must implement your Graph in a class
 *         named CapGraph.  Here is the stub file.
 */
public class CapGraph implements Graph {

    int graphMapSize = 0;
    int edgeSize = 0;
    private HashMap<Integer, HashSet<Integer>> graphMap = new HashMap<Integer, HashSet<Integer>>();

    /* (non-Javadoc)
     * @see graph.Graph#addVertex(int)
     */
    @Override
    public void addVertex(int num) {
        if (!graphMap.containsKey(num)) {
            graphMap.put(num, new HashSet<Integer>());
            graphMapSize++;
        }
    }

    /* (non-Javadoc)
     * @see graph.Graph#addEdge(int, int)
     */
    @Override
    public void addEdge(int from, int to) {
        if (!graphMap.containsKey(from))
            throw new IllegalArgumentException("There is no: " + from);
        else if (!graphMap.containsKey(to))
            throw new IllegalArgumentException("There is no: " + to);
        graphMap.get(from).add(to);
        edgeSize++;
    }

    /* (non-Javadoc)
     * @see graph.Graph#getEgonet(int)
     */
    @Override
    public Graph getEgonet(int center) {
        if (!graphMap.containsKey(center))
            throw new IllegalArgumentException("There is no vertex with number: " + center);
        //Add center and neighbors with edges to graph
        Graph egonetGraph = addNodeAndNeighborsToGraph(center);
        return egonetGraph;
    }

    private Graph addNodeAndNeighborsToGraph(int center) {
        CapGraph egonetGraph = new CapGraph();
        egonetGraph.addVertex(center);
        HashSet<Integer> centerNeighbor = graphMap.get(center);
        for (Integer currVertex : centerNeighbor) {
            egonetGraph.addVertex(currVertex);
            egonetGraph.addEdge(center, currVertex);
        }
        for (Integer currNeighbor : centerNeighbor) {
            for (Integer k : centerNeighbor) {
                if (graphMap.get(currNeighbor).contains(k))
                    egonetGraph.addEdge(currNeighbor, k);
            }
        }
        return egonetGraph;
    }

    /* (non-Javadoc)
     * @see graph.Graph#getSCCs()
     */
    @Override
    public List<Graph> getSCCs() {
        Stack<Integer> vertices = new Stack<Integer>();
        List<Graph> SCCsList = new ArrayList<Graph>();
        vertices.addAll(graphMap.keySet());
        Stack<Integer> finished = dfs(false, vertices, graphMap, SCCsList);
        HashMap<Integer, HashSet<Integer>> reverseMap = reverseEdges(graphMap);
        vertices.removeAllElements();
        vertices.addAll(finished);
        dfs(true, vertices, reverseMap, SCCsList);

        return SCCsList;
    }

    private Stack<Integer> dfs(boolean isReverse, Stack<Integer> vertices, HashMap<Integer, HashSet<Integer>> graphMap, List<Graph> SCCsList) {
        HashSet<Integer> visited = new HashSet<Integer>();
        Stack<Integer> finished = new Stack<Integer>();

        while (!vertices.empty()) {
            int currVer = vertices.pop();
            if (!visited.contains(currVer)) {
                Graph currSCCGraph = new CapGraph();
                if (isReverse)
                    currSCCGraph.addVertex(currVer);
                dfsVisit(isReverse, vertices, currVer, visited, finished, graphMap, currSCCGraph);
                if (isReverse)
                    SCCsList.add(currSCCGraph);
            }
        }

        return finished;
    }

    private void dfsVisit(boolean isReverse, Stack<Integer> vertices, int currVer, HashSet<Integer> visited, Stack<Integer> finished, HashMap<Integer, HashSet<Integer>> graphMap, Graph currSCCGraph) {
        visited.add(currVer);
        for (Integer currNeighbor : graphMap.get(currVer)) {
            if (!visited.contains(currNeighbor)) {
                if (isReverse) {
                    currSCCGraph.addVertex(currNeighbor);
                    currSCCGraph.addEdge(currVer, currNeighbor);
                }
                dfsVisit(isReverse, vertices, currNeighbor, visited, finished, graphMap, currSCCGraph);
            }
            finished.push(currVer);
        }
    }

    private HashMap<Integer, HashSet<Integer>> reverseEdges(HashMap<Integer, HashSet<Integer>> graphMap) {
        HashMap<Integer, HashSet<Integer>> reverseMap = new HashMap<Integer, HashSet<Integer>>();
        for (Integer currInt : graphMap.keySet()) {
            reverseMap.put(currInt, new HashSet<Integer>());
        }
        for (Integer currInt : graphMap.keySet()) {
            for (Integer currNeighbor : graphMap.get(currInt)) {
                if (reverseMap.containsKey(currNeighbor))
                    reverseMap.get(currNeighbor).add(currInt);
            }
        }
        return reverseMap;
    }

    /* (non-Javadoc)
     * @see graph.Graph#exportGraph()
     */
    @Override
    public HashMap<Integer, HashSet<Integer>> exportGraph() {
        return graphMap;
    }

    public int getGraphSize() {
        return graphMapSize;
    }

    public boolean isVertex(int vertex) {
        return graphMap.containsKey(vertex);
    }

    public boolean isEdge(int from, int to) {
        if (!graphMap.containsKey(from) || !graphMap.containsKey(to))
            throw new IllegalArgumentException("There is no vertex: " + from + " " + to);
        if (graphMap.get(from).contains(to))
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Integer currV : graphMap.keySet()) {
            sb.append(currV + " : ");
            for (Integer currVNeighbor : graphMap.get(currV)) {
                sb.append(currVNeighbor + ", ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
