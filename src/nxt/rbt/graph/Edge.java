package nxt.rbt.graph;

public class Edge  {
	
	private final String id; 
	private final Vertex source;
	private final Vertex destination;
	private final float weight; 
	
	public Edge(String id, Vertex source, Vertex destination, float weight) {
		this.id = id;
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}
	
	public String getId() {
		return id;
	}
	public Vertex getDestination() {
		return destination;
	}

	public Vertex getSource() {
		return source;
	}
	public float getWeight() {
		return weight;
	}
	
	@Override
	public String toString() {
		return source + " " + destination;
	}
	
	
}