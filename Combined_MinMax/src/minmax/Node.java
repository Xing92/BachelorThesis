package minmax;

import java.util.ArrayList;

public class Node {
	private Board state;
	private Node parent;
	private ArrayList<Node> children;
	private boolean isRootNode = false;
	
	private int value;

	public Node(Board state, Node parent) {
		this.state = state;
		this.parent = parent;
	}
	
	public Node(Board state) {
		this.state = state;
		this.isRootNode = true;
	}
	
	public void addChild(Node child){
		children.add(child);
	}
	
	public void setValue(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
	
}
