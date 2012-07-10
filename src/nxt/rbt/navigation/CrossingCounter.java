package nxt.rbt.navigation;

public class CrossingCounter {

	int count;
	int currentAngle;
	int angleLastLine;
	
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
}
