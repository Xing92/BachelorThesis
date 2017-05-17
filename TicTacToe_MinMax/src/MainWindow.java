import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JSpinner;


public class MainWindow extends JFrame {
	public LogicBoard LB = new LogicBoard();
	public DrawBoard DB = LB.DB;
//	public DrawBoard DB = new DrawBoard();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new MainWindow();
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		getContentPane().add(LB.DB);
		DB.setLayout(null);
		initialize();
		MouseHandle MHandler = new MouseHandle();
        super.addMouseListener(MHandler);
		DB.repaint();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		DB.setLayout(null);

		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LB = new LogicBoard();
			}
		});
		btnNewGame.setBounds(10, 120, 111, 25);
		DB.add(btnNewGame);

		JSpinner spinner = new JSpinner();
		spinner.setBounds(10, 156, 45, 22);
		DB.add(spinner);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);

			}
		});
		btnExit.setBounds(10, 225, 111, 25);
		DB.add(btnExit);

		JButton btnUseComputer = new JButton("Use Computer");
		btnUseComputer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// LB.SwitchComputer(); TODO:
			}
		});
		btnUseComputer.setBounds(10, 189, 111, 25);
		DB.add(btnUseComputer);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		setLocationRelativeTo(null);
		setTitle("Connect Four - PC");
		setResizable(false);
		setVisible(true);
	}
	
	  private class MouseHandle implements MouseListener{
	  		public void mousePressed(MouseEvent event) {
	  			if((event.getX()>200 && event.getY()>100)){
	  				for(int i=0; i<3; i++){
	  					for(int j=0; j<3; j++){
	  						tile = LB.getTile(i, j); // TODO: make Tile class or board state 
	  						if((event.getX() > tile.getX()) && (event.getX() < tile.getX()+40) && (event.getY() > tile.getY()) && (event.getY() < tile.getY()+40)){
	  							
	  							
	  						}
	  					}
	  				}
	  				
	  				
//		  			if(!LB.isGameOver()){
//			  			for(int i=0; i<10; i++){
//			  	        	for(int j=0; j<10; j++){
//			  	                tile = LB.getTile(i, j);
//			  	                if((event.getX() > tile.getX()) && (event.getX() < tile.getX()+40) && (event.getY() > tile.getY()) && (event.getY() < tile.getY()+40)){
//			  	                	if(LB.checkCanPlace(i, j, currentPlayer)){
//				  	                	LB.changeTilePlayer(i, j, currentPlayer);
//				  	                	if(currentPlayer==2) currentPlayer=1;
//				  	                	else currentPlayer=2;
//			  	                	}
//			  	                }
//			  	        	}
//			  	        }
//			  			repaint();
//			  			
//		  			}
	  			}
  				System.out.println(event.getX());
	  		}
	  		public void mouseReleased(MouseEvent event) {
	  		}
	  		public void mouseClicked(MouseEvent event) {
	  		}
	  		public void mouseEntered(MouseEvent event) {	
	  		}
	  		public void mouseExited(MouseEvent event) {
	  		}
	      }
}
