package nxt.rbt.behavior;

import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import nxt.rbt.limit.ColorLimits;
import nxt.rbt.navigation.LabyrinthNavigator;

public class CrossingBe extends AbstractBehavior{

	LightSensor s1;
	LightSensor s2;
	LightSensor s3;
	
	public CrossingBe(LabyrinthNavigator navigator, DifferentialPilot pilot) {
		super(navigator, pilot);
		s1 = new LightSensor(SensorPort.S1);
		s2 = new LightSensor(SensorPort.S2);
		s3 = new LightSensor(SensorPort.S3);
		
		this.pilot = pilot;
	}
		
	@Override
	public boolean takeControl() {
		LCD.drawString("Crossing: \nSensor1: " + s1.readValue() + " \nSensor2: " + s2.readValue() + " \nSensor3: " + s3.readValue(), 0, 0);
		if ((s1.readValue() > ColorLimits.YELLOW_LIMIT && s2.readValue() > ColorLimits.YELLOW_LIMIT) ||
				(s3.readValue() > ColorLimits.YELLOW_LIMIT && s2.readValue() > ColorLimits.YELLOW_LIMIT) || 
				(s3.readValue() > ColorLimits.YELLOW_LIMIT && s2.readValue() > ColorLimits.YELLOW_LIMIT && s1.readValue() > ColorLimits.YELLOW_LIMIT) ||
				(s3.readValue() > ColorLimits.YELLOW_LIMIT && s2.readValue() < ColorLimits.YELLOW_LIMIT && s1.readValue() > ColorLimits.YELLOW_LIMIT)) 
			return true;
		else
			return false;
	}

	@Override
	public void action() {	
		//LCD.drawString("Kreuzung ", 0, 800);
		pilot.stop();
	}

	@Override
	public void suppress() {
		
		
	}

}