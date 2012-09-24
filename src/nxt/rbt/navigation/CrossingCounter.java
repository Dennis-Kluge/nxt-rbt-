package nxt.rbt.navigation;

import nxt.rbt.graph.Direction;
import nxt.rbt.graph.DirectionStates;

public class CrossingCounter {

	int count;
	int currentAngle;
	int angleLastLine;
	Direction left;
	Direction right;
	Direction forward;
	Direction backward;
	
	public CrossingCounter() {
		count =  0;
		currentAngle = 0;
		angleLastLine = 0;
	}
	
	public void resetCurrentAngle() {
		currentAngle = 0;
	}
	
	public void addCount() {
		count +=1;
		angleLastLine = currentAngle;
	}
	
	public int getCount() {
		return count;
	}
	
	public int getCurrentAngle() {
		return currentAngle;
	}
	
	public void addCurrentAngle(int rotate) {
		currentAngle += rotate; 
	}
	
	public int getAngleLastLine() {
		return angleLastLine;
	}
	
	public Direction[] getDirections() {
		// rechts, hinten, links, geradeaus
		Direction[] directions = {right,backward,left,forward};
		return directions;
	}

	public void setLeft(double pose, DirectionStates left) {
		this.left = new Direction(pose, left);
	}

	public void setRight(double pose, DirectionStates right) {
		this.right = new Direction(pose, right);
	}

	public void setForward(double pose, DirectionStates forward) {
		this.forward = new Direction(pose, forward);
	}
	
	public void setBackward(double pose, DirectionStates backward) {
		this.backward = new Direction(pose, backward);
	}
}
