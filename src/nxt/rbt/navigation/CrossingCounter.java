package nxt.rbt.navigation;

import nxt.rbt.graph.DirectionStates;

public class CrossingCounter {

	int count;
	int currentAngle;
	int angleLastLine;
	DirectionStates left;
	DirectionStates right;
	DirectionStates forward;
	
	public CrossingCounter() {
		count =  0;
		currentAngle = 0;
		angleLastLine = 0;
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
	
	public DirectionStates[] getDirections() {
		// rechts, hinten, links, geradeaus
		DirectionStates[] directions = {right,DirectionStates.TAKEN,left,forward};
		return directions;
	}

	public DirectionStates getLeft() {
		return left;
	}

	public void setLeft(DirectionStates left) {
		this.left = left;
	}

	public DirectionStates getRight() {
		return right;
	}

	public void setRight(DirectionStates right) {
		this.right = right;
	}

	public DirectionStates getForward() {
		return forward;
	}

	public void setForward(DirectionStates forward) {
		this.forward = forward;
	}
}
