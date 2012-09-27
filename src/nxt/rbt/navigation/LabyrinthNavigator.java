package nxt.rbt.navigation;

import java.util.LinkedList;

import lejos.robotics.localization.PoseProvider;
import nxt.rbt.communication.BluetoothConnector;
import nxt.rbt.graph.Direction;
import nxt.rbt.graph.Edge;
import nxt.rbt.graph.Graph;
import nxt.rbt.graph.Node;

public class LabyrinthNavigator {

	private Graph graph;	

	private PoseProvider poseProvider;	
	
	boolean turned;
	
	private LinkedList<Node> path;
	
	int currentPosNode;
	
	boolean navigateToNode;
	
	boolean navigateToFinish;

	

	public LabyrinthNavigator(PoseProvider poseProvider) {			
		this.poseProvider = poseProvider;		
		graph = new Graph();
		graph.addEndNode(poseProvider.getPose().getX(), poseProvider.getPose().getY(), true, false);
		path = new LinkedList<Node>();
		navigateToNode = false;
		navigateToFinish = false;
	}

	public boolean isNavigateToFinish() {
		return navigateToFinish;
	}

	public void setNavigateToFinish(boolean navigateToFinish) {
		this.navigateToFinish = navigateToFinish;
	}
	
	public int getCurrentPosNode() {
		return currentPosNode;
	}

	public void incrementCurrentPosNode() {
		this.currentPosNode++;
	}

	public boolean isNavigateToNode() {
		return navigateToNode;
	}

	public void setNavigateToNode(boolean navigateToNode) {
		this.navigateToNode = navigateToNode;
		currentPosNode = 0;
	}

	public LinkedList<Node> getPath() {
		return path;
	}

	public void setPath(LinkedList<Node> path) {
		this.path = path;
	}
	
	public void addPoint() {	
		BluetoothConnector.getInstance().sendMessage(poseProvider.getPose().getX() + " " + poseProvider.getPose().getY() + " " + "point,");
		graph.addPoint(poseProvider.getPose().getX(), poseProvider.getPose().getY());		
	}		
	
	public void addNode(Direction[] possibleDirections) {
		BluetoothConnector.getInstance().sendMessage(poseProvider.getPose().getX() + " " + poseProvider.getPose().getY() + " " + "node,");
		graph.addNode(possibleDirections, poseProvider.getPose().getX(), poseProvider.getPose().getY());
	}
	
	public void addEndNode(boolean isFinishNode) {
		BluetoothConnector.getInstance().sendMessage(poseProvider.getPose().getX() + " " + poseProvider.getPose().getY() + " " + "node,");
		graph.addEndNode(poseProvider.getPose().getX(), poseProvider.getPose().getY(), false, isFinishNode);	
	}
	
	
	public boolean hasNode() {
		if(graph.nodeExists(poseProvider.getPose().getX(), poseProvider.getPose().getY()) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public Direction[] getDirections() {
		Direction[] ret = graph.getDirection(poseProvider.getPose().getX(), poseProvider.getPose().getY());
		if(ret != null) {
			return ret;
		}
		return ret;
	}
	
	public void updateDirectionsForNode(Direction[] states) {
		graph.updateDirectionsForNode(states, poseProvider.getPose().getX(), poseProvider.getPose().getY());
	}
	
	public Node getLastNode() {
		return graph.getLastNode();
	}
	
	public Node getCurrentNode() {
		return graph.getCurrentNode();
	}
	
	public Node getNodeForPosition() {
		return graph.nodeExists(poseProvider.getPose().getX(), poseProvider.getPose().getY());
	}
	
	public float getPose() {
		return poseProvider.getPose().getHeading();
	}
	
	public boolean isGraphfinished() {
		return graph.isGraphfinished();
	}
	
	public Node getNodeToFinish() {
		return graph.getNodeToFinish();
	}
	
	public Graph getGraph() {
		return graph;
	}
	
	public Node getStartNode() {
		return graph.getStartNode();
	}
	
	public Node getFinishNode() {
		return graph.getFinishNode();
	}
	
	public Edge getCurrentEdge() {
		return graph.getCurrentEdge();
	}

	public Direction getDirectionToDrive(Node currentNode) {
		Node node = getNodeForPosition();
		Direction[] directions = node.getDirections();
		for(int i = 0; i < directions.length; i++) {
			Edge edge = directions[i].getEdge();
			if(edge != null && edge.getSource().getId().equals(currentNode.getId()) || edge.getDestination().getId().equals(currentNode.getId())) {
				return directions[i];
			}
		}
		return null;
	}
}
