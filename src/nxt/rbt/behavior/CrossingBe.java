package nxt.rbt.behavior;

import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.comm.RConsole;
import lejos.robotics.navigation.DifferentialPilot;
import nxt.rbt.graph.Direction;
import nxt.rbt.graph.DirectionStates;
import nxt.rbt.graph.Node;
import nxt.rbt.limit.NavigationLimits;
import nxt.rbt.navigation.CrossingCounter;
import nxt.rbt.navigation.LabyrinthNavigator;

public class CrossingBe extends AbstractBehavior{

	LightSensor s1;
	LightSensor s2;
	LightSensor s3;
	boolean hasNode = false;
	
	public CrossingBe(LabyrinthNavigator navigator, DifferentialPilot pilot) {
		super(navigator, pilot);
		s1 = new LightSensor(SensorPort.S1);
		s2 = new LightSensor(SensorPort.S2);
		s3 = new LightSensor(SensorPort.S3);		
	}
		
	@Override
	public boolean takeControl() {
		
		if ((isInYellow(s2.readValue()) && (isInYellow(s1.readValue()) || isInYellow(s3.readValue()))) ||
				(isInYellow(s3.readValue()) && isInYellow(s1.readValue())) && 
				(isInYellow(s2.readValue()) || !isInYellow(s2.readValue()))) 
			return true;
		else
			return false;
	}

	@Override
	public void action() {
		RConsole.println("Ausgabe: Crossing: s2: " + s2.readValue() +" , s1: " + s1.readValue() + " , s3: " + s3.readValue());	
//		LCD.drawString("Crossing:" ,0, 0);
		CrossingCounter sc =  new CrossingCounter();
//		RConsole.println("Crossing: 1 " + navigator.hasNode());
		hasNode = navigator.hasNode();
		if(!hasNode) {
			// zum scannen der kreuzung - wie viele abzweigungen vorhanden sind 
			pilot.travel(2.0);
			do {
				pilot.rotate(-1 * NavigationLimits.CROSSING_TURN_RATE_SEARCH);
				sc.addCurrentAngle(NavigationLimits.CROSSING_TURN_RATE_SEARCH);
				if(isInYellow(s2.readValue()) && sc.getCurrentAngle() > (sc.getAngleLastLine() + 20)) {
					int currentAngle = sc.getCurrentAngle();
					if(currentAngle >= 0 && currentAngle <= 20 || currentAngle > 300) {
						sc.setForward(DirectionStates.POSSIBLE);
					} else if(currentAngle > 20 && currentAngle <= 100) {
						sc.setRight(DirectionStates.POSSIBLE);
					} else if(currentAngle> 190 && currentAngle <= 300) {
						sc.setLeft(DirectionStates.POSSIBLE);
					}
					sc.addCount();
				}
			} while (sc.getCurrentAngle() < NavigationLimits.COMPLETE_ROTATION);
			navigator.addNode(sc.getDirections());
			setNodeForDirections(navigator.getLastNode(), navigator.getCurrentNode());
		}
		
		Node node = navigator.getCurrentNode();
		DirectionStates[] states = navigator.getDirections();
		RConsole.println("Crossing: 2" + states);
		if(states != null) {
			RConsole.println("Crossing: 3" + states[0]+" , " +states[2]+" , "+states[3]);
			// zum abfahren der kreuzung nach rechts und links
			if(hasNode)
				pilot.travel(2.0);
			if (states[0] == DirectionStates.POSSIBLE) {
				// right
				sc.resetCurrentAngle();
				do {
					pilot.rotate(-1 * NavigationLimits.CROSSING_TURN_RATE);
				} while (!isInYellow(s2.readValue()) && sc.getCurrentAngle() < 20);
				states[0] = DirectionStates.TAKEN;
				navigator.updateDirectionsForNode(states);
				node.setCurrentDirection(Direction.RIGHT);
			} else if (states[2] == DirectionStates.POSSIBLE) {
				//left
				sc.resetCurrentAngle();
				do {
					pilot.rotate(NavigationLimits.CROSSING_TURN_RATE);
				} while (!isInYellow(s2.readValue()) && sc.getCurrentAngle() < 20);
				states[2] = DirectionStates.TAKEN;
				navigator.updateDirectionsForNode(states);
				node.setCurrentDirection(Direction.LEFT);
			} else if(states[3] == DirectionStates.POSSIBLE) {
				node.setCurrentDirection(Direction.FORWARD);
				return;
			}
		}
	}

	@Override
	public void suppress() {
		
		
	}
	
	public void setNodeForDirections(Node lasNode, Node currentNode) {
		// rechts, hinten, links, geradeaus
		Node[] nodesForDirection = lasNode.getNodesForDirection();
		switch (lasNode.getCurrentDirection()) {
		case LEFT:
			nodesForDirection[2] = currentNode;
			break;
		case RIGHT:
			nodesForDirection[0] = currentNode;
			break;
		case FORWARD:
			nodesForDirection[3] = currentNode;
			break;
		default:
			break;
		}
	}
	
}