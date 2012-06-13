package nxt.rbt.behavior;

import nxt.rbt.limit.ColorLimits;
import nxt.rbt.navigation.Line;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;

public class ForwardBe extends AbstractBehavior {

	LightSensor s1;
	LightSensor s2;
	LightSensor s3;	
	Line line;
	
	public ForwardBe(Navigator navigator, DifferentialPilot pilot, OdometryPoseProvider poseProvider) {
		super(navigator, pilot, poseProvider);
		s1 = new LightSensor(SensorPort.S1);
		s2 = new LightSensor(SensorPort.S2);
		s3 = new LightSensor(SensorPort.S3);
		
		line = new Line();
	}
		
	@Override
	public boolean takeControl() {
		LCD.drawString("Forward: \nSensor1: " + s1.readValue() + " Sensor2: " + s2.readValue(), 0, 0);
		LCD.drawString("Forward", 0, 1);
		logPosition();
		if (s1.readValue() < ColorLimits.YELLOW_LIMIT && s3.readValue() < ColorLimits.YELLOW_LIMIT ) 
			return true;
		else
			return false;
	}

	@Override
	public void action() {
		line.addPoint(poseProvider.getPose().getX(), poseProvider.getPose().getY());
		pilot.forward();
	}

	@Override
	public void suppress() {
		
		
	}

}


