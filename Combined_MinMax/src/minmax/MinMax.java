package minmax;

public class MinMax {

	private Node node;
	
	public MinMax(){
		
	}
	
	public void start(Board board){
		board.evaluateBoard();
		node = new Node(board);
		board.generateMoves();
		
		
	}
}
