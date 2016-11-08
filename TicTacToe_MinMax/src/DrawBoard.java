import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class DrawBoard extends JPanel {
	public static ImageContainer IC = new ImageContainer();

	public DrawBoard() {
		setFocusable(true);
		setBackground(Color.WHITE);
		setDoubleBuffered(true);
	}
	
	 public void paint(Graphics g) {
	        super.paint(g);
	        Graphics2D g2d = (Graphics2D)g;
	        for(int i=0; i<3; i++){
	        	for(int j=0; j<3; j++){
//	        		if(LB.board[i][j]==0){
	        			g2d.drawImage(IC.getBlank(),200 + 80*i, 100 + 80*j,this);
//	        		}
//	        		else if(LB.board[i][j]==1){
//	        			g2d.drawImage(IC.getCross(),155+80*i,160+80*j,this);
//	        		}
//	        		else if(LB.board[i][j]==-1){
//	        			g2d.drawImage(IC.getCircle(),155+80*i,160+80*j,this);
//	        		}
	        	}
	        }
	    }
}
