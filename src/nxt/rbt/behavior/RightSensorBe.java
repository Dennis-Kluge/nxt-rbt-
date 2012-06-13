package nxt.rbt.behavior;

import nxt.rbt.limit.ColorLimits;
import nxt.rbt.limit.NavigationLimits;
import nxt.rbt.navigation.LabyrinthNavigator;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;

public class RightSensorBe extends AbstractBehavior {
	
	private LightSensor s1;	
	private LightSensor s3;
	
	public RightSensorBe(LabyrinthNavigator navigator, DifferentialPilot pilot) {
		super(navigator, pilot);
		s1 = new LightSensor(SensorPort.S1);		
		s3 = new LightSensor(SensorPort.S3);			
	}
	
	@Override
	public boolean takeControl() {
		LCD.drawString("RightSensor: \nSensor1: " + s1.readValue() + " Sensor2: " + s3.readValue(), 0, 0);
		LCD.drawString("Right", 0, 200);
		if (s1.readValue() > ColorLimits.YELLOW_LIMIT && s3.readValue() < ColorLimits.YELLOW_LIMIT) {
			return true;
		} else
			return false;
	}

	@Override
	public void action() {
		pilot.steer(-1 * NavigationLimits.TURN_RATE);
	}

	@Override
	public void suppress() {
		
		
	}
	
}
