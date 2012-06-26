package nxt.rbt.navigation;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import lejos.geom.Point;
import lejos.nxt.LCD;

public class Line {
	
	private List<Point> points;	
	private int counter;
	
	public Line() {
		points = new LinkedList<Point>();
		counter = 0;
	}
	
	public void addPoint(float x, float y) {
		points.add(new Point(x, y));
		counter++;
		if (counter == 30) {			
			counter = 0;
		}
	}
	
	public float getDistance() {
		float distance = 0;			
		if (points.size() > 2) {
			ListIterator<Point> iterator = (ListIterator<Point>) points.iterator();
			Point previousPoint = iterator.next();
			while (iterator.hasNext()) {
				Point point = iterator.next();				
				Point tempPoint = point.subtractWith(previousPoint);				
				distance += Math.sqrt(Math.pow(tempPoint.x, 2) + Math.pow(tempPoint.y, 2));				
				tempPoint = point;
				if (iterator.hasNext()) 
					point = iterator.next();				
			}
			LCD.drawString("Distance: " + distance, 0, 6);
			return distance;
		}				
		return 0;		
	}
	
	public void clearLine() {
		points.clear();
	}
}
