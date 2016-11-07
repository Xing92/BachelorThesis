import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


public class DrawBoard extends JPanel {

	public LogicBoard LB = new LogicBoard();
	public ImageContainer IC = new ImageContainer();
    private int currentPlayer = 1;

    public int getCurrentPlayer(){
    	return currentPlayer;
    }
    public void setCurrentPlayer(int currentPlayer){
    	this.currentPlayer = currentPlayer;
    }
    public DrawBoard() {
        setFocusable(true);
        setBackground(Color.WHITE);
        setDoubleBuffered(true);
    }


    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
         

        for(int i=0; i<7; i++){
        	for(int j=0; j<6; j++){
        		if(LB.board[i][j]==0){
        			g2d.drawImage(IC.getBlank(),155+80*i,160+80*j,this);
        		}
        		else if(LB.board[i][j]==1){
        			g2d.drawImage(IC.getCross(),155+80*i,160+80*j,this);
        		}
        		else if(LB.board[i][j]==-1){
        			g2d.drawImage(IC.getCircle(),155+80*i,160+80*j,this);
        		}
        	}
        }
    }
}