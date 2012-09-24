package nxt.rbt.behavior;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.comm.RConsole;
import nxt.rbt.limit.ColorLimits;
import nxt.rbt.limit.NavigationLimits;
import nxt.rbt.navigation.CrossingCounter;
import nxt.rbt.navigation.LabyrinthNavigator;
import nxt.rbt.navigation.NavigationPilotPose;

public class EndLineBe extends AbstractBehavior{

	LightSensor s1;
	LightSensor s2;
	LightSensor s3;
	
	public EndLineBe(LabyrinthNavigator navigator, NavigationPilotPose pilot) {
		super(navigator, pilot);
		s1 = new LightSensor(SensorPort.S1);
		s2 = new LightSensor(SensorPort.S2);
		s3 = new LightSensor(SensorPort.S3);
		
	}
		
	@Override
	public boolean takeControl() {	
		
		if ((s1.readValue() >= ColorLimits.WHITE_LIMIT || s2.readValue() >= ColorLimits.WHITE_LIMIT || s3.readValue() >= ColorLimits.WHITE_LIMIT)) 
			return true;
		else
			return false;
	}

	@Override
	public void action() {
		CrossingCounter sc = new CrossingCounter();
//		RConsole.println("Ausgabe: Endline: s2: " + s2.readValue() +" , s1: " + s1.readValue() + " , s3: " + s3.readValue());
//		LCD.drawString("End: " +s2.readValue() ,0, 0);
		RConsole.println("Endline");
		navigator.addEndNode(false);
		do {
			sc.addCurrentAngle(NavigationLimits.CROSSING_TURN_RATE_ENDLINE);
			pilot.rotate(NavigationLimits.CROSSING_TURN_RATE_ENDLINE);
//			RConsole.println("Ausgabe: Endline drehen: s2: " + s2.readValue() +" , wnkel: " + sc.getCurrentAngle()) ;
		} while (sc.getCurrentAngle() < 100 || (!isInYellow(s2.readValue()) && sc.getCurrentAngle() >= 100));
//		navigator.turnDirection();
//		navigator.turnNodesForDirection();
			
	}

	@Override
	public void suppress() {
		
		
	}

}



	

