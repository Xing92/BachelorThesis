package minmax;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;

import javax.swing.text.MaskFormatter;

import java.lang.instrument.Instrumentation;

public class MinMax {

	private static int generateTreeCounter;
	private static int goodAbCounter;
	private static int cutsCounter;

	public Node startMinMax(Board board, int depth, int player) {
		goodAbCounter = 0;
		cutsCounter = 0;
		
		long start_time = System.nanoTime();

		Node node = new Node(board);
		
//		Node node = generateTree(board, depth, player);

		long mid_time = System.nanoTime();

		minMax(node, depth, player);

		long end_time = System.nanoTime();
		double difference1 = (mid_time - start_time) / 1e6;
		double difference2 = (end_time - mid_time) / 1e6;
		double difference3 = (end_time - start_time) / 1e6;

//		System.out.println("Depth-Checks-Gen_time-MM_time: " + depth + " " + generateTreeCounter + " " + difference1 + " " + difference2);
		System.out.println("Depth-AB-Cuts-Gen_time-MM_time: " + depth + " " + goodAbCounter + " " + cutsCounter + " " + difference1 + " " + difference2);

		// System.out.println("Generate time: " + difference1 + " ms");
		// System.out.println("MinMax time: " + difference2 + " ms");
		// System.out.println("Depth: " + depth + " - " + difference3 + " ms");
		return node;
	}

	private int wholeMinMax() {

		return 0;
	}

	private int wholeMinMove() {
		return 0;
	}

	private int wholeMaxMove() {
		return 0;
	}

	// public ValueMove minMax(Board board, int depth) {
	// return maxMove(board, depth);
	// }
	//
	// private ValueMove maxMove(Board board, int depth) {
	// if (depth == 0 || board.isGameFinished()) {
	// ValueMove vm = new ValueMove(board.evaluateBoard(), null);
	// return vm;
	// }
	// ValueMove tempVM = null;
	// ValueMove bestVM = new ValueMove();
	// bestVM.setValue(-10_000);
	// List<Move> moveList = board.generateMoves();
	//
	// for (Move move : moveList) {
	// Board tempBoard = board.makeMove(move);
	// tempVM = minMove(tempBoard, depth - 1);
	// tempVM.setMove(move);
	// if (tempVM.getValue() > bestVM.getValue()) {
	// bestVM = tempVM;
	// }
	// }
	// return bestVM;
	// }
	//
	// private ValueMove minMove(Board board, int depth) {
	// if (depth == 0 || board.isGameFinished()) {
	// ValueMove vm = new ValueMove(board.evaluateBoard(), null);
	// return vm;
	// }
	// ValueMove tempVM = null;
	// ValueMove bestVM = new ValueMove();
	// bestVM.setValue(10_000);
	// List<Move> moveList = board.generateMoves();
	//
	// for (Move move : moveList) {
	// Board tempBoard = board.makeMove(move);
	// tempVM = minMove(tempBoard, depth - 1);
	// tempVM.setMove(move);
	// if (tempVM.getValue() < bestVM.getValue()) {
	// bestVM = tempVM;
	// }
	// }
	// return bestVM;
	// }

	private Node generateTree(Board board, int depth, int player) {
		generateTreeCounter = 0;
		Node rootNode = new Node(board);
		generateSubtree(rootNode, depth, player);
//		System.out.println("Generate Tree Counter: " + generateTreeCounter);
		return rootNode;
	}

	private void generateSubtree(Node subRootNode, int depth, int player) {
		Board board = subRootNode.getBoard();
		List<Move> moveList = board.generateMoves();

		if (depth == 0 || moveList.isEmpty()) {
			generateTreeCounter++;
			subRootNode.setValue(board.evaluateBoard((byte) player));
			return;
		}
		for (Move move : moveList) {
			Board tempBoard = board.makeMove(move);
			Node tempNode = new Node(tempBoard, move, subRootNode);
			generateSubtree(tempNode, depth - 1, player);
		}
	}

	private void minMax(Node node, int depth, int player) {
//		 maxMove(node);
//		alphaBeta(node, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
		goodAB(node.getBoard(), Integer.MIN_VALUE, Integer.MAX_VALUE, true, depth);
	}

	private int maxMove(Node node) {
		if (node.getChildren().isEmpty()) {
			return node.getValue();
		}
		int bestValue = Integer.MIN_VALUE;
		for (Node childNode : node.getChildren()) {
			int tempValue = minMove(childNode);
			childNode.setValue(tempValue);
			if (tempValue > bestValue) {
				bestValue = tempValue;
			}
		}
		return bestValue;
	}

	private int minMove(Node node) {
		if (node.getChildren().isEmpty()) {
			return node.getValue();
		}
		int bestValue = Integer.MAX_VALUE;
		for (Node childNode : node.getChildren()) {
			int tempValue = maxMove(childNode);
			childNode.setValue(tempValue);
			if (tempValue < bestValue) {
				bestValue = tempValue;
			}
		}
		return bestValue;
	}

	private int goodAB(Board board, int alpha, int beta, boolean maximizing, int depth){
//		if (node.getChildren().isEmpty()) {
//			return node.getValue();
//		}
		goodAbCounter++;
		if(depth == 0){
			return board.evaluateBoard();
		}
		
		if (maximizing) {
			for (Move move : board.generateMoves()) {
				Board newBoard = board.makeMove(move);
				int tempVal = goodAB(newBoard, alpha, beta, !maximizing, depth-1);
				alpha = (tempVal > alpha ? tempVal : alpha);
				if (alpha >= beta) {
//					 System.out.println("CUT!==========================" +
//					 alpha);
					cutsCounter++;
					return alpha;
				}
			}
			return alpha;
		} else {
			for (Move move : board.generateMoves()) {
				Board newBoard = board.makeMove(move);
				int tempVal = goodAB(newBoard, alpha, beta, !maximizing, depth-1);
				beta = (tempVal < beta ? tempVal : beta);
				if (alpha >= beta) {
//					 System.out.println("CUT!==========================" +
//					 beta);
					cutsCounter++;
					return beta;
				}
			}
			return beta;
		}
	}
	
	private int alphaBeta(Node node, int alpha, int beta, boolean maximizing) {
		if (node.getChildren().isEmpty()) {
			return node.getValue();
		}
		if (maximizing) {
			for (Node childNode : node.getChildren()) {
				int tempVal = alphaBeta(childNode, alpha, beta, !maximizing);
				alpha = (tempVal > alpha ? tempVal : alpha);
				childNode.setValue(alpha);
				if (alpha >= beta) {
					// System.out.println("CUT!==========================" +
					// alpha);
					return alpha;
				}
			}
			return alpha;
		} else {
			for (Node childNode : node.getChildren()) {
				int tempVal = alphaBeta(childNode, alpha, beta, !maximizing);
				beta = (tempVal < beta ? tempVal : beta);
				childNode.setValue(beta);
				if (alpha >= beta) {
					// System.out.println("CUT!==========================" +
					// beta);
					return beta;
				}
			}
			return beta;
		}
	}

	// public Node generateTree(Node node, int depth, boolean maximizingPlayer){
	// if(depth == 0){
	// node.setValue(node.getBoard().evaluateBoard());
	// return node;
	//
	// }
	// Board board = node.getBoard().getBoardCopy();
	//// List<Move> moveList = new ArrayList<Move>();
	// for(Move move : board.generateMoves()){
	//// moveList.add(move);
	// Board newBoard = board.makeMove(move);
	// Node newNode = new Node(newBoard, move, node);
	// Node value = generateTree(newNode, depth-1, !maximizingPlayer);
	// newNode.setValue(value.getValue());
	// }
	//// node.printAllNodes();
	// return node;
	// }

	// public int minMax(Node node, int depth, int player, boolean
	// maximizingPlayer) {
	// if (depth == 0) {
	// node.setValue(node.getBoard().evaluateBoard());
	// return player * node.getBoard().evaluateBoard();
	// }
	// int value;
	// int bestValue;
	// Board board = node.getBoard();
	// if (maximizingPlayer) {
	// bestValue = -10_000;
	// for (Move move : node.getBoard().generateMoves()) {
	// Board newBoard = board.makeMove(move);
	// Node newNode = new Node(newBoard, move, node);
	// int newPlayer = player == 1 ? -1 : 1;
	// value = minMax(newNode, depth - 1, player, !maximizingPlayer);
	// bestValue = Math.max(bestValue, value);
	// newNode.setValue(bestValue);
	// }
	// } else {
	// bestValue = 10_000;
	// for (Move move : node.getBoard().generateMoves()) {
	// Board newBoard = board.makeMove(move);
	// Node newNode = new Node(newBoard, move, node);
	// int newPlayer = player == 1 ? -1 : 1;
	// value = minMax(newNode, depth - 1, player, !maximizingPlayer);
	// bestValue = Math.min(bestValue, value);
	// newNode.setValue(bestValue);
	// }
	// }
	//
	// // node.printAllNodes();
	// return bestValue;
	// }

	// public ValueMove alphaBeta(Node node, int depth, int player, boolean
	// maximizingPlayer, int maxPlayerBestValue,
	// int minPlayerBestValue) {
	//
	// if (depth == 0) {
	// return new ValueMove(node.getBoard().evaluateBoard(), null);
	// // return /*player * */node.getBoard().evaluateBoard();
	// }
	//
	// int bestValue;
	// Move bestMove;
	// Board board = node.getBoard();
	// if (maximizingPlayer) {
	// List<Move> moveList = board.generateMoves();
	// bestMove = moveList.get(0);
	// bestValue = -10_000;
	// for (Move move : moveList) {
	// Board newBoard = board.makeMove(move);
	// Node newNode = new Node(newBoard, move, node);
	// ValueMove newValueMove;
	// newValueMove = alphaBeta(newNode, depth - 1, player, !maximizingPlayer,
	// minPlayerBestValue,
	// maxPlayerBestValue);
	// newNode.setValue(newValueMove.getValue());
	// if (newValueMove.getValue() >= bestValue) {
	// bestMove = move;
	// bestValue = newValueMove.getValue();
	// }
	// if (bestValue >= minPlayerBestValue) {
	// return new ValueMove(bestValue, bestMove);
	// }
	// }
	// return new ValueMove(bestValue, bestMove);
	// } else {
	// bestValue = 10_000;
	// List<Move> moveList = board.generateMoves();
	// bestMove = moveList.get(0);
	// for (Move move : moveList) {
	// Board newBoard = board.makeMove(move);
	// Node newNode = new Node(newBoard, move, node);
	// ValueMove newValueMove;
	// newValueMove = alphaBeta(newNode, depth - 1, player, !maximizingPlayer,
	// minPlayerBestValue,
	// maxPlayerBestValue);
	// if (newValueMove.getValue() <= bestValue) {
	// bestMove = move;
	// bestValue = newValueMove.getValue();
	// }
	// if (bestValue <= maxPlayerBestValue) {
	// return new ValueMove(bestValue, bestMove);
	// }
	// }
	// return new ValueMove(bestValue, bestMove);
	// }
	// }

}
