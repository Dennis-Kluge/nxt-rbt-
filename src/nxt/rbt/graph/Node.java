package nxt.rbt.graph;

import lejos.nxt.comm.RConsole;


public class Node {
	final private String id;
	final private String name;
	
	private final float x;
	private final float y;
	
	Direction[] directions = new Direction[4];
	
	Node[] nodesForDirection = new Node[4];
	Directions currentDirection = Directions.FORWARD;
	private int POSE_TOLERANCY = 30;
	
	private boolean isEnding;
	private boolean isStartNode;
	private boolean isFinishNode;
	
	public Node(String id, String name, float x, float y) {
		this.id = id;
		this.name = name;		
		this.x = x;
		this.y = y;
	}
	
	public Node(String id, String name, Direction[] directions, float x, float y) {
		this.id = id;
		this.name = name;		
		this.directions = directions;
		this.x = x;
		this.y = y;
	}
	
//	public void turnDirections() {
//		DirectionStates tmp2 = null;
//		for(int i = 0; i < 4; i++) {
//			DirectionStates tmp;
//			if (i != 3) {
//				tmp = directions[i + 1];
//				if(i == 0)
//					directions[i+1] = directions[i];
//				else 
//					directions[i + 1] = tmp2;
//				tmp2 = tmp;
//			} else {
//				directions[0] = tmp2;
//			}			
//		}
//	}
	
//	public void turnNodes() {
//		for(int i = 0; i < 4; i++) {
//			Node tmp;
//			Node tmp2 = null;
//			if (i != 3) {
//				tmp = nodesForDirection[i + 1];
//				if(i == 0)
//					nodesForDirection[i+1] = nodesForDirection[i];
//				else 
//					nodesForDirection[i + 1] = tmp2;
//				tmp2 = tmp;
//			} else {
//				nodesForDirection[0] = tmp2;
//			}			
//		}
//	}
	
	public Direction[] getDirections() {
		return directions;
	}
	
	public void setLeftDirectionState(double pose, DirectionStates state) {
		for (int i = 0; i < directions.length; i++) {
			double left = pose + 90;
			if(left > directions[i].getPose() - POSE_TOLERANCY && left < directions[i].getPose() + POSE_TOLERANCY	) {
				directions[i].setDirectionState(state);
			}
		}
	}
	
	public Direction getLeftDirection(double pose) {
		for (int i = 0; i < directions.length; i++) {
			double left = pose + 90;
			if(directions[i] != null && left > directions[i].getPose() - POSE_TOLERANCY && left < directions[i].getPose() + POSE_TOLERANCY	) {
				return directions[i];
			}
		}
		return null;
	}
	
	public void setRightDirectionState(double pose, DirectionStates state) {
		for (int i = 0; i < directions.length; i++) {
			double right = pose - 90;
			if(right > directions[i].getPose() - POSE_TOLERANCY && right < directions[i].getPose() + POSE_TOLERANCY	) {
				directions[i].setDirectionState(state);
			}
		}
	}
	
	public Direction getRightDirection(double pose) {
		for (int i = 0; i < directions.length; i++) {
			double right = pose - 90;
			if(directions[i] != null && right > directions[i].getPose() - POSE_TOLERANCY && right < directions[i].getPose() + POSE_TOLERANCY	) {
				return directions[i];
			}
		}
		return null;
	}
	
	public void setForwardDirection(double pose, DirectionStates state) {
		for (int i = 0; i < directions.length; i++) {
			if(pose > directions[i].getPose() - POSE_TOLERANCY && pose < directions[i].getPose() + POSE_TOLERANCY	) {
				directions[i].setDirectionState(state);
			}
		}
	}
	
	public Direction getForwardDirection(double pose) {
		for (int i = 0; i < directions.length; i++) {
			if(directions[i] != null && pose > directions[i].getPose() - POSE_TOLERANCY && pose < directions[i].getPose() + POSE_TOLERANCY	) {
				return directions[i];
			}
		}
		return null;
	}
	
	public void setDirection(Direction[] direction) {
		directions = direction;
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	@Override
	public String toString() {
		return name;
	}

	public boolean isEnding() {
		return isEnding;
	}

	public void setEnding(boolean isEnding) {
		this.isEnding = isEnding;
	}

	public boolean isStartNode() {
		return isStartNode;
	}

	public void setStartNode(boolean isStartNode) {
		this.isStartNode = isStartNode;
	}

	public boolean isFinishNode() {
		return isFinishNode;
	}

	public void setFinishNode(boolean isFinishNode) {
		this.isFinishNode = isFinishNode;
	}		
	
//	public Node[] getNodesForDirection() {
//		return nodesForDirection;
//	}
	
//	public void setNodesForDirection(Node[] nodes) {
//		nodesForDirection = nodes;
//	}
	
//	public Directions getCurrentDirection() {
//		return currentDirection;
//	}
	
//	public void setCurrentDirection(Directions direction) {
//		currentDirection = direction;
//	}
}
