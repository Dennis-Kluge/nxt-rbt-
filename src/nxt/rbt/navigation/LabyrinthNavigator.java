package nxt.rbt.navigation;

import java.io.OutputStream;

import lejos.robotics.localization.PoseProvider;
import nxt.rbt.graph.DirectionStates;
import nxt.rbt.graph.Graph;
import nxt.rbt.graph.Node;

public class LabyrinthNavigator {

	private Graph graph;	

	private PoseProvider poseProvider;
	private OutputStream outputStream;
	
	boolean turned;

	public LabyrinthNavigator(PoseProvider poseProvider, OutputStream outputStream) {			

		this.poseProvider = poseProvider;
		this.outputStream = outputStream;
		graph = new Graph(outputStream);
		graph.addEndNode(poseProvider.getPose().getX(), poseProvider.getPose().getY());

	}

	public void addPoint() {		
		graph.addPoint(poseProvider.getPose().getX(), poseProvider.getPose().getY());		
	}		
	
	public void addNode(DirectionStates[] possibleDirections) {
		graph.addNode(possibleDirections, poseProvider.getPose().getX(), poseProvider.getPose().getY());
	}
	
	public void addEndNode() {
		graph.addEndNode(poseProvider.getPose().getX(), poseProvider.getPose().getY());	
	}
	
	public void turnDirection() {
		for (Node vertex : graph.getVertexes()) {
			vertex.turnDirections();
		}
	}
	
	public void turnNodesForDirection() {
		for (Node vertex : graph.getVertexes()) {
			vertex.turnNodes();
		}
	}
	
	public boolean hasNode() {
		if(graph.nodeExists(poseProvider.getPose().getX(), poseProvider.getPose().getY()) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public DirectionStates[] getDirections() {
		DirectionStates[] ret = graph.getDirection(poseProvider.getPose().getX(), poseProvider.getPose().getY());
		if(ret != null) {
			return ret;
		}
		return ret;
	}
	
	public void updateDirectionsForNode(DirectionStates[] states) {
		graph.updateDirectionsForNode(states, poseProvider.getPose().getX(), poseProvider.getPose().getY());
	}
	
	public Node getLastNode() {
		return graph.getLastNode();
	}
	
	public Node getCurrentNode() {
		return graph.getCurrentNode();
	}
}
