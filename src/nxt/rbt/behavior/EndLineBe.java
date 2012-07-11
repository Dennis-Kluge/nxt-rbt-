package nxt.rbt.behavior;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import nxt.rbt.limit.ColorLimits;
import nxt.rbt.limit.NavigationLimits;
import nxt.rbt.navigation.LabyrinthNavigator;

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
		if ((s1.readValue() > ColorLimits.WHITE_LIMIT || s2.readValue() > ColorLimits.WHITE_LIMIT || s3.readValue() > ColorLimits.WHITE_LIMIT)) 
			return true;
		else
			return false;
	}

	@Override
	public void action() {	
		do {
			pilot.rotate(NavigationLimits.CROSSING_TURN_RATE_ENDLINE);
		} while (!isInYellow(s2.readValue()));
		navigator.turnDirection();
			
	}

	@Override
	public void suppress() {
		
		
	}

}



	

