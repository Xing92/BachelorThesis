package connectfour;

import java.util.ArrayList;
import java.util.List;

import minmax.Board;
import minmax.Move;

public class ConnectFourBoard implements Board{

	private int board[][];
	private int player;

	private int checkMovesAhead;
	
	private final static int BOARD_SIZE_X = 7;
	private final static int BOARD_SIZE_Y = 6;
	
	public ConnectFourBoard(){
		board = new int[7][6];
	}
	
	public ConnectFourBoard(int[][] board, int player){
		this.board = board;
		this.player = player;
	}
	
	public ConnectFourBoard(int[][] board, int player, int checkMovesAhead){
		this.board = board;
		this.player = player;
		this.checkMovesAhead = checkMovesAhead;
	}
	
	
	
	@Override
	public int evaluateBoard(){
		int elem1=0,elem2=0,elem3=0,elem4=0;
		int score = 0;
		int result = 0;
		int[] Countertab = new int[9];
		
		for(int j=0; j<6; j++){
			for(int i=0; i<4; i++){
				elem1 = board[i][j];
				elem2 = board[i+1][j];
				elem3 = board[i+2][j];
				elem4 = board[i+3][j];
				score = elem1 + elem2+ elem3 + elem4 + 4;
				Countertab[score]++;
			}
		}
		
		for(int j=3; j<6; j++){
			for(int i=0; i<7; i++){
				elem1 = board[i][j];
				elem2 = board[i][j-1];
				elem3 = board[i][j-2];
				elem4 = board[i][j-3];
				score = elem1 + elem2 + elem3 + elem4 + 4;
				Countertab[score]++;
			}
		}
		
		for(int i=0; i<4; i++){
			for(int j=3; j<6; j++){
				elem1 = board[i][j];
				elem2 = board[i+1][j-1];
				elem3 = board[i+2][j-2];
				elem4 = board[i+3][j-3];
				score = elem1 + elem2 + elem3 + elem4 + 4;
				Countertab[score]++;
			}
		}
		
		for(int i=0; i<4; i++){
			for(int j=0; j<3; j++){
				elem1 = board[i][j];
				elem2 = board[i+1][j+1];
				elem3 = board[i+2][j+2];
				elem4 = board[i+3][j+3];
				score = elem1 + elem2 + elem3 + elem4 + 4;
				Countertab[score]++;
			}
		}
		
		if (Countertab[0] != 0) {
            return -10000;
        }
        else if (Countertab[8] != 0) {
            return 10000;
        } 
        else{
        	result= +(Countertab[5] + 2 * Countertab[6] + 5 * Countertab[7] * 7 - Countertab[3] - 2 * Countertab[2] - 5 * Countertab[1] * 7);
        	return result;
        }
		
	}

	@Override
	public boolean isGameFinished()
    {
        int x=evaluateBoard();
        if (x == 10000 || x == -10000)
        {
            return true;          
        }
        return false;
    }
	
	@Override
	public List<Move> generateMoves() {
		if(isGameFinished() || checkMovesAhead < 1){	//TODO consider moving it into different place - somewhere earlier
			return null;
		}
		List<Move> moveList = new ArrayList<Move>();
		for(int column = 0; column < BOARD_SIZE_X; column++){
			if(isMoveDoable(column)){
				moveList.add(new ConnectFourMove(switchPlayer(), column));
			}
		}
		return moveList;
	}

	@Override
	public Board makeMove(Move move) {
		return makeMove((ConnectFourMove)move);
	}

	private Board makeMove(ConnectFourMove move){
		int newBoard[][] = board;
		
		isMoveDoable(move.getMove());
		
		
		
		return new ConnectFourBoard(newBoard, 0); // TODO
	}

	private boolean isMoveDoable(int column){
		if(board[column][BOARD_SIZE_Y - 1]!=0) return false;
		else return true;
		
	}
	
	private int switchPlayer(){
		return (player == 1) ? -1 : 1;
	}
}
