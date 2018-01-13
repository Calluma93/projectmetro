/**
 * Boston Metro
 * MetroNetwork.java
 * Purpose: Stores the GraphADT and Parses text files which generate the Graph
 * Description:  Is the first object that is the seperation between the adt and the actual metro system, will contain
 * object implementations of the graphimplementation. Has a function that can parse the list of boston metro lines into
 * a usable list of edges. Also contains a findPath func that takes strings and searches the edgelist to find nodes with
 * labels most closely matching the given strings.
 *
 * @author Team6(Callum Anderson, Declan Neilson, Eimantas Peckys, Jonathan Baird, Scott Ellis)
 * @version 1.20
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MetroNetwork {
	private MultiGraph network;

	/**
	 * A parser that takes in the text file listing all the stations, first turning it into a unique list of nodes and
	 * storing that in the Graph and then uses that list to produce a list of connected edges on the second pass.
	 * @param input as File - this is the file we would like to read the graph from
	 */
	public void MITFileParser(File input) {

		try {
			FileReader fr = null;
			try{
				fr = new FileReader(input);
			} catch (FileNotFoundException e2) {
				System.out.println("The file 'bostonmetro.txt' was not found. Please ensure 'bostonmetro.txt' containing" +
									" the metro representation is located in the same directory as the jar file/source dir");
				System.exit(0);
			}
			
			BufferedReader rd = new BufferedReader(fr);

			String l = rd.readLine();

			while (l != null) {
				String[] list = l.trim().split("[\\s 	]+");
				
				network.addNode(Integer.valueOf(list[0]), list[1]);

				l = rd.readLine();
			}

			rd.close();

			rd = new BufferedReader(new FileReader(input));

			l = rd.readLine();
			// this is the least elegant thing in the world but it works
			while (l != null) {
				String[] list = l.trim().split("[\\s 	]+");
				List<String> textEList = new LinkedList<String>(
						Arrays.asList(list));
				
				int curIndex = Integer.valueOf(textEList.get(0));
				
				textEList.remove(0);
				textEList.remove(0);
				
				while(textEList.size() > 0){ // edit this
					String label = textEList.get(0);
					int stIndexA = Integer.valueOf(textEList.get(1));
					int stIndexB = Integer.valueOf(textEList.get(2));
					
					if(stIndexA != 0){
						network.addEdge(label, curIndex, stIndexA);
					}
					
					if(stIndexB != 0){
						network.addEdge(label, curIndex, stIndexB);
					}

					textEList.remove(0);
					textEList.remove(0);
					textEList.remove(0);
				}
				
				l = rd.readLine();
			}

			rd.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * The constructor, instantiates the GraphImplementation object in use.
	 */
	public MetroNetwork() {
		network = new GraphImplementation();
	}

	/**
	 * Takes two nodes in and passes them to the GraphImplementation findPath method.
	 * @param start as Node - the node which the path should start from
	 * @param end as Node - the node which the path should finish at
	 * @return path as List(Edge) - the list of edges which make up the path from start to end
	 */
	public List<Edge> findPath(Node start, Node end) {
		return network.findPath(start, end);
	}

	/**
	 * Returns a Map of Nodes with labels containing the string specifed. If an exact match isn't found it'll try use
     * half the string to partially map it to a result.
	 * 
	 * @param str as String this is the Station name we would like to find
	 * @return a Map of Stations and their edges. (Incase multiple stations share the same name, we want to be able to identify them)
	 */
	public HashMap<Node,Edge> getStationsFromString(String str) {
		HashMap<Node,Edge> foundNodes = new HashMap<Node,Edge>();
		for (Edge edge : network.getEdgeList()) {
			Node nodeA = edge.getNodeA();
			Node nodeB = edge.getNodeB();

			if (nodeA.getLabel().toLowerCase().replace(" ", "").contains(str.toLowerCase())) {
				foundNodes.put(nodeA,edge);
			}
			if (nodeB.getLabel().toLowerCase().replace(" ", "").contains(str.toLowerCase())) {
				foundNodes.put(nodeB,edge);
			}
		}
		if(foundNodes.isEmpty()){
			for (Edge edge : network.getEdgeList()) {
				Node nodeA = edge.getNodeA();
				Node nodeB = edge.getNodeB();

				if (nodeA.getLabel().toLowerCase().contains(str.toLowerCase().substring(0, str.length()/2))) {
					foundNodes.put(nodeA,edge);
				}
				if (nodeB.getLabel().toLowerCase().contains(str.toLowerCase().substring(0, str.length()/2))){
					foundNodes.put(nodeB,edge);
				}
			}
		}
		return foundNodes;
	}
	
	public Map<Integer, Node> getAllStations(){
		return network.getNodeList();
	}

}
