package nxt.rbt.behavior;

import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import nxt.rbt.limit.ColorLimits;
import nxt.rbt.limit.NavigationLimits;
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
		
	}
		
	@Override
	public boolean takeControl() {
		//LCD.drawString("Crossing: \nSensor1: " + s1.readValue() + " \nSensor2: " + s2.readValue() + " \nSensor3: " + s3.readValue(), 0, 0);
		if ((isInYellow(s2.readValue()) && (isInYellow(s1.readValue()) || isInYellow(s3.readValue()))) ||
				(isInYellow(s3.readValue()) && isInYellow(s1.readValue())) && 
				(isInYellow(s2.readValue()) || !isInYellow(s2.readValue()))) 
			return true;
		else
			return false;
	}

	@Override
	public void action() {
		if (s1.readValue() > ColorLimits.YELLOW_LIMIT && s2.readValue() > ColorLimits.YELLOW_LIMIT) {
			// right
			pilot.travel(2.0);
			do {
				pilot.rotate(-1 * NavigationLimits.CROSSING_TURN_RATE);
			} while (s2.readValue() < ColorLimits.YELLOW_LIMIT); 			
		} else if (s2.readValue() > ColorLimits.YELLOW_LIMIT && s3.readValue() > ColorLimits.YELLOW_LIMIT) {
			//left
			pilot.travel(2.0);
			do {
				pilot.rotate(NavigationLimits.CROSSING_TURN_RATE);
			} while (s2.readValue() < ColorLimits.YELLOW_LIMIT);
		}			
	}

	@Override
	public void suppress() {
		
		
	}
	
}