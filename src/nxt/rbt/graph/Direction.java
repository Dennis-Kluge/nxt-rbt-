package nxt.rbt.graph;

public class Direction {

	DirectionStates directionStates;
	double pose;
	Edge edge;
	
	public Edge getEdge() {
		return edge;
	}

	public void setEdge(Edge edge) {
		this.edge = edge;
	}

	public Direction (double pose, DirectionStates directionState){
		this.pose = pose;
		this.directionStates = directionState;
	}

	public DirectionStates getDirectionState() {
		return directionStates;
	}

	public void setDirectionState(DirectionStates state) {
		directionStates = state;
	}
	
	public double getPose() {
		return pose;
	}
	
	
	
}
