package nxt.rbt;

import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class ForwardBe implements Behavior{

	LightSensor s1;
	LightSensor s2;
	LightSensor s3;
	
	DifferentialPilot pilot;
	
	public ForwardBe(DifferentialPilot pilot) {
		s1 = new LightSensor(SensorPort.S1);
		s2 = new LightSensor(SensorPort.S2);
		s3 = new LightSensor(SensorPort.S3);
		
		this.pilot = pilot;
	}
		
	@Override
	public boolean takeControl() {
		LCD.drawString("Forward: \nSensor1: " + s1.readValue() + " Sensor2: " + s2.readValue(), 0, 0);
		LCD.drawString("Forward", 0, 200);
		if (s1.readValue() < ColorLimits.YELLOW_LIMIT && s3.readValue() < ColorLimits.YELLOW_LIMIT ) 
			return true;
		else
			return false;
	}

	@Override
	public void action() {
		pilot.forward();
	}

	@Override
	public void suppress() {
		
		
	}

}


