package nxt.rbt;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import nxt.rbt.behavior.CrossingBe;
import nxt.rbt.behavior.EndLineBe;
import nxt.rbt.behavior.ForwardBe;
import nxt.rbt.behavior.LeftSensorBe;
import nxt.rbt.behavior.RightSensorBe;
import nxt.rbt.limit.NavigationLimits;
import nxt.rbt.navigation.LabyrinthNavigator;

public class Main {

	public static void main(String[] args) {
//		BluetoothConnector.getInstance().initialize();
		
		Button.ENTER.waitForPressAndRelease();
		DifferentialPilot pilot = new DifferentialPilot(2.25f, 4.8f, Motor.C, Motor.A);	
		pilot.setRotateSpeed(NavigationLimits.ROTATE_SPEED);
		pilot.setTravelSpeed(NavigationLimits.TRAVEL_SPEED);				
		OdometryPoseProvider poseProvider = new OdometryPoseProvider(pilot);
		LabyrinthNavigator navigator = new LabyrinthNavigator(poseProvider);
		
		Behavior forwardBe = new ForwardBe(navigator, pilot);
		Behavior rightBe = new RightSensorBe(navigator, pilot);
		Behavior leftBe = new LeftSensorBe(navigator, pilot);
		Behavior crossingBe = new CrossingBe(navigator, pilot);
		Behavior endBe = new EndLineBe(navigator, pilot);
//		Behavior[] bArray = {endBe,crossingBe, forwardBe, rightBe,leftBe};
		Behavior[] bArray = {endBe, forwardBe, crossingBe, rightBe,leftBe };
		Arbitrator arby = new Arbitrator(bArray);
		LCD.drawString("Started", 0, 0);
		arby.start();		
	}
}
