package minmax;

import java.util.List;

public interface Board {
		
	public int evaluateBoard();
	
	public boolean isGameFinished();
	
	public Board makeMove(Move move);
	
	public List<Move> generateMoves();
	
	public void printBoard();

}
