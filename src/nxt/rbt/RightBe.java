package nxt.rbt;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.subsumption.Behavior;

public class RightBe implements Behavior{
	
	
	private LightSensor light;
	
	public RightBe() {
		light = new LightSensor(SensorPort.S1);
	}
	
	@Override
	public boolean takeControl() {
		if (light.readValue() > 40 ) 
			return true;
		else
			return false;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}
	

}
