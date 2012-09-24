package nxt.rbt.graph;

public class Direction {

	DirectionStates directionStates = DirectionStates.UNKNOWN;
	double pose;
	
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
