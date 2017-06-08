package minmax;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private Board board;
	private Node parent;
	private List<Node> children = new ArrayList<Node>();;
	private boolean isRootNode = false;
	
	private int value;

	public Node(Board board, Node parent) {
		this.board = board;
		this.parent = parent;
	}
	
	public Node(Board board) {
		this.board = board;
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
	
	public void printBoard(){
		board.printBoard();
	}
	
	public Board getBoard(){
		return board;
	}
	
}
