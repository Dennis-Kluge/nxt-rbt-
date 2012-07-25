package nxt.rbt.navigation;

import java.io.OutputStream;

import lejos.robotics.localization.PoseProvider;
import nxt.rbt.graph.DirectionStates;
import nxt.rbt.graph.Graph;
import nxt.rbt.graph.Node;

public class LabyrinthNavigator {

	private Graph graph;	

	private PoseProvider poseProvider;	
	
	boolean turned;

	public LabyrinthNavigator(PoseProvider poseProvider) {			
		this.poseProvider = poseProvider;		
		graph = new Graph();
		graph.addEndNode(poseProvider.getPose().getX(), poseProvider.getPose().getY());

		turned = false;
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
		turned = true;
		for (Node vertex : graph.getVertexes()) {
			vertex.turnDirections();
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
}
