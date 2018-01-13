/**
 * Boston Metro
 * Node.java
 * Purpose: The interface which controls the nodes of a graph
 * 
 * @author Team6(Callum Anderson, Declan Neilson, Eimantas Peckys, Jonathan Baird, Scott Ellis)
 * @version 1.14
 */

public interface Node {
	/**
	 * Assigns a string to Label
	 * 
	 * @param value As String
	 */
	public void setLabel(String value);

	/**
	 * Returns the Label attribute of the node
	 * 
	 * @return Label as String
	 */
	public String getLabel();

}
