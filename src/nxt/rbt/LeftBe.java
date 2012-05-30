package nxt.rbt;

import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.subsumption.Behavior;

public class LeftBe implements Behavior {
	
	private LightSensor s1;
	private LightSensor s2;
	
	public LeftBe() {
		s1 = new LightSensor(SensorPort.S1);
		s2 = new LightSensor(SensorPort.S2);
	}
	
	@Override
	public boolean takeControl() {
		if (s2.readValue() > 40 && s1.readValue() < 40) {
			return true;
		} else 
			return false;
	}

	@Override
	public void action() {
		Motor.C.forward();
		Motor.A.stop();
	}

	@Override
	public void suppress() {
		
	}

}
