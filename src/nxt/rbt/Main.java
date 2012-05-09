package nxt.rbt;

import lejos.nxt.LCD;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Main {

	public static void main(String[] args) {		
		Behavior lightBe = new LightBe();
		Behavior rightBe = new RightBe();
		Behavior leftBe = new LeftBe();		
		Behavior[] bArray = {lightBe, rightBe, leftBe};
		Arbitrator arby = new Arbitrator(bArray);
		LCD.drawString("Started", 0, 0);
		arby.start();		
	}
}
