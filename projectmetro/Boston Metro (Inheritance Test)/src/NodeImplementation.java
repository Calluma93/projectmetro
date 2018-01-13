/**
 * Boston Metro
 * NodeImplementation.java
 * Purpose: Manages an Node
 * 
 * @author Team6(Callum Anderson, Declan Neilson, Eimantas Peckys, Jonathan Baird, Scott Ellis)
 * @version 1.5
 */

public class NodeImplementation implements Node {
	private String label;
	
	public NodeImplementation(String label){
		this.label = label;
	}

	@Override
	public void setLabel(String value) {
		label = value;

	}

	@Override
	public String getLabel() {
		return label;
	}
	
	/**
	 * Returns the label as a string representation of this node
	 * 
	 * @return Label as String
	 */
	public String toString(){
		return label;
	}

}
