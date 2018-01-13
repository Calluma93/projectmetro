/**
 * Boston Metro
 * GraphImplementation.java
 * Purpose: Manages the Graph and finds paths between nodes
 * 
 * @author Team6(Callum Anderson, Declan Neilson, Eimantas Peckys, Jonathan Baird, Scott Ellis)
 * @version 1.11
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class GraphImplementation implements MultiGraph {

	private List<Edge> edgeList;
	private Map<Integer, Node> nodeList;

	/**
	 * Constructs the graph by initialising the edgeList as an ArrayList of Edges and nodeList as a Hashmap of (Integer, Node)
	 */
	public GraphImplementation() {
		edgeList = new ArrayList<Edge>();
		nodeList = new HashMap<Integer, Node>();
	}
	
	@Override
	public void setEdgeList(List<Edge> value) {
		edgeList = value;
	}

	@Override
	public List<Edge> getEdgeList() {
		return edgeList;
	}
	

	@Override
	public void setNodeList(Map<Integer, Node> value) {
		nodeList = value;
	}

	@Override
	public Map<Integer, Node> getNodeList() {
		return nodeList;
	}



	@Override
	public void addEdge(String label, int node1, int node2) {
		Node n1 = nodeList.get(node1);
		Node n2 = nodeList.get(node2);
		
		edgeList.add(new EdgeImplementation(label, n1, n2));
	}

	@Override
	public void addNode(int index, String label) {
		nodeList.put(index, new NodeImplementation(label));
	}
	

	@Override
	public Node getNodeByIndex(int index) {
		return nodeList.get(index);
	}



	@Override
	public List<Edge> findPath(Node startNode, Node endNode) {

		// Declare Data Structures
		Map<Node, Node> searchTable = new HashMap<Node, Node>();
		Queue<Node> queue = new LinkedList<Node>();
		Stack<Edge> path = new Stack<Edge>();
		List<Node> visited = new ArrayList<Node>();

		// Initialise Search Table and Queue
		queue.add(startNode);

		// Populate Search Table until Target Found
		while (!(queue.peek()==endNode)){
			Node currentNode = queue.poll();
			visited.add(currentNode);
			// For Each node in the queue, add its adjacent nodes to the
			// searchTable and queue
			List<Edge> nodeEdges = getNodeEdges(currentNode);
			for (Edge edge : nodeEdges) {
				if(!(visited.contains(edge.getNodeB()))){
					if(!searchTable.containsKey(edge.getNodeB())){
						//If the node has not been visited or recorded in the searchTable add it to the searchTable
							searchTable.put(edge.getNodeB(), edge.getNodeA());
					}
					//Add the node to the queue for processing
					queue.add(edge.getNodeB());
				}
			}
			if(queue.isEmpty()){
				//if the queue is empty there was no path
				return null;
			}
		}
		for (Node node = endNode; node != startNode;) {
			//reverse load the path into the stack and return
			path.add(getEdgeByNodes(node, searchTable.get(node)));
			//Use the searchTable to get the previousNode
			node = searchTable.get(node);
		}
		// Get the path from the SearchTable and return
		return path;
	}

	
	/**
	 * Returns all the edges which contain the specified node
	 * @param node	the node that should be contained in the edge
	 * @return a list of edges coming from the node
	 */
	public List<Edge> getNodeEdges(Node node) {
		List<Edge> FilteredEdgeList = new ArrayList<Edge>();
		for (Edge edge : edgeList) {
			if (edge.getNodeA() == node) {
				FilteredEdgeList.add(edge);
			}
		}
		return FilteredEdgeList;
	}

	/**
	 *Gets all edges containing both nodeA and nodeB
	 * 
	 * @param nodeA	the first node of the edge
	 * @param nodeB the second node of the edge
	 * @return the edge which contains both of the nodes
	 */
	public Edge getEdgeByNodes(Node nodeA, Node nodeB) {
		List<Edge> nodeAEdges = getNodeEdges(nodeA);
		for (Edge edge : nodeAEdges) {
			if (edge.getNodeB() == nodeB) {
				return edge;
			}
		}
		return null;
	}

	
	
}
