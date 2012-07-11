package nxt.rbt.graph;

public class Vertex {
	final private String id;
	final private String name;
	
	DirectionStates[] directions = {DirectionStates.NOT_POSSIBLE, DirectionStates.NOT_POSSIBLE, DirectionStates.NOT_POSSIBLE, DirectionStates.NOT_POSSIBLE};
	
	boolean isEnding;
	
	public Vertex(String id, String name) {
		this.id = id;
		this.name = name;		
	}
	
	public Vertex(String id, String name, DirectionStates[] directions) {
		this.id = id;
		this.name = name;		
		this.directions = directions;
	}
	
	public void turnDirections() {
		for(int i = 0; i < 4; i++) {
			if (i != 3) {
				directions[i+1] = directions[i];
			} else {
				directions[0] = directions[i];
			}			
		}
	}
	
	public DirectionStates[] getDirections() {
		return directions;
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
		Vertex other = (Vertex) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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
	
}
