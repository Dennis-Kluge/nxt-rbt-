package nxt.rbt;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Main {

	public static void main(String[] args) {
		Button.ENTER.waitForPressAndRelease();
		Behavior forwardBe = new ForwardBe();
		Behavior rightBe = new RightSensorBe();
		Behavior leftBe = new LeftSensorBe();
		Behavior crossingBe = new CrossingBe();
		Behavior endBe = new EndLineBe();
		Behavior[] bArray = {endBe,forwardBe, rightBe,leftBe, crossingBe};
		Arbitrator arby = new Arbitrator(bArray);
		LCD.drawString("Started", 0, 0);
		arby.start();		
	}
}
