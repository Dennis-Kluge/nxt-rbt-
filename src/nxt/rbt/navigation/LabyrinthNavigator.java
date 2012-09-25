package nxt.rbt.navigation;

import lejos.robotics.localization.PoseProvider;
import nxt.rbt.graph.Direction;
import nxt.rbt.graph.Graph;
import nxt.rbt.graph.Node;

public class LabyrinthNavigator {

	private Graph graph;	

	private PoseProvider poseProvider;	
	
	boolean turned;

	public LabyrinthNavigator(PoseProvider poseProvider) {			
		this.poseProvider = poseProvider;		
		graph = new Graph();
		graph.addEndNode(poseProvider.getPose().getX(), poseProvider.getPose().getY(), true, false);

	}

	public void addPoint() {		
		graph.addPoint(poseProvider.getPose().getX(), poseProvider.getPose().getY());		
	}		
	
	public void addNode(Direction[] possibleDirections) {
//		LCD.drawString("addNode: 1 \n" + poseProvider.getPose().getX() +"\n"+ poseProvider.getPose().getY()+"\n",0, 0);
		graph.addNode(possibleDirections, poseProvider.getPose().getX(), poseProvider.getPose().getY());
	}
	
	public void addEndNode(boolean isFinishNode) {
		graph.addEndNode(poseProvider.getPose().getX(), poseProvider.getPose().getY(), false, isFinishNode);	
	}
	
//	public void turnDirection() {
//		for (Node vertex : graph.getVertexes()) {
//			vertex.turnDirections();
//		}
//	}
	
//	public void turnNodesForDirection() {
//		for (Node vertex : graph.getVertexes()) {
//			vertex.turnNodes();
//		}
//	}
	
	public boolean hasNode() {
		if(graph.nodeExists(poseProvider.getPose().getX(), poseProvider.getPose().getY()) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public Direction[] getDirections() {
//		LCD.drawString("\n\n\n\n\ngetDir: 1 \n" + poseProvider.getPose().getX() +"\n"+ poseProvider.getPose().getY(),0, 0);
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
}
