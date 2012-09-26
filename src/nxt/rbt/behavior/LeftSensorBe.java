package nxt.rbt.behavior;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import nxt.rbt.limit.ColorLimits;
import nxt.rbt.limit.NavigationLimits;
import nxt.rbt.navigation.LabyrinthNavigator;
import nxt.rbt.navigation.NavigationPilotPose;

public class LeftSensorBe extends AbstractBehavior{
	
	private LightSensor s1;	
	private LightSensor s3;
	private LightSensor s2;
	
	public LeftSensorBe(LabyrinthNavigator navigator, NavigationPilotPose pilot) {
		super(navigator, pilot);
		s1 = new LightSensor(SensorPort.S1);		
		s3 = new LightSensor(SensorPort.S3);
		s2 = new LightSensor(SensorPort.S2);
	}
	
	@Override
	public boolean takeControl() {
		//LCD.drawString("LeftSensor: \nSensor1: " + s1.readValue() + " \nSensor2: " + s2.readValue() + " \nSensor3: " + s3.readValue(), 0, 0);
			
		if (isInYellow(s3.readValue()) && s1.readValue() < ColorLimits.YELLOW_LIMIT && s2.readValue() < ColorLimits.YELLOW_LIMIT) {
			return true;
		} else 
			return false;
	}

	@Override
	public void action() {
//		LCD.drawString("Left", 0, 0);	
//		pilot.steer(NavigationLimits.TURN_RATE);
		pilot.rotate(NavigationLimits.CROSSING_TURN_RATE);
	}

	@Override
	public void suppress() {
		
	}
	

}
