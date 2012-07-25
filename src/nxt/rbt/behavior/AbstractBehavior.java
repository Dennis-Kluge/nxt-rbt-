package nxt.rbt.behavior;

import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;
import nxt.rbt.limit.ColorLimits;
import nxt.rbt.navigation.LabyrinthNavigator;

public abstract class AbstractBehavior implements Behavior {
	
	protected LabyrinthNavigator navigator;
	protected DifferentialPilot pilot;	
	
	public AbstractBehavior(LabyrinthNavigator navigator, DifferentialPilot pilot) {
		this.navigator = navigator;
		this.pilot = pilot;		
	}
	
	protected boolean isInYellow(int value) {
		return (value > ColorLimits.YELLOW_LIMIT && value < ColorLimits.MAX_YELLOW_LIMIT); 
	}
}
