package nxt.rbt.behavior;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import nxt.rbt.limit.ColorLimits;
import nxt.rbt.limit.NavigationLimits;
import nxt.rbt.navigation.LabyrinthNavigator;
import nxt.rbt.navigation.NavigationPilotPose;

public class RightSensorBe extends AbstractBehavior {
	
	private LightSensor s1;	
	private LightSensor s3;
	private LightSensor s2;
	
	public RightSensorBe(LabyrinthNavigator navigator, NavigationPilotPose pilot) {
		super(navigator, pilot);
		s1 = new LightSensor(SensorPort.S1);		
		s3 = new LightSensor(SensorPort.S3);
		s2 = new LightSensor(SensorPort.S2);
	}
	
	@Override
	public boolean takeControl() {
		//LCD.drawString("RightSensor: \nSensor1: " + s1.readValue() + " \nSensor2: " + s2.readValue() + " \nSensor3: " + s3.readValue(), 0, 0);
		
		if (isInYellow(s1.readValue()) && s3.readValue() < ColorLimits.YELLOW_LIMIT && s2.readValue() < ColorLimits.YELLOW_LIMIT) {
			return true;
		} else
			return false;
	}

	@Override
	public void action() {
//		LCD.drawString("Right", 0, 0);
//		pilot.steer(-1 * NavigationLimits.TURN_RATE);
		pilot.rotate(-1*NavigationLimits.CROSSING_TURN_RATE);
	}

	@Override
	public void suppress() {
		
		
	}

}
