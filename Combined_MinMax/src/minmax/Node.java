package minmax;

import java.util.ArrayList;

public class Node {
	private Board state;
	private Node parent;
	private ArrayList<Node> children;

	public Node(Board state, Node parent) {
		this.state = state;
		this.parent = parent;
	}
	
	public void addChild(Node child){
		children.add(child);
	}
	
}
