package nxt.rbt;

import lejos.nxt.LCD;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Main {

	public static void main(String[] args) {		
		Behavior forwardBe = new ForwardBe();
		Behavior rightBe = new RightSensorBe();
		Behavior leftBe = new LeftSensorBe();		
		Behavior[] bArray = {forwardBe, rightBe,leftBe};
		Arbitrator arby = new Arbitrator(bArray);
		LCD.drawString("Started", 0, 0);
		arby.start();		
	}
}
