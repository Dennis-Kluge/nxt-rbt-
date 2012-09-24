package nxt.rbt.behavior;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import nxt.rbt.limit.ColorLimits;
import nxt.rbt.navigation.LabyrinthNavigator;
import nxt.rbt.navigation.Line;
import nxt.rbt.navigation.NavigationPilotPose;

public class ForwardBe extends AbstractBehavior {

	LightSensor s1;
	LightSensor s2;
	LightSensor s3;	
	Line line;
	
	public ForwardBe(LabyrinthNavigator navigator, NavigationPilotPose pilot) {
		super(navigator, pilot);
		s1 = new LightSensor(SensorPort.S1);
		s2 = new LightSensor(SensorPort.S2);
		s3 = new LightSensor(SensorPort.S3);
		
		line = new Line();
	}
		
	@Override
	public boolean takeControl() {
//		RConsole.println("Ausgabe: Forward: s2: " + s2.readValue() +" , s1: " + s1.readValue() + " , s3: " + s3.readValue());
		//LCD.drawString("Forward: \nSensor1: " + s1.readValue() + " \nSensor3: " + s3.readValue(), 0, 0);
		
		//navigator.logPosition();
		if (s1.readValue() < ColorLimits.YELLOW_LIMIT && s3.readValue() < ColorLimits.YELLOW_LIMIT) 
			return true;
		else
			return false;
	}

	@Override
	public void action() {
//		RConsole.println("Ausgabe: Forward: s2: " + s2.readValue() +" , s1: " + s1.readValue() + " , s3: " + s3.readValue());
//		LCD.drawString("Forward: " + s2.readValue(), 0, 0);
		navigator.addPoint();
		pilot.forward();
	}

	@Override
	public void suppress() {
		
		
	}

}


