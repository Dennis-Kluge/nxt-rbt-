package nxt.rbt;

import lejos.nxt.LCD;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lejos.robotics.subsumption.Behavior;

public abstract class AbstractBehavior implements Behavior {
	
	protected Navigator navigator;
	protected DifferentialPilot pilot;
	protected Pose pose;
	
	public AbstractBehavior(Navigator navigator, DifferentialPilot pilot, Pose pose) {
		this.navigator = navigator;
		this.pilot = pilot;
		this.pose = pose;
	}
	
	protected void logPosition() {
		LCD.drawString("Pose - X: " + pose.getX() + " Y: " + pose.getY(), 0, 400);
	}
	
}
