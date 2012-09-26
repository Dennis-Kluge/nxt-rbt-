package nxt.rbt.navigation;

import lejos.nxt.comm.RConsole;
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
		RConsole.println("Ausgabe: rotate: " + angle);
		pose += angle;
		super.rotate(angle*0.9);
	}


	@Override
	public void steer(double turnRate) {
		// TODO Auto-generated method stub
		super.steer(turnRate);
	}

	public double getPose() {
		double ret;
		int temp = (int)pose / 360;
		ret = pose - temp*360;
		if(ret < 0) 
			ret = 360 - ret;
		if(ret == 0)
			ret = 360;
		return ret;
	}
	
}
