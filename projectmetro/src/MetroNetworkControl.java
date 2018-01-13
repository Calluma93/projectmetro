/**
 * Boston Metro
 * MetroNetworkControl.java
 * Purpose: The User Interface for our program. Handles user input, calls commands and handles the printing of function outputs.
 * Description: Stores the main method and will implement a MetroNetwork object, interfacing with this will allow the
 * command line system to have direct access to anything it needs with easy decoupling.
 * 
 * @author Team6(Callum Anderson, Declan Neilson, Eimantas Peckys, Jonathan Baird, Scott Ellis)
 * @version 1.25
 */

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class MetroNetworkControl {

	MetroNetwork metroNetwork;
	Scanner input;

	/**
	 * Helper function to process the next line in from the Scanner into a more easily usable format
	 * @return an array of Strings
	 */
	private String[] takeLineString() {
		String currentLine = "";
		while (currentLine.length() == 0) {
			currentLine = input.nextLine().toLowerCase();
		}
		return currentLine.split(" ");
	}

	/**
	 * Takes in a map of Node objects which represents found stations and on what line, then prompts the user which
     * station they wish to choose and manages the chosen result via a number based selection system.
	 * 
	 * @param stationSearchResults as Map(Node,Edge) take the return of a getStationsFromString call @see MetroNetwork.getStationsFromString 
	 * @return returns the selected station
	 */
	private Node altstationsPrompt(HashMap<Node, Edge> stationSearchResults) {
		System.out.println("Multiple stations found please indicate your choice by typing in the number:");
		Map<Integer, Entry<Node, Edge>> choiceMap = new HashMap<Integer, Entry<Node, Edge>>();
		int i = 1;
		for (Entry<Node, Edge> n : stationSearchResults.entrySet()) {
			System.out.println("\t" + i + ": " + n.getKey() + " on line " + n.getValue());
			choiceMap.put(i, n);
			i++;
		}

		while (true) {
			System.out.print("Choose a number between 1 and " + stationSearchResults.size() + ": ");

			int choiceI = 0;
			String[] choice = takeLineString();

			if (choice.length != 1) {
				System.out.println("Invalid choice, please retry.");
				continue;
			}
			try {
				choiceI = Integer.parseInt(choice[0]);
			} catch (NumberFormatException e) {
				continue;
			}

			if (choiceI >= 1 && choiceI <= stationSearchResults.size()) {
				return choiceMap.get(choiceI).getKey();
			}
		}

	}
	
	/**
	 * Takes in user input and manages searching for a list of stations from a specified string, it then passes the list
     * of found stations to altstationsPrompt if more than one is found.
	 * @return returns the selected station
	 */
	private Node navPrompt() {
		while (true) {
			String[] sourceStation = takeLineString();

			if (sourceStation.length < 1)
				continue;

			HashMap<Node, Edge> altStationSuggestionList = metroNetwork.getStationsFromString(sourceStation[0]);
			if (altStationSuggestionList.isEmpty()) {
				System.out.println("No stations found with that name, please try a shorter term with no spaces.");
			} else {
				if (altStationSuggestionList.size() > 1) {
					try {
						return altstationsPrompt(altStationSuggestionList);
					} catch (Exception e) {
						System.out.println("Your input did not match an item on the list");
					}
				} else {
					return altStationSuggestionList.entrySet().iterator().next().getKey();
				}
			}
		}
	}

	/**
	 * Shortened function that prints out a short list detailing all available commands a user can input.
	 */
	private static void help() {
		System.out.println("--Command List--");
		System.out.println("navigate - Begins the navigation dialogue");
		System.out.println("help - Shows this list");
		System.out.println("exit - Exits the program");
		System.out.println("list - Prints the full list of all stations in the graph");
		System.out.println("--End Command List--");
	}

	/**
	 * Shortened function that prompts the user to enter a station to travel from and to, then it prints the directions
     * if the nodes aren't the same.
	 */
	private void navigate() {
		System.out.print("\tPlease input a station to navigate from: ");
		Node fromNode = navPrompt();
		System.out.print("\tPlease input a station to navigate to: ");
		Node toNode = navPrompt();

		// System.out.println("from: " + fromNode + " to: " +
		// toNode);

		if (!fromNode.equals(toNode)) {
			List<Edge> list = metroNetwork.findPath(fromNode, toNode);

			if (list.isEmpty()) {
				System.out.println("No path for this found");
			} else {
				printDirections(list);
			}

		} else {
			System.out.println("Can't path to the same station");
		}
	}
	
	private void list() {
		Map<Integer, Node> tempM = metroNetwork.getAllStations();
		
		System.out.println("Full list of all stations: ");
		
		for(Entry<Integer, Node> e : tempM.entrySet()){
			System.out.println(e.getKey() + ": " + e.getValue());
		}
	}

	/**
	 * Takes in a list of edges and creates shortened version of it by filtering out the stations which sequentially
     * have the same edge label. Returns new shortened list.
	 * @param list normal passed from Navigate
	 * @return return a list of lists of edges (grouped by consequetive edge with same line)
	 */
    private ArrayList<List<Edge>> shortenDirections(List<Edge> list){
        ArrayList<List<Edge>> groupedList = new ArrayList<List<Edge>>();
        List<Edge> group = new ArrayList<Edge>();
        for (int i = 0; i < list.size(); i++) {
            if (group.isEmpty()) {
                group.add(list.get(i));
            } else if (group.get(group.size() - 1).toString().equals(list.get(i).toString())) {
                group.add(list.get(i));
            } else {
                groupedList.add(group);
                group = new ArrayList<Edge>();
                group.add(list.get(i));
            }
            if (i == list.size() - 1) {
                groupedList.add(group);
            }
        }
        return groupedList;
    }

    /**
     * Takes in a list of edges and prints out a user readable list of directions.
     * @param list list of edge to be printed
     */
	private void printDirections(List<Edge> list) {

        ArrayList<List<Edge>> groupedList = shortenDirections(list);
		System.out
				.println("Routing you From " + list.get(list.size() - 1).getNodeB() + " to " + list.get(0).getNodeA());

		for (int i = groupedList.size() - 1; i >= 0; i--) {
			System.out.println("Take the " + groupedList.get(i).get(0).getLabel() + " line from "
					+ groupedList.get(i).get(groupedList.get(i).size() - 1).getNodeB() + " to "
					+ groupedList.get(i).get(0).getNodeA());
		}
	}

	/**
	 * The main method, uses self initialisation to instantiate the MetroNetworkControl object.
	 * @param args
	 */
	public static void main(String[] args) {
		new MetroNetworkControl();
	}

	/**
	 *  The constructor, initiates the command system and prompts the network to be populated.
	 */
	public MetroNetworkControl() {
		metroNetwork = new MetroNetwork();
		metroNetwork.MITFileParser(new File("bostonmetro.txt"));
		input = new Scanner(System.in);
		System.out.println("Welcome to the Metro. \nWhat service do you require? (Type help for service list)");
		boolean terminate = false;

		while (!terminate) {
			System.out.print("CMD: ");
			String[] inputL = takeLineString();

			if (inputL.length < 1)
				continue;

			switch (inputL[0]) {

			case "help":
				help();
				break;

			case "navigate":
				navigate();
				break;
				
			case "list":
				list();
				break;

			case "exit":
				System.exit(0);
				break;

			}

		}
	}



}
