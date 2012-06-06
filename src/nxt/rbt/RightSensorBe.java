package nxt.rbt;

import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.subsumption.Behavior;

public class RightSensorBe implements Behavior{
	
	private LightSensor s1;
	private LightSensor s2;
	private LightSensor s3;
	
	public RightSensorBe() {
		s1 = new LightSensor(SensorPort.S1);
		s2 = new LightSensor(SensorPort.S2);
		s3 = new LightSensor(SensorPort.S3);
	}
	
	@Override
	public boolean takeControl() {
		LCD.drawString("RightSensor: \nSensor1: " + s1.readValue() + " Sensor2: " + s3.readValue(), 0, 0);
		if (s1.readValue() > ColorLimits.YELLOW_LIMIT && s3.readValue() < ColorLimits.YELLOW_LIMIT) {
			return true;
		} else
			return false;
	}

	@Override
	public void action() {
		Motor.A.stop();
		Motor.C.forward();
	}

	@Override
	public void suppress() {
		
		
	}
	
}
