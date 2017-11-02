/**
 *
 */
package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @author Damian Wilinski.
 *         <p>
 *         For the warm up assignment, you must implement your Graph in a class
 *         named CapGraph.  Here is the stub file.
 */
public class CapGraph implements Graph {

    private HashMap<Integer, HashSet<Integer>> graphMap = new HashMap<Integer, HashSet<Integer>>();
    int graphMapSize = 0;
    int edgeSize = 0;

    /* (non-Javadoc)
     * @see graph.Graph#addVertex(int)
     */
    @Override
    public void addVertex(int num) {
        if (!graphMap.containsKey(num))
            graphMap.put(num, new HashSet<Integer>());
        graphMapSize++;
    }

    /* (non-Javadoc)
     * @see graph.Graph#addEdge(int, int)
     */
    @Override
    public void addEdge(int from, int to) {
        if (!graphMap.containsKey(from) || !graphMap.containsKey(to))
            throw new IllegalArgumentException("There is no: " + from + " " + to);
        graphMap.get(from).add(to);
        edgeSize++;
    }

    /* (non-Javadoc)
     * @see graph.Graph#getEgonet(int)
     */
    @Override
    public Graph getEgonet(int center) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see graph.Graph#getSCCs()
     */
    @Override
    public List<Graph> getSCCs() {
        // TODO Auto-generated method stub
        return null;
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

}
