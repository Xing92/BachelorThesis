package minmax;

import java.util.ArrayList;
import java.util.List;

public class MinMax {

	private Node rootNode;
	
	public MinMax(){
		
	}
	
	public Node start(Board board){
		board.evaluateBoard();
		rootNode = new Node(board);
		populateNodes(rootNode);
		return rootNode;
	}
	
	private List<Node> populateNodes(Node rootNode){

		Board rootBoard = rootNode.getBoard();
		List<Board> listOfBoards = new ArrayList<Board>();
		List<Node> listOfNodes = new ArrayList<Node>();

		List<Move> listOfMoves = rootBoard.generateMoves();
		for(Move move : listOfMoves){
			Board board = rootBoard.makeMove(move);
			listOfBoards.add(board);
			Node node = new Node(board, rootNode);
			rootNode.addChild(node);
			listOfNodes.add(node);
			
			populateNodes(node);
		}
		
//		for(Board board : listOfBoards){
//			board.printBoard();
//		}
		
		return listOfNodes;
	}
}
