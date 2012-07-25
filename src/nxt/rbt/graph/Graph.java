package nxt.rbt.graph;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import nxt.rbt.navigation.Line;

public class Graph {
	
	private List<Node> nodes;
	private List<Edge> edges;
	
	private Node currentVertex;
	private int vertexCounter;

	private int edgeCounter;
	
	private Line line;
	
	private OutputStream outputStream;
	
	private final float NODE_TOLERANCY = 10;

	public Graph(OutputStream outputStream) {
		this.outputStream = outputStream;
		this.line = new Line();
		this.nodes = new ArrayList<Node>();
		this.edges = new ArrayList<Edge>();
		
		vertexCounter = 0;
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
			node = new Node(Integer.toString(vertexCounter), Integer.toString(vertexCounter), possibleDirections, x, y);
		
		Edge edge = new Edge(Integer.toString(edgeCounter), currentVertex, node, distance);
		
		edges.add(edge);
		nodes.add(node);
		
		// bluetooth messaging
		String message = currentVertex.getId() + " " + node.getId() + " "
				+ distance + ",";
		try {
			outputStream.write(message.getBytes());
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// prepare next addition
		edgeCounter++;
		currentVertex = node;
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
			node = new Node(Integer.toString(vertexCounter), Integer.toString(vertexCounter), x, y);				
		
		Edge edge = new Edge(Integer.toString(edgeCounter), currentVertex, node, distance);
		node.setEnding(true);		
		edges.add(edge);
		nodes.add(node);
		
		// bluetooth messaging
		String message = currentVertex.getId() + " " + node.getId() + " "
				+ distance + ",";
		try {
			outputStream.write(message.getBytes());
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
