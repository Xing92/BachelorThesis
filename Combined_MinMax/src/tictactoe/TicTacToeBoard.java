package tictactoe;

import java.util.ArrayList;
import java.util.List;

import connectfour.ConnectFourMove;
import minmax.Board;
import minmax.Move;

public class TicTacToeBoard implements Board {

	private byte board[][];
	private byte player;

	private static byte BOARD_SIZE = 3;

	public TicTacToeBoard() {
		board = new byte[BOARD_SIZE][BOARD_SIZE];
		player = 1;
	}

	public TicTacToeBoard(byte[][] board, byte player) {
		this.board = board;
		this.player = player;
	}

	@Override
	public int evaluateBoard() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int evaluateBoard(byte player) {
		if (checkWin(player))
			return 10;
		else if (checkWin(switchPlayer()))
			return -10;
		return 0;
	}

	@Override
	public boolean isGameFinished() {
		return checkWin(player) || checkWin(switchPlayer()) || checkNoMoreMoves();
	}

	private boolean checkNoMoreMoves() {
		for (byte column = 0; column < BOARD_SIZE; column++) {
			for (byte row = 0; row < BOARD_SIZE; row++) {
				if (board[row][column] == 0)
					return false;
			}
		}
		return true;
	}

	public boolean checkWin(byte player) {

		return checkWinRows(player) || checkWinColumns(player) || checkWinDiagonals(player);
	}

	private boolean checkWinRows(byte player) {
		if (player == board[0][0] && player == board[0][1] && player == board[0][2]) {
			return true;
		} else if (player == board[1][0] && player == board[1][1] && player == board[1][2]) {
			return true;
		} else if (player == board[2][0] && player == board[2][1] && player == board[2][2]) {
			return true;
		}
		return false;
	}

	private boolean checkWinColumns(byte player) {
		if (player == board[0][0] && player == board[1][0] && player == board[2][0]) {
			return true;
		} else if (player == board[0][1] && player == board[1][1] && player == board[2][1]) {
			return true;
		} else if (player == board[0][2] && player == board[1][2] && player == board[2][2]) {
			return true;
		}
		return false;
	}

	private boolean checkWinDiagonals(byte player) {
		if (player == board[0][0] && player == board[1][1] && player == board[2][2]) {
			return true;
		} else if (player == board[0][2] && player == board[1][1] && player == board[2][0]) {
			return true;
		}
		return false;
	}

	@Override
	public Board makeMove(Move move) {
		byte newBoardInt[][] = copyBoardValues(board);
		byte newPlayer = player;
		if (isMoveDoable(move)) {
			newBoardInt[move.getMove() % BOARD_SIZE][move.getMove() / BOARD_SIZE] = player;
			newPlayer = switchPlayer();
		}
		TicTacToeBoard newBoard = new TicTacToeBoard(newBoardInt, newPlayer);
		return newBoard;
	}

	private byte switchPlayer() {
		return (byte) (player == 1 ? -1 : 1);
	}

	private boolean isMoveDoable(Move move) {
		return (board[move.getMove() % BOARD_SIZE][move.getMove() / BOARD_SIZE] == 0 ? true : false);
	}

	private byte[][] copyBoardValues(byte[][] board) {
		byte[][] newBoard = new byte[BOARD_SIZE][BOARD_SIZE];
		for (byte column = 0; column < BOARD_SIZE; column++) {
			for (byte row = 0; row < BOARD_SIZE; row++) {
				newBoard[column][row] = board[column][row];
			}
		}
		return newBoard;

	}

	@Override
	public List<Move> generateMoves() {
		if (isGameFinished()) {
			return new ArrayList<Move>();
		}
		List<Move> moveList = new ArrayList<Move>();
		for (byte column = 0; column < BOARD_SIZE; column++) {
			for (byte row = 0; row < BOARD_SIZE; row++) {
				Move move = new TicTacToeMove(player, column * 3 + row);
				if (isMoveDoable(move)) {
					moveList.add(move);
				}
			}
		}
		return moveList;
	}

	@Override
	public void printBoard() {
		// TODO Auto-generated method stub
	}

	@Override
	public byte[][] getBoard() {
		return board;
	}

	@Override
	public byte getPlayer() {
		return player;
	}

	@Override
	public Board getBoardCopy() {
		// TODO Auto-generated method stub
		return null;
	}

}
