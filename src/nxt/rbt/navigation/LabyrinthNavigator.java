package nxt.rbt.navigation;

import java.util.List;

import lejos.nxt.LCD;
import lejos.robotics.localization.PoseProvider;
import nxt.rbt.graph.Graph;
import nxt.rbt.graph.Edge;
import nxt.rbt.graph.Vertex;


public class LabyrinthNavigator {
	
	Graph graph;
	Line line;
	List<Edge> edges;
	List<Vertex> vertexes;
	
	Edge previousEdge;
	Edge currentEdge;
	
	PoseProvider poseProvider;
	
	public LabyrinthNavigator(PoseProvider poseProvider) {
		//graph = new Graph(vertexes, edges)
		this.poseProvider = poseProvider;
		line = new Line();
		
		//previousEdge = new Edge(id, source, destination, weight)
	}
	
	public void addPoint() {
		line.addPoint(poseProvider.getPose().getX(), poseProvider.getPose().getY());
	}
	
	public void addNode() {
		
	}
	
	public void logPosition() {
		LCD.drawString("Pose - X: " + poseProvider.getPose().getX() + "\n Y: " + poseProvider.getPose().getY(), 0, 2);
	}	
}
