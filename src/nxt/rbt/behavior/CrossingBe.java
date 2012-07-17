package nxt.rbt.behavior;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import nxt.rbt.graph.DirectionStates;
import nxt.rbt.limit.ColorLimits;
import nxt.rbt.limit.NavigationLimits;
import nxt.rbt.navigation.CrossingCounter;
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
		
		// zum scannen der kreuzung - wie viele abzweigungen vorhanden sind 
		CrossingCounter sc =  new CrossingCounter();
		pilot.travel(2.0);
		do {
			pilot.rotate(-1 * NavigationLimits.CROSSING_TURN_RATE_SEARCH);
			sc.addCurrentAngle(NavigationLimits.CROSSING_TURN_RATE_SEARCH);
			if(isInYellow(s2.readValue()) && sc.getCurrentAngle() > (sc.getAngleLastLine() + 20)) {
				int currentAngle = sc.getCurrentAngle();
				if(currentAngle >= 0 && currentAngle <= 20 || currentAngle > 300) {
					sc.setForward(DirectionStates.POSSIBLE);
				} else if(currentAngle > 20 && currentAngle <= 100) {
					sc.setRight(DirectionStates.POSSIBLE);
				} else if(currentAngle> 190 && currentAngle <= 300) {
					sc.setLeft(DirectionStates.POSSIBLE);
				}
				sc.addCount();
			}
		} while (sc.getCurrentAngle() < NavigationLimits.COMPLETE_ROTATION);
		
		
		
		// zum abfahren der kreuzung nach rechts und links 
		if (s1.readValue() > ColorLimits.YELLOW_LIMIT && s2.readValue() > ColorLimits.YELLOW_LIMIT) {
			// right
			pilot.travel(2.0);
			sc.resetCurrentAngle();
			do {
				pilot.rotate(-1 * NavigationLimits.CROSSING_TURN_RATE);
			} while (!isInYellow(s2.readValue()) && sc.getCurrentAngle() < 20);
			sc.setRight(DirectionStates.TAKEN);
		} else if (s2.readValue() > ColorLimits.YELLOW_LIMIT && s3.readValue() > ColorLimits.YELLOW_LIMIT) {
			//left
			pilot.travel(2.0);
			sc.resetCurrentAngle();
			do {
				pilot.rotate(NavigationLimits.CROSSING_TURN_RATE);
			} while (!isInYellow(s2.readValue()) && sc.getCurrentAngle() < 20);
			sc.setLeft(DirectionStates.TAKEN);
		}
		
		navigator.addNode(sc.getDirections());
	}

	@Override
	public void suppress() {
		
		
	}
	
}