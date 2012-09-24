package nxt.rbt.behavior;

import lejos.robotics.subsumption.Behavior;
import nxt.rbt.limit.ColorLimits;
import nxt.rbt.navigation.LabyrinthNavigator;
import nxt.rbt.navigation.NavigationPilotPose;

public abstract class AbstractBehavior implements Behavior {
	
	protected LabyrinthNavigator navigator;
	protected NavigationPilotPose pilot;	
	
	public AbstractBehavior(LabyrinthNavigator navigator, NavigationPilotPose pilot) {
		this.navigator = navigator;
		this.pilot = pilot;		
	}
	
	protected boolean isInYellow(int value) {
		return (value > ColorLimits.YELLOW_LIMIT && value < ColorLimits.MAX_YELLOW_LIMIT); 
	}
}
