package nxt.rbt.graph;

import java.util.ArrayList;
import java.util.List;

import nxt.rbt.navigation.Line;

public class Graph {
	
	private List<Node> nodes;
	private List<Edge> edges;
	
	private Node currentNode;
	private int nodeCounter;

	private int edgeCounter;
	
	private Line line;		
	
	private final float NODE_TOLERANCY = 6;

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
	
	public Edge getCurrentEdge() {
		return edges.get(edges.size()-1);
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
		
		possibleDirections[1].edge = edge;
		edges.add(edge);
		nodes.add(node);
		
		// prepare next addition
		nodeCounter++;
		edgeCounter++;
		currentNode = node;
	}
	
	public Node nodeExists(float x, float y) {
		for(Node node : nodes) {
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
		
		// prepare next addition
		nodeCounter++;
		edgeCounter++;
		if(isStartNode) {
			currentNode = node;
		}
	}
	
	public boolean isGraphfinished() {
		for(Node node : nodes) {
			if(!node.isEnding()) {
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
	
	public Node getNodeToFinish() {
		for(Node node : nodes) {
			if(!node.isEnding()) {
				for(int i = 0; i < node.getDirections().length; i++) {
					Direction direction = node.getDirections()[i];
					if(direction.getDirectionState() == DirectionStates.POSSIBLE) {
						return node;
					}
				}
			}
		}
		return null;
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

	public Node getStartNode() {
		for(Node node : nodes) {
			if(node.isStartNode()) {
				return node;
			}
		}
		return null;
	}

	public Node getFinishNode() {
		for(Node node : nodes) {
			if(node.isFinishNode()) {
				return node;
			}
		}
		return null;
	}	
}
