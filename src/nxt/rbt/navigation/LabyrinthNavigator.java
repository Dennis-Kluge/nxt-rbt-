package nxt.rbt.navigation;

import java.io.OutputStream;
import java.util.List;

import lejos.robotics.localization.PoseProvider;
import nxt.rbt.graph.DirectionStates;
import nxt.rbt.graph.Graph;
import nxt.rbt.graph.Node;

public class LabyrinthNavigator {

	private Graph graph;	
	private List<Node> vertexes;

	private Node currentVertex;
	private int vertexCounter;

	private PoseProvider poseProvider;
	private OutputStream outputStream;
	
	boolean turned;

	public LabyrinthNavigator(PoseProvider poseProvider, OutputStream outputStream) {			

		this.poseProvider = poseProvider;
		this.outputStream = outputStream;
		
		graph = new Graph(outputStream);
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
		for (Node vertex : vertexes) {
			vertex.turnDirections();
		}
	}
}
