package nxt.rbt.navigation;

import java.util.LinkedList;
import java.util.List;

import lejos.geom.Point;

public class Line {
	
	private List<Point> points;
	
	public Line() {
		points = new LinkedList<Point>();
	}
	
	public void addPoint(float x, float y) {
		points.add(new Point(x, y));
	}
	
	public float getDistance() {
		float distance = 0;
		//TODO calculate distance 
		for (Point point : points) {
			
		}
		
		return distance;
	}
}
