package nxt.rbt.graph;

import java.util.ArrayList;
import java.util.List;

import lejos.nxt.comm.RConsole;

import nxt.rbt.communication.BluetoothConnector;
import nxt.rbt.navigation.Line;

public class Graph {
	
	private List<Node> nodes;
	private List<Edge> edges;
	
	private Node currentNode;
	private int nodeCounter;

	private int edgeCounter;
	
	private Line line;		
	
	private final float NODE_TOLERANCY = 7;

	public Graph() {		
		this.line = new Line();
		this.nodes = new ArrayList<Node>();
		this.edges = new ArrayList<Edge>();
		
		nodeCounter = 0;
		edgeCounter   = 0;
	}
	
	public Node getLastNode() {
		return nodes.get(nodes.size()-2);
	}
	
	public Node getCurrentNode() {
		return nodes.get(nodes.size()-1);
	}
	
	public Direction[] getDirection(float x, float y) {
		Node node = nodeExists(x, y);
		if(node != null) {
			return node.getDirections();
		}
		return null;
	}
	
	public void updateDirectionsForNode(Direction[] states, float x, float y) {
		Node node = nodeExists(x, y);
		if(node != null) {
			node.setDirection(states);
		}
	}
	
	public void addNode(Direction[] possibleDirections, float x, float y) {
		float distance = line.getDistance();
		line.clearLine();
		// does node exist
		Node node;
		Node exist = nodeExists(x, y);
		if(exist != null) 
			node = exist;
		else
			node = new Node(Integer.toString(nodeCounter), Integer.toString(nodeCounter), possibleDirections, x, y);
		
		Edge edge = new Edge(Integer.toString(edgeCounter), currentNode, node, distance);
		
		edges.add(edge);
		nodes.add(node);
		
		// bluetooth messaging
		String message;
		if(currentNode == null)
			message = -1 + " " + node.getId() + " " + distance + ",";
		else 
			message = currentNode.getId() + " " + node.getId() + " " + distance + ",";
		BluetoothConnector.getInstance().sendMessage(message);
		
		// prepare next addition
		nodeCounter++;
		edgeCounter++;
		currentNode = node;
	}
	
	public Node nodeExists(float x, float y) {
		RConsole.println("Ausgabe: aktueller Node: " + x+ " , "+ y);
//		System.out.println("Ausgabe: aktueller Node: " + x+ " , "+ y);
		int i= 1;
		for(Node node : nodes) {
			RConsole.println("Ausgabe: node "+i+": "+ node.getX() + " , " + node.getY());
			i++;
//			System.out.println("Ausgabe: node "+i+": "+ node.getX() + " , " + node.getY());
			if((node.getX() + NODE_TOLERANCY > x && node.getX() - NODE_TOLERANCY  < x || node.getX() == x) 
			    && (node.getY() + NODE_TOLERANCY  > y && node.getY() - NODE_TOLERANCY < y  || node.getY() == y)) {
				return node;
			}
		}
		return null;
	}
	
	public void addEndNode(float x, float y, boolean isStartNode, boolean isFinishNode) {
		float distance = line.getDistance();
		line.clearLine();
		// does node exist
		Node node;
		Node exist = nodeExists(x, y);
		if(exist != null) 
			node = exist;
		else
			node = new Node(Integer.toString(nodeCounter), Integer.toString(nodeCounter), x, y);				
		
		Edge edge = new Edge(Integer.toString(edgeCounter), currentNode, node, distance);
		node.setEnding(true);
		node.setStartNode(isStartNode);
		node.setFinishNode(isFinishNode);
		edges.add(edge);
		nodes.add(node);
		
		// bluetooth messaging
		String message = "";
		if(currentNode == null)
			message = -1 + " " + node.getId() + " " + distance + ",";
		else 
			message = currentNode.getId() + " " + node.getId() + " " + distance + ",";
		BluetoothConnector.getInstance().sendMessage(message);
		
		// prepare next addition
		nodeCounter++;
		edgeCounter++;		
	}
	
	public boolean isGraphfinished() {
		for(Node node : nodes) {
			if(!node.isEnding) {
				for(int i = 0; i < node.getDirections().length; i++) {
					Direction direction = node.getDirections()[i];
					if(direction.getDirectionState() != DirectionStates.TAKEN) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public void addPoint(float x, float y) {
		line.addPoint(x, y);
	}
	
	public List<Node> getVertexes() {
		return nodes;
	}

	public List<Edge> getEdges() {
		return edges;
	}	
}
