package minmax;

import java.util.ArrayList;
import java.util.List;

public class MinMax {

	private Node rootNode;

	public MinMax() {

	}

	public Node start(Board board) {
		board.evaluateBoard();
		rootNode = new Node(board);
		populateNodes(rootNode);
		return rootNode;
	}

	private List<Node> populateNodes(Node rootNode) {

		Board rootBoard = rootNode.getBoard();
		List<Board> listOfBoards = new ArrayList<Board>();
		List<Node> listOfNodes = new ArrayList<Node>();

		List<Move> listOfMoves = rootBoard.generateMoves();
		for (Move move : listOfMoves) {
			Board board = rootBoard.makeMove(move);
			listOfBoards.add(board);
			Node node = new Node(board, move, rootNode);
			rootNode.addChild(node);
			listOfNodes.add(node);

			populateNodes(node);
		}

		// for(Board board : listOfBoards){
		// board.printBoard();
		// }

		return listOfNodes;
	}

	public int minMax(Node node, int depth, int player, boolean maximizingPlayer){
		if(depth == 0){
			return player * node.getBoard().evaluateBoard();
		}
		int value;
		int bestValue;
		Board board = node.getBoard();
		if(maximizingPlayer){
			bestValue = -10_000;
			for(Move move : node.getBoard().generateMoves()){
				Board newBoard = board.makeMove(move);
				Node newNode = new Node(newBoard, move, node);
				int newPlayer = player == 1 ? -1 : 1;
				value = minMax(newNode, depth-1, player, !maximizingPlayer);
				bestValue = Math.max(bestValue,  value);
				newNode.setValue(bestValue);
			}
		}
		else{
			bestValue = 10_000;
			for(Move move : node.getBoard().generateMoves()){
				Board newBoard = board.makeMove(move);
				Node newNode = new Node(newBoard, move, node);
				int newPlayer = player == 1 ? -1 : 1;
				value = minMax(newNode, depth-1, player, !maximizingPlayer);
				bestValue = Math.min(bestValue,  value);
				newNode.setValue(bestValue);
			}
		}

//		node.printAllNodes();
		return bestValue;
	}

}
