/**
 * Boston Metro
 * Edge.java
 * Purpose: An interface which controls the edges of a Graph
 * Description: As in node, it is an interface that outlines an �edge� object in a graph, it stores a label of the edge
 * to allow for edge uniqueness in a multigraph. Our implementation of the multigraph is that we decided to store the in
 * and out node (if present) inside the edge.
 * 
 * @author Team6(Callum Anderson, Declan Neilson, Eimantas Peckys, Jonathan Baird, Scott Ellis)
 * @version 1.5
 */
public interface Edge {
	
	/**
	 * Assigns a string to Label
	 * 
	 * @param value As String
	 */
	public void setLabel(String value);

	/**
	 * Returns the Label attribute of the Edge
	 * 
	 * @return Label as String
	 */
	public String getLabel();

	/**
	 * Return the NodeA object of the edge
	 * 
	 * @return NodeA As Node
	 */
	public Node getNodeA();

	/**
	 * Return the NodeB attribute of the edge
	 * 
	 * @return NodeB As Node
	 */
	public Node getNodeB();

	/**
	 * Assigns a node to NodeA
	 * 
	 * @param node As Node
	 */
	public void setNodeA(Node node);

	/**
	 * Assignes a node to NodeB
	 * 
	 * @param node As Node
	 */
	public void setNodeB(Node node);
}
