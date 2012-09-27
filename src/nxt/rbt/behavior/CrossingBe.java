package nxt.rbt.behavior;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import nxt.rbt.graph.DijkstraAlgorithm;
import nxt.rbt.graph.Direction;
import nxt.rbt.graph.DirectionStates;
import nxt.rbt.graph.Node;
import nxt.rbt.limit.NavigationLimits;
import nxt.rbt.navigation.CrossingCounter;
import nxt.rbt.navigation.LabyrinthNavigator;
import nxt.rbt.navigation.NavigationPilotPose;

public class CrossingBe extends AbstractBehavior{

	LightSensor s1;
	LightSensor s2;
	LightSensor s3;
	boolean hasNode = false;
	
	public CrossingBe(LabyrinthNavigator navigator, NavigationPilotPose pilot) {
		super(navigator, pilot);
		s1 = new LightSensor(SensorPort.S1);
		s2 = new LightSensor(SensorPort.S2);
		s3 = new LightSensor(SensorPort.S3);		
	}
		
	@Override
	public boolean takeControl() {
		if ((isInYellow(s2.readValue()) && (isInYellow(s1.readValue()) || isInYellow(s3.readValue()))) ||
				(isInYellow(s3.readValue()) && isInYellow(s1.readValue())) && 
				(isInYellow(s2.readValue()) || !isInYellow(s2.readValue()))) {
			return true;
		} else
			return false;
	}

	@Override
	public void action() {
		CrossingCounter sc =  new CrossingCounter();
		hasNode = navigator.hasNode();
		// rechts, hinten, links, geradeaus
		if(!hasNode) {
			// zum scannen der kreuzung - wie viele abzweigungen vorhanden sind 
			pilot.travel(2.0);
			do {
				pilot.rotate(-1 * NavigationLimits.CROSSING_TURN_RATE_SEARCH);
				sc.addCurrentAngle(NavigationLimits.CROSSING_TURN_RATE_SEARCH);
				if(isInYellow(s2.readValue()) && sc.getCurrentAngle() > (sc.getAngleLastLine() + 20)) {
					int currentAngle = sc.getCurrentAngle();
					if(currentAngle >= 0 && currentAngle <= 20 || currentAngle > 300) {
						sc.setForward(pilot.getPose(), DirectionStates.POSSIBLE);
					} else if(currentAngle > 20 && currentAngle <= 100) {
						sc.setRight(pilot.getPose(), DirectionStates.POSSIBLE);
					} else if(currentAngle > 100 && currentAngle <= 210) {
						sc.setBackward(pilot.getPose(), DirectionStates.TAKEN);
					} else if(currentAngle> 210 && currentAngle <= 300) {
						sc.setLeft(pilot.getPose(), DirectionStates.POSSIBLE);
					}
					sc.addCount();
				}
			} while (sc.getCurrentAngle() < NavigationLimits.COMPLETE_ROTATION /*|| sc.getCurrentAngle() >= NavigationLimits.COMPLETE_ROTATION && !isInYellow(s2.readValue())*/);
			navigator.addNode(sc.getDirections());
		}
		
		Node node = navigator.getNodeForPosition();
		if(node != null) {
				if(hasNode)
					pilot.travel(2.0);
				
				double currentPose = pilot.getPose();
				
				if (node.getRightDirection(currentPose)!= null && node.getRightDirection(currentPose).getDirectionState() == DirectionStates.POSSIBLE) {
					// right
					sc.resetCurrentAngle();
					do {
						pilot.rotate(-1 * NavigationLimits.CROSSING_TURN_RATE);
						sc.addCurrentAngle(NavigationLimits.CROSSING_TURN_RATE);
					} while (sc.getCurrentAngle() < 20 || (!isInYellow(s2.readValue()) && sc.getCurrentAngle() >= 20));
					node.setRightDirectionState(currentPose, DirectionStates.TAKEN);
				} else if (node.getLeftDirection(currentPose) != null && node.getLeftDirection(currentPose).getDirectionState() == DirectionStates.POSSIBLE) {
					//left
					sc.resetCurrentAngle();
					do {
						pilot.rotate(NavigationLimits.CROSSING_TURN_RATE);
						sc.addCurrentAngle(NavigationLimits.CROSSING_TURN_RATE);
					} while (sc.getCurrentAngle() < 20 || (!isInYellow(s2.readValue()) && sc.getCurrentAngle() >= 20));
					node.setLeftDirectionState(currentPose, DirectionStates.TAKEN);
				} else if(node.getForwardDirection(currentPose) != null && node.getForwardDirection(currentPose).getDirectionState() == DirectionStates.POSSIBLE) {
					pilot.travel(2.0);
					node.setForwardDirection(currentPose, DirectionStates.TAKEN);
					return;
				} else {
					if(navigator.isNavigateToNode() || navigator.isNavigateToFinish()) {
						if(navigator.getCurrentPosNode() < navigator.getPath().size()) {
							Node currentNode = navigator.getPath().get(navigator.getCurrentPosNode());
							Direction direction = navigator.getDirectionToDrive(currentNode);
							driveToDirection(direction);
							if (navigator.getCurrentPosNode() >= navigator.getPath().size() -1) {
								driveToDirection(direction);
								if(currentNode.isStartNode()) {
									pilot.travel(4.0);
									sc.resetCurrentAngle();
									do {
										sc.addCurrentAngle(NavigationLimits.CROSSING_TURN_RATE_ENDLINE);
										pilot.rotate(NavigationLimits.CROSSING_TURN_RATE_ENDLINE);
									} while (sc.getCurrentAngle() < 100 || (!isInYellow(s2.readValue()) && sc.getCurrentAngle() >= 100));
									DijkstraAlgorithm alg = new DijkstraAlgorithm(navigator.getGraph());
									alg.execute(navigator.getStartNode());
									navigator.setPath(alg.getPath(navigator.getFinishNode()));
									navigator.setNavigateToFinish(true);
								} else {
									navigator.setNavigateToNode(false);
								}
						}
					} else {
						DijkstraAlgorithm alg = new DijkstraAlgorithm(navigator.getGraph());
						alg.execute(navigator.getNodeForPosition());
						if(navigator.isGraphfinished()) {
							// hier kommt die navigation zum start
							navigator.setPath(alg.getPath(navigator.getStartNode()));
							navigator.setNavigateToNode(true);
							Node currentNode = navigator.getPath().get(navigator.getCurrentPosNode());
							Direction direction = navigator.getDirectionToDrive(currentNode);
							driveToDirection(direction);
						
						} else {
							Node nodeToFinish = navigator.getNodeToFinish();
							if(nodeToFinish != null) {
								// hier kommt die navigation zum noch nicht fertigen knoten
								navigator.setPath(alg.getPath(nodeToFinish));
								navigator.setNavigateToNode(true);
								Node currentNode = navigator.getPath().get(navigator.getCurrentPosNode());
								Direction direction = navigator.getDirectionToDrive(currentNode);
								driveToDirection(direction);
							} else {
								// hier kommt die navigation zum start
								navigator.setPath(alg.getPath(navigator.getStartNode()));
								navigator.setNavigateToNode(true);
								Node currentNode = navigator.getPath().get(navigator.getCurrentPosNode());
								Direction direction = navigator.getDirectionToDrive(currentNode);
								driveToDirection(direction);
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void suppress() {
		
		
	}
	
	public void driveToDirection(Direction direction) {
		CrossingCounter sc =  new CrossingCounter();
		Node node = navigator.getNodeForPosition();
		if (node.getRightDirection(direction.getPose()) != null) {
			// right
			sc.resetCurrentAngle();
//			RConsole.println("Crossing: 4: rechts: " + sc.getCurrentAngle());
			do {
				pilot.rotate(-1 * NavigationLimits.CROSSING_TURN_RATE);
				sc.addCurrentAngle(NavigationLimits.CROSSING_TURN_RATE);
			} while (sc.getCurrentAngle() < 20 || (!isInYellow(s2.readValue()) && sc.getCurrentAngle() >= 20));
			navigator.incrementCurrentPosNode();
//			node.setCurrentDirection(Directions.RIGHT);
		} else if (node.getLeftDirection(direction.getPose()) != null) {
			//left
			sc.resetCurrentAngle();
//			RConsole.println("Crossing: 4: links: " + sc.getCurrentAngle());
			do {
				pilot.rotate(NavigationLimits.CROSSING_TURN_RATE);
				sc.addCurrentAngle(NavigationLimits.CROSSING_TURN_RATE);
			} while (sc.getCurrentAngle() < 20 || (!isInYellow(s2.readValue()) && sc.getCurrentAngle() >= 20));
			navigator.incrementCurrentPosNode();
//			node.setCurrentDirection(Directions.LEFT);
		} else if(node.getForwardDirection(direction.getPose()) != null) {
//			RConsole.println("Crossing: 4: geradeaus: ");
//			node.setCurrentDirection(Directions.FORWARD);
			pilot.travel(2.0);
			navigator.incrementCurrentPosNode();
			return;
		}
	}
	
//	public void setNodeForDirections(Node lasNode, Node currentNode) {
//		// rechts, hinten, links, geradeaus
//		Node[] nodesForDirection = lasNode.getNodesForDirection();
//		switch (lasNode.getCurrentDirection()) {
//		case LEFT:
//			nodesForDirection[2] = currentNode;
//			break;
//		case RIGHT:
//			nodesForDirection[0] = currentNode;
//			break;
//		case FORWARD:
//			nodesForDirection[3] = currentNode;
//			break;
//		default:
//			break;
//		}
//	}
	
}