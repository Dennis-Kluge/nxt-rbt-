package nxt.rbt;

import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.subsumption.Behavior;

public class LeftSensorBe implements Behavior {
	
	private LightSensor s1;
	private LightSensor s2;
	
	public LeftSensorBe() {
		s1 = new LightSensor(SensorPort.S1);
		s2 = new LightSensor(SensorPort.S2);
	}
	
	@Override
	public boolean takeControl() {
		LCD.drawString("Sensor1: " + s1.readValue() + " Sensor2: " + s2.readValue(), 0, 0);
		if (s2.readValue() > 45 && s1.readValue() < 45) {
			return true;
		} else 
			return false;
	}

	@Override
	public void action() {
		Motor.C.stop();
		Motor.A.forward();
	}

	@Override
	public void suppress() {
		
	}

}
