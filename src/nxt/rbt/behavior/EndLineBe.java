package nxt.rbt.behavior;

import nxt.rbt.limit.ColorLimits;
import nxt.rbt.limit.NavigationLimits;
import nxt.rbt.navigation.LabyrinthNavigator;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class EndLineBe extends AbstractBehavior{

	LightSensor s1;
	LightSensor s2;
	LightSensor s3;
	
	public EndLineBe(LabyrinthNavigator navigator, DifferentialPilot pilot) {
		super(navigator, pilot);
		s1 = new LightSensor(SensorPort.S1);
		s2 = new LightSensor(SensorPort.S2);
		s3 = new LightSensor(SensorPort.S3);
		
	}
		
	@Override
	public boolean takeControl() {
//		LCD.drawString("Endline: Sensor1: " + s1.readValue() + " \nSensor2: " + s2.readValue() + " \nSensor3: " + s3.readValue(), 0, 0);
		if ((s1.readValue() > ColorLimits.WHITE_LIMIT || s2.readValue() > ColorLimits.WHITE_LIMIT || s3.readValue() > ColorLimits.WHITE_LIMIT)) 
			return true;
		else
			return false;
	}

	@Override
	public void action() {	
//		LCD.drawString("Ende ", 0, 0);
		do {
			pilot.rotate(NavigationLimits.CROSSING_TURN_RATE_ENDLINE);
		} while (!isInYellow(s2.readValue()));
			
	}

	@Override
	public void suppress() {
		
		
	}

	
	
	

}



	

