package nxt.rbt.behavior;

import nxt.rbt.limit.ColorLimits;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class CrossingBe implements Behavior{

	LightSensor s1;
	LightSensor s2;
	LightSensor s3;
	
	DifferentialPilot pilot;
	
	public CrossingBe(DifferentialPilot pilot) {
		s1 = new LightSensor(SensorPort.S1);
		s2 = new LightSensor(SensorPort.S2);
		s3 = new LightSensor(SensorPort.S3);
		
		this.pilot = pilot;
	}
		
	@Override
	public boolean takeControl() {
		LCD.drawString("Crossing: \nSensor1: " + s1.readValue() + " Sensor2: " + s3.readValue(), 0, 0);
		if ((s1.readValue() > ColorLimits.YELLOW_LIMIT && s2.readValue() > ColorLimits.YELLOW_LIMIT) ||
				(s3.readValue() > ColorLimits.YELLOW_LIMIT && s2.readValue() > ColorLimits.YELLOW_LIMIT) || 
				(s3.readValue() > ColorLimits.YELLOW_LIMIT && s2.readValue() > ColorLimits.YELLOW_LIMIT && s1.readValue() > ColorLimits.YELLOW_LIMIT)) 
			return true;
		else
			return false;
	}

	@Override
	public void action() {	
		LCD.drawString("Kreuzung ", 0, 0);
		pilot.stop();
	}

	@Override
	public void suppress() {
		
		
	}

}


// 34 - 50