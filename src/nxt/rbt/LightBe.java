package nxt.rbt;

import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.subsumption.Behavior;

public class LightBe implements Behavior{

	LightSensor light;
	
	public LightBe() {
		light = new LightSensor(SensorPort.S1);
	}
		
	@Override
	public boolean takeControl() {
		LCD.drawString("Sensor Value:" + light.readValue(), 0, 0);
		if (light.readValue() < 50 ) 
			return true;
		else
			return false;
	}

	@Override
	public void action() {				
		Motor.A.backward();
		Motor.C.backward();		
	}

	@Override
	public void suppress() {
		
		
	}

}
