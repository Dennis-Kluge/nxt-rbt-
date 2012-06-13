package nxt.rbt.behavior;

import nxt.rbt.limit.ColorLimits;
import nxt.rbt.limit.NavigationLimits;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;

public class LeftSensorBe extends AbstractBehavior{
	
	private LightSensor s1;	
	private LightSensor s3;
	
	public LeftSensorBe(Navigator navigator, DifferentialPilot pilot, OdometryPoseProvider poseProvider) {
		super(navigator, pilot, poseProvider);
		s1 = new LightSensor(SensorPort.S1);		
		s3 = new LightSensor(SensorPort.S3);			
	}
	
	@Override
	public boolean takeControl() {
		LCD.drawString("LeftSensor: \nSensor1: " + s1.readValue() + " Sensor2: " + s3.readValue(), 0, 0);
		LCD.drawString("Left", 0, 200);		
		if (s3.readValue() > ColorLimits.YELLOW_LIMIT && s1.readValue() < ColorLimits.YELLOW_LIMIT) {
			return true;
		} else 
			return false;
	}

	@Override
	public void action() {
		pilot.rotate(NavigationLimits.ANGLE);
		//pilot.steer(turnRate)
	}

	@Override
	public void suppress() {
		
	}

}
