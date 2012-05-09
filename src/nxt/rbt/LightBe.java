package nxt.rbt;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.subsumption.Behavior;

public class LightBe implements Behavior{

	LightSensor light;
	
	public LightBe() {
		light = new LightSensor(SensorPort.S1);
	}
	
	
	@Override
	public boolean takeControl() {
		
		return true;
	}

	@Override
	public void action() {
		
		
	}

	@Override
	public void suppress() {
		
		
	}

}
