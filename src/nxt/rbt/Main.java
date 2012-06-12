package nxt.rbt;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Main {

	public static void main(String[] args) {
		Button.ENTER.waitForPressAndRelease();
		DifferentialPilot pilot = new DifferentialPilot(2.25f, 4.8f, Motor.C, Motor.A);	
		pilot.setRotateSpeed(NavigationLimits.ROTATE_SPEED);
		pilot.setTravelSpeed(NavigationLimits.TRAVEL_SPEED);
		Navigator navigator = new Navigator(pilot);
		OdometryPoseProvider poseProvider = new OdometryPoseProvider(pilot);
		
		Behavior forwardBe = new ForwardBe(navigator, pilot, poseProvider);
		Behavior rightBe = new RightSensorBe(navigator, pilot, poseProvider);
		Behavior leftBe = new LeftSensorBe(navigator, pilot, poseProvider);
		Behavior crossingBe = new CrossingBe(pilot);
		Behavior endBe = new EndLineBe(pilot);
//		Behavior[] bArray = {endBe,crossingBe, forwardBe, rightBe,leftBe};
		Behavior[] bArray = {forwardBe, rightBe,leftBe};
		Arbitrator arby = new Arbitrator(bArray);
		
		LCD.drawString("Started", 0, 0);
		arby.start();		
	}
}
