package nxt.rbt;

import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.subsumption.Behavior;

public class RightBe implements Behavior{
	
	private LightSensor s1;
	private LightSensor s2;
	
	public RightBe() {
		s1 = new LightSensor(SensorPort.S1);
		s1 = new LightSensor(SensorPort.S2);
	}
	
	@Override
	public boolean takeControl() {
		if (s1.readValue() > 40 && s2.readValue() < 40) {
			return true;
		} else
			return false;
	}

	@Override
	public void action() {
		Motor.A.forward();
		Motor.C.stop();
	}

	@Override
	public void suppress() {
		
		
	}
	
}
