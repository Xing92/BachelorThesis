import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

public class LogicBoard {
	public int[][] board = new int[7][6];
	private int player;
	private boolean computer = true;
	private int movesAhead = 5;
	private boolean isGameOver = false;

	
	public LogicBoard(){
		player = -1;
	}
	
	public void setMovesAhead(int ma){
		movesAhead = ma;
	}
	
	public boolean InsertObject(int column){
		int row = RowForColumn(column);
		board[column][row] = player;
		
		CheckIfWinner(board);
		ChangeType();
		
		
		if(computer && player == 1 && !isGameOver){
			long start_time = System.nanoTime();
			
			InsertObject(MiniMax());//
			long end_time = System.nanoTime();
			double difference = (end_time - start_time)/1e6;
			System.out.println("Moves ahead: "+movesAhead+" - "+difference+ " ms");
		}
		return isGameOver;
	}
	
	public void SwitchComputer(){
		computer = (!computer);
		System.out.println("Computer switched to: " + computer);
		JOptionPane.showMessageDialog(null, "Computer switched to: " + computer);
	}
	
	
	
	//================
	//====================================  Private methods
	//================
	
	private int MiniMax(){
		int result = -1;
		int temp;
		int currentMax = -10000;
		int depth = 1;
		
		for(int i=0; i<7; i++){
			if(!IsColumnFull(i,board)){
				temp = MinMove(board,i,depth);
				if(temp>currentMax){
					currentMax = temp;
					result = i;
				}
			}
		}
		return result;
		
	}
	
	private int MinMove(int[][]b, int move, int depth){
		int [][] copy = new int[7][6];
		for(int i=0; i<7; i++){
			for(int j=0; j<6; j++){
				copy[i][j] = b[i][j];
			}
		}
		depth++;
		InsertObjectCopy(move, 1, copy);
		
		if(depth>movesAhead || GameEnded(copy)){
			return ScoreBoard(copy);
		}
		else{
			int minValue = 10000;
			int temp;
			for(int i=0; i<7; i++){
				if(!IsColumnFull(i,copy)){
					temp = MaxMove(copy,i,depth);
					if(temp<=minValue){
						minValue=temp;
					}
				}
			}
			return minValue;
		}
	}
	
	private int MaxMove(int[][] b , int move, int depth){
		int[][] copy = new int[7][6];
		for(int i=0; i<7; i++){
			for(int j=0; j<6; j++){
				copy[i][j] = b[i][j];
			}
		}
		depth++;
		InsertObjectCopy(move, -1, copy);
		if(depth>movesAhead || GameEnded(copy)){
			return ScoreBoard(copy);
		}
		else{
			int maxValue = -10000;
			int temp;
			for(int i=0; i<7; i++){
				if(!IsColumnFull(i,copy)){
					temp = MinMove(copy,i,depth);
					if(temp>=maxValue){
						maxValue=temp;
					}
				}
			}
			return maxValue;
			
		}
	}
	
	private boolean GameEnded(int[][] b)
    {

        int x=ScoreBoard(b);
        if (x == 10000 || x == -10000)
        {
            return true;          
        }
        return false;
    }
	
	private int ScoreBoard(int[][]b){
		int elem1=0,elem2=0,elem3=0,elem4=0;
		int score = 0;
		int result = 0;
		int[] Countertab = new int[9];
		
		for(int j=0; j<6; j++){
			for(int i=0; i<4; i++){
				elem1 = b[i][j];
				elem2 = b[i+1][j];
				elem3 = b[i+2][j];
				elem4 = b[i+3][j];
				score = elem1 + elem2+ elem3 + elem4 + 4;
				Countertab[score]++;
			}
		}
		
		for(int j=3; j<6; j++){
			for(int i=0; i<7; i++){
				elem1 = b[i][j];
				elem2 = b[i][j-1];
				elem3 = b[i][j-2];
				elem4 = b[i][j-3];
				score = elem1 + elem2 + elem3 + elem4 + 4;
				Countertab[score]++;
			}
		}
		
		for(int i=0; i<4; i++){
			for(int j=3; j<6; j++){
				elem1 = b[i][j];
				elem2 = b[i+1][j-1];
				elem3 = b[i+2][j-2];
				elem4 = b[i+3][j-3];
				score = elem1 + elem2 + elem3 + elem4 + 4;
				Countertab[score]++;
			}
		}
		
		for(int i=0; i<4; i++){
			for(int j=0; j<3; j++){
				elem1 = b[i][j];
				elem2 = b[i+1][j+1];
				elem3 = b[i+2][j+2];
				elem4 = b[i+3][j+3];
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
	
	private void InsertObjectCopy(int column, int type, int[][] b)
    {
        player = type;
        int row = RowForColumnCopy(column,b);
        b[column][row] = player;
    }
	
	int RowForColumnCopy(int column, int [][]b)
    {
        int[] tab = new int[6];
        for (int i = 0; i < 6; i++)
        {
            tab[i] = b[column][i];
        }

        for (int j = tab.length - 1; j >= 0; j--)
        {
            if (b[column][j] == 0)
            {
                return j;
            }
        }

        return 0;
    }
	
	private boolean IsColumnFull(int n, int[][]b){
		if(b[n][0]==0) return false;
		else return true;		
	}
	
	private void ChangeType(){
		if(player == -1) player=1;
		else player=-1;
	}
	
	private void CheckIfWinner(int[][]b){
		CheckWinnerCol(b);
		CheckWinnerRow(b);
		CheckWinnerDiagonallyRight(b);
		CheckWinnerDiagonallyLeft(b);
	}
	
	private void CheckWinnerCol(int[][]b){
		for(int i=0; i<7; i++){
			for(int j=5; j>=3; j--){
				if(board[i][j] == 1 && board[i][j] == board[i][j-1] && board[i][j] == board[i][j-2] && board[i][j] == board[i][j-3]){
					System.out.println("Computer Wins!");
					isGameOver=true;
					JOptionPane.showMessageDialog(null, "Computer Wins!");
				}
				if(board[i][j] == -1 && board[i][j] == board[i][j-1] && board[i][j] == board[i][j-2] && board[i][j] == board[i][j-3]){
					System.out.println("Player Wins!");
					isGameOver=true;
					JOptionPane.showMessageDialog(null, "Player Wins!");
				}
			}
		}
	}
	
	private void CheckWinnerRow(int[][]b){
		for(int i=0; i<4; i++){
			for(int j=0; j<6; j++){
				if(board[i][j] == 1 && board[i][j] == board[i+1][j] && board[i][j] == board[i+2][j] && board[i][j] == board[i+3][j]){
					System.out.println("Computer Wins!");
					isGameOver=true;
					JOptionPane.showMessageDialog(null, "Computer Wins!");
				}
				if(board[i][j] == -1 && board[i][j] == board[i+1][j] && board[i][j] == board[i+2][j] && board[i][j] == board[i+3][j]){
					System.out.println("Player Wins!");
					isGameOver=true;
					JOptionPane.showMessageDialog(null, "Player Wins!");
				}
			}
		}
	}
	
	private void CheckWinnerDiagonallyRight(int[][]b){
		int column;
		int row;
		
		for(column=0; column<4; column++){
			for(row=3; row<6; row++){
				if(b[column][row] == 1 && b[column][row] == b[column + 1][row - 1] && b[column][row] == b[column + 2][row - 2] && b[column][row] == b[column + 3][row - 3]){
					System.out.println("Computer Wins!");
					isGameOver=true;
					JOptionPane.showMessageDialog(null, "Computer Wins!");
				} 
				if(b[column][row] == -1 && b[column][row] == b[column + 1][row - 1] && b[column][row] == b[column + 2][row - 2] && b[column][row] == b[column + 3][row - 3]){
					System.out.println("Player Wins!");
					isGameOver=true;
					JOptionPane.showMessageDialog(null, "Player Wins!");
				} 
			}
		}
	}
	
	private void CheckWinnerDiagonallyLeft(int[][]b){
		int column;
		int row;
		
		for(column=0; column<4; column++){
			for(row=0; row<3; row++){
				if(b[column][row] == 1 && b[column][row] == b[column + 1][row + 1] && b[column][row] == b[column + 2][row + 2] && b[column][row] == b[column + 3][row + 3]){
					System.out.println("Computer Wins!");
					isGameOver=true;
					JOptionPane.showMessageDialog(null, "Computer Wins!");
				} 
				if(b[column][row] == -1 && b[column][row] == b[column + 1][row + 1] && b[column][row] == b[column + 2][row + 2] && b[column][row] == b[column + 3][row + 3]){
					System.out.println("Player Wins!");
					isGameOver=true;
					JOptionPane.showMessageDialog(null, "Player Wins!");
				} 
			}
		}
	}
	
	private int RowForColumn(int column){
		int[] tab = new int[6];
        for (int i = 0; i < 6; i++)
        {
            tab[i] = board[column][i];
        }

        for (int j = tab.length - 1; j >= 0; j--)
        {
            if (board[column][j] == 0)
            {
                return j;
            }
        }

        return 0;
	}
}
