package nxt.rbt.graph;

import java.util.ArrayList;
import java.util.List;

import nxt.rbt.communication.BluetoothConnector;
import nxt.rbt.navigation.Line;

public class Graph {
	
	private List<Node> nodes;
	private List<Edge> edges;
	
	private Node currentNode;
	private int nodeCounter;

	private int edgeCounter;
	
	private Line line;		
	
	private final float NODE_TOLERANCY = 10;

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
	
	public DirectionStates[] getDirection(float x, float y) {
		Node node = nodeExists(x, y);
		if(node != null) {
			return node.getDirections();
		}
		return null;
	}
	
	public void updateDirectionsForNode(DirectionStates[] states, float x, float y) {
		Node node = nodeExists(x, y);
		if(node != null) {
			node.setDirection(states);
		}
	}
	
	public void addNode(DirectionStates[] possibleDirections, float x, float y) {
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
		String message = currentNode.getId() + " " + node.getId() + " " + distance + ",";
		BluetoothConnector.getInstance().sendMessage(message);
		
		// prepare next addition
		edgeCounter++;
		currentNode = node;
	}
	
	public Node nodeExists(float x, float y) {
		for(Node node : nodes) {
			if((node.getX() > x + NODE_TOLERANCY || node.getX() < x - NODE_TOLERANCY) 
			    && (node.getY() > x + NODE_TOLERANCY || node.getY() < x - NODE_TOLERANCY)) {
				return node;
			}
		}
		return null;
	}
	
	public void addEndNode(float x, float y) {
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
		edges.add(edge);
		nodes.add(node);
		
		// bluetooth messaging
		String message = currentNode.getId() + " " + node.getId() + " " + distance + ",";
		BluetoothConnector.getInstance().sendMessage(message);
		
		// prepare next addition
		edgeCounter++;		
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
