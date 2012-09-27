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
		if (isInYellow(s3.readValue()) && s1.readValue() <= ColorLimits.YELLOW_LIMIT && s2.readValue() <= ColorLimits.YELLOW_LIMIT) {
			return true;
		} else 
			return false;
	}

	@Override
	public void action() {
		pilot.rotate(NavigationLimits.CROSSING_TURN_RATE_SEARCH);
	}

	@Override
	public void suppress() {
		
	}
	

}
