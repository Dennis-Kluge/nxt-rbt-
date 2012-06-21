package nxt.rbt.behavior;

import nxt.rbt.limit.ColorLimits;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class EndLineBe implements Behavior{

	LightSensor s1;
	LightSensor s2;
	LightSensor s3;
	
	DifferentialPilot pilot;
	
	public EndLineBe(DifferentialPilot pilot) {
		s1 = new LightSensor(SensorPort.S1);
		s2 = new LightSensor(SensorPort.S2);
		s3 = new LightSensor(SensorPort.S3);
		
		this.pilot = pilot;
	}
		
	@Override
	public boolean takeControl() {
		LCD.drawString("Endline: Sensor1: " + s1.readValue() + " \nSensor2: " + s2.readValue() + " \nSensor3: " + s3.readValue(), 0, 0);
		if ((s1.readValue() < ColorLimits.WHITE_LIMIT || s2.readValue() < ColorLimits.WHITE_LIMIT || s3.readValue() < ColorLimits.WHITE_LIMIT)) 
			return true;
		else
			return false;
	}

	@Override
	public void action() {	
		LCD.drawString("Ende ", 0, 0);
		Motor.A.rotate(720);
		Motor.C.stop();		
	}

	@Override
	public void suppress() {
		
		
	}

}



	

