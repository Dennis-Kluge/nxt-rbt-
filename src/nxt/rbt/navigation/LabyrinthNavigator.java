package nxt.rbt.navigation;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

import lejos.nxt.LCD;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.localization.PoseProvider;
import nxt.rbt.graph.Graph;
import nxt.rbt.graph.Edge;
import nxt.rbt.graph.Vertex;

public class LabyrinthNavigator {

	Graph graph;
	Line line;
	List<Edge> edges;
	List<Vertex> vertexes;

	Vertex currentVertex;
	int vertexCounter;

	Edge previousEdge;
	Edge currentEdge;
	int edgeCounter;

	PoseProvider poseProvider;
	OutputStream outputStream;

	public LabyrinthNavigator(PoseProvider poseProvider,
			OutputStream outputStream) {
		// graph = new Graph(vertexes, edges)

		edges = new LinkedList<Edge>();
		vertexes = new LinkedList<Vertex>();

		this.poseProvider = poseProvider;
		this.outputStream = outputStream;

		line = new Line();
		vertexCounter = 0;
		currentVertex = new Vertex(Integer.toString(vertexCounter),
				Integer.toString(vertexCounter));
		vertexes.add(currentVertex);
		vertexCounter++;

		edgeCounter = 0;
	}

	public void addPoint() {
		line.addPoint(poseProvider.getPose().getX(), poseProvider.getPose().getY());
		LCD.drawString("Pose - " + poseProvider.getPose().getHeading(), 0, 2);
	}

	public void addNode() {
		float distance = line.getDistance();
		line.clearLine();
		Vertex vertex = new Vertex(Integer.toString(vertexCounter),
				Integer.toString(vertexCounter));
		Edge edge = new Edge(Integer.toString(edgeCounter), currentVertex,
				vertex, distance);
		edges.add(edge);
		String message = currentVertex.getId() + " " + vertex.getId() + " "
				+ distance + ",";
		try {
			outputStream.write(message.getBytes());
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		edgeCounter++;
		currentVertex = vertex;
	}

	public void logPosition() {
		// LCD.drawString("Pose - X: " + poseProvider.getPose().getX() +
		// "\n Y: " + poseProvider.getPose().getY(), 0, 2);
	}
}
