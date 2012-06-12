package nxt.rbt;

import lejos.nxt.LCD;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lejos.robotics.subsumption.Behavior;

public abstract class AbstractBehavior implements Behavior {
	
	protected Navigator navigator;
	protected DifferentialPilot pilot;
	protected OdometryPoseProvider poseProvider;
	
	public AbstractBehavior(Navigator navigator, DifferentialPilot pilot, OdometryPoseProvider poseProvider) {
		this.navigator = navigator;
		this.pilot = pilot;
		this.poseProvider = poseProvider;
	}
	
	protected void logPosition() {
		LCD.drawString("Pose - X: " + poseProvider.getPose().getX() + " Y: " + poseProvider.getPose().getY(), 0, 2);
	}
	
}
