package minmax;

import java.util.List;

public interface Board {
		
	public int evaluateBoard();
	
	public int evaluateBoard(byte player);
	
	public boolean isGameFinished();
	
	public Board makeMove(Move move);
	
	public List<Move> generateMoves();
	
	public void printBoard();

	public byte[][] getBoard();
	
	public byte getPlayer();
	
	public boolean checkWin(byte player);
	
	public Board getBoardCopy();

}
