package connectfour;

import minmax.Board;

public class ConnectFourBoard implements Board{

	private int board[][] = new int[7][6];
	private int player;
	
	public ConnectFourBoard(int[][] board, int player){
		this.board = board;
		this.player = player;
	}
		
	public int scoreBoard(){
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
	
	public boolean isGameFinished()
    {

        int x=scoreBoard();
        if (x == 10000 || x == -10000)
        {
            return true;          
        }
        return false;
    }
}
