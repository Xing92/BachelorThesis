package connectfour;

import java.util.ArrayList;
import java.util.List;

import minmax.Board;
import minmax.Move;

public class ConnectFourBoard implements Board {

	private int board[][];
	private int player;

	private int checkMovesAhead;

	private static int iterationCounter = 0;
	private int selfIterationCounter;

	private final static int BOARD_SIZE_X = 7;
	private final static int BOARD_SIZE_Y = 6;

	public ConnectFourBoard() {
		board = new int[BOARD_SIZE_X][BOARD_SIZE_Y];
	}

	public ConnectFourBoard(int[][] board, int player) {
		this.board = board;
		this.player = player;
		selfIterationCounter = iterationCounter++;
	}

	public ConnectFourBoard(int[][] board, int player, int checkMovesAhead) {
		this(board, player);
		this.checkMovesAhead = checkMovesAhead;
	}

	@Override
	public int evaluateBoard() {
		int elem1 = 0, elem2 = 0, elem3 = 0, elem4 = 0;
		int score = 0;
		int result = 0;
		int[] Countertab = new int[9];

		for (int j = 0; j < 6; j++) {
			for (int i = 0; i < 4; i++) {
				elem1 = board[i][j];
				elem2 = board[i + 1][j];
				elem3 = board[i + 2][j];
				elem4 = board[i + 3][j];
				score = elem1 + elem2 + elem3 + elem4 + 4;
				Countertab[score]++;
			}
		}

		for (int j = 3; j < 6; j++) {
			for (int i = 0; i < 7; i++) {
				elem1 = board[i][j];
				elem2 = board[i][j - 1];
				elem3 = board[i][j - 2];
				elem4 = board[i][j - 3];
				score = elem1 + elem2 + elem3 + elem4 + 4;
				Countertab[score]++;
			}
		}

		for (int i = 0; i < 4; i++) {
			for (int j = 3; j < 6; j++) {
				elem1 = board[i][j];
				elem2 = board[i + 1][j - 1];
				elem3 = board[i + 2][j - 2];
				elem4 = board[i + 3][j - 3];
				score = elem1 + elem2 + elem3 + elem4 + 4;
				Countertab[score]++;
			}
		}

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				elem1 = board[i][j];
				elem2 = board[i + 1][j + 1];
				elem3 = board[i + 2][j + 2];
				elem4 = board[i + 3][j + 3];
				score = elem1 + elem2 + elem3 + elem4 + 4;
				Countertab[score]++;
			}
		}

		if (Countertab[0] != 0) {
			return -1000000;
		} else if (Countertab[8] != 0) {
			return 1000000;
		} else {
			result = +(Countertab[5] + 2 * Countertab[6] + 5 * Countertab[7] * 7 - Countertab[3] - 2 * Countertab[2]
					- 5 * Countertab[1] * 7);
			return result;
		}

	}

	@Override
	public boolean isGameFinished() {
		int x = evaluateBoard();
		if (x == 10000 || x == -10000) {
			return true;
		}
		return false;
	}

	@Override
	public List<Move> generateMoves() {
		if (isGameFinished() || checkMovesAhead < 1) { // TODO consider moving
														// it into different
														// place - somewhere
														// earlier
			return new ArrayList<Move>();
		}
		List<Move> moveList = new ArrayList<Move>();
		for (int column = 0; column < BOARD_SIZE_X; column++) {
			if (isMoveDoable(column)) {
				moveList.add(new ConnectFourMove(switchPlayer(), column, checkMovesAhead - 1));
			}
		}
		return moveList;
	}

	@Override
	public Board makeMove(Move move) {
		return makeMove((ConnectFourMove) move);
	}

	@Override
	public void printBoard() {
		System.out.println("Printing board: " + selfIterationCounter);
		for (int y = 0; y < BOARD_SIZE_Y; y++) {
			for (int x = 0; x < BOARD_SIZE_X; x++) {
				System.out.print(board[x][y]);
			}
			System.out.println();
		}
	}

	private Board makeMove(ConnectFourMove move) {
		int newBoard[][] = copyBoardValues(board);
		int column = move.getMove();
		System.out.println(selfIterationCounter + ": " + column);
		if (isMoveDoable(column)) {
			newBoard[column][getRowFromColumn(column)] = player;
		}

		int newPlayer = switchPlayer();
		return new ConnectFourBoard(newBoard, newPlayer, move.getDepth()) {
		}; // TODO
	}

	private boolean isMoveDoable(int column) {
		if (board[column][BOARD_SIZE_Y - 1] != 0)
			return false;
		else
			return true;

	}

	private int switchPlayer() {
		return (player == 1) ? -1 : 1;
	}

	private int getRowFromColumn(int column) {

		for(int row=0; row < BOARD_SIZE_Y; row++){
			if(board[column][row]==0){
				return row;
			}
		}
		return -1;
	}

	private int[][] copyBoardValues(int[][] board) {
		int[][] newBoard = new int[BOARD_SIZE_X][BOARD_SIZE_Y];
		for (int column = 0; column < BOARD_SIZE_X; column++) {
			for (int row = 0; row < BOARD_SIZE_Y; row++) {
				newBoard[column][row] = board[column][row];
			}
		}
		return newBoard;
	}
}
