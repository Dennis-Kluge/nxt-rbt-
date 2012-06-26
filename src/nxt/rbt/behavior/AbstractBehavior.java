package nxt.rbt.behavior;

import nxt.rbt.limit.ColorLimits;
import nxt.rbt.navigation.LabyrinthNavigator;
import lejos.nxt.LCD;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.subsumption.Behavior;

public abstract class AbstractBehavior implements Behavior {
	
	protected LabyrinthNavigator navigator;
	protected DifferentialPilot pilot;	
	
	public AbstractBehavior(LabyrinthNavigator navigator, DifferentialPilot pilot) {
		this.navigator = navigator;
		this.pilot = pilot;		
	}
	
	protected boolean isInYellow(int value) {
		return (value > ColorLimits.YELLOW_LIMIT && value < ColorLimits.WHITE_LIMIT); 
}
}
