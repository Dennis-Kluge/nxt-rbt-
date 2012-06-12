package nxt.rbt;

import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class RightSensorBe implements Behavior{
	
	private LightSensor s1;	
	private LightSensor s3;
	
	DifferentialPilot pilot; 
	
	public RightSensorBe(DifferentialPilot pilot) {
		s1 = new LightSensor(SensorPort.S1);		
		s3 = new LightSensor(SensorPort.S3);
		
		this.pilot = pilot;
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
		pilot.rotate(-1 * NavigationLimits.ANGLE);
	}

	@Override
	public void suppress() {
		
		
	}
	
}
