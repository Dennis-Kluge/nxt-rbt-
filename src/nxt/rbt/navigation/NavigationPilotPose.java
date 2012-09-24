package nxt.rbt.navigation;

import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;



public class NavigationPilotPose extends DifferentialPilot{

	DifferentialPilot pilot;
	double pose;
	
	public NavigationPilotPose (final double wheelDiameter, final double trackWidth,
	          final RegulatedMotor leftMotor, final RegulatedMotor rightMotor) {
		super(wheelDiameter, trackWidth, leftMotor, rightMotor);
		pose = 0;
	}

	@Override
	public void rotate(double angle) {
		pose = angle * 0.9;
		super.rotate(angle*0.9);
	}


	@Override
	public void steer(double turnRate) {
		// TODO Auto-generated method stub
		super.steer(turnRate);
	}

	public double getPose() {
		return pose;
	}
	
}
