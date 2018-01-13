/**
 * Boston Metro
 * MultiGraph.java
 * Purpose: The interface which controls the management of the graph
 * 
 * @author Team6(Callum Anderson, Declan Neilson, Eimantas Peckys, Jonathan Baird, Scott Ellis)
 * @version 1.6
 */
import java.util.List;
import java.util.Map;

public interface MultiGraph {

	/**
	 * Assigns a list to edgeList
	 * 
	 * @param value the List of edge to be assigned
	 */
	public void setEdgeList(List<Edge> value);

	/**
	 * Get the List of Edges that make up the Graph
	 * 
	 * @return edgeList List of all Edges in the Graph
	 */
	public List<Edge> getEdgeList();

	/**
	 * Assigns a list to nodeList
	 * 
	 * @param value the List of nodes to be assigned
	 */
	public void setNodeList(Map<Integer, Node> value);

	/**
	 * Get the List of Nodes that make up the Graph
	 * 
	 * @return nodeList List of all Nodes in the Graph
	 */
	public Map<Integer, Node> getNodeList();
	
	/**
	 * Constructs and adds an Edge to the edgeList.
	 * 
	 * @param label as String used in the construction of the Edge
	 * @param nodeIndex1 as Int refers to the index of the nodeA object in the edge. Used in the construction of the Edge
	 * @param nodeIndex2 as Int refers to the index of the nodeB object in the edge. Used in the construction of the Edge
	 */
	public void addEdge(String label, int nodeIndex1, int nodeIndex2);
	
	/**
	 * Constructs and adds a Node to the nodeList
	 * @param index as Int refers to the index of this node. Used in the construction of the Node
	 * @param label as String is the label of this node.  Used in the construction of the Node
	 */
	public void addNode(int index, String label);
	
	/**
	 * Find node object from nodeList at position index and returns it 
	 * @param index as Int the position of the node you would like
	 * @return node the node found at the position of the nodeList specified
	 */
	public Node getNodeByIndex(int index);
	
	/**
	 * Returns a list of edges which create the shortest path from one node to another
	 * 
	 * @param	start	the node from which the path should start
	 * @param	end		the node at which the path should end
	 * @return	path		the list of edges which make up the path. If path could not be found null returned
	 */
	List<Edge> findPath(Node start, Node end);


}
