/**
 * Boston Metro
 * EdgeImplementation.java
 * Purpose: Manages an Edge and connects Nodes
 * Description: Implementation of the Edge interface into a usable object, the label in the final implementation is used
 * to identify the "line colour" and it contains accessor functions for setting and getting the edges nodes.
 * 
 * @author Team6(Callum Anderson, Declan Neilson, Eimantas Peckys, Jonathan Baird, Scott Ellis)
 * @version 1.7
 */
public class EdgeImplementation implements Edge {

	String Label;
	Node nodeA;
	Node nodeB;
	
	/**
	 * Constructs the edge object. Assigning parameters to the fields
	 * 
	 * @param Label as String - this will be assigned to the label field of this node
	 * @param nodeA as Node - this will be assigned to the nodeA field of this node
	 * @param nodeB as Node - this will be assigned to the nodeB field of this node
	 */
	public EdgeImplementation(String Label, Node nodeA, Node nodeB){
		this.Label = Label;
		this.nodeA = nodeA;
		this.nodeB = nodeB;	
	}
	@Override
	public void setLabel(String value) {
		Label = value;
	}

	@Override
	public String getLabel() {
		return Label;
	}

	@Override
	public Node getNodeA() {
		if(nodeA != null){
			return nodeA;
		}
		return new NodeImplementation("");
	}

	@Override
	public Node getNodeB() {
		if(nodeB != null){
			return nodeB;
		}
		return new NodeImplementation("");
	}

	@Override
	public void setNodeA(Node node) {
		nodeA = node;
	}

	@Override
	public void setNodeB(Node node) {
		nodeB = node;
	}
	
	/**
	 * Returns the label as a string representation of this edge
	 * 
	 * @return Label as String
	 */
	public String toString(){
		return Label;
	}

}
