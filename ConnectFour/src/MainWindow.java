import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class MainWindow extends JFrame {
	public LogicBoard LB = new LogicBoard();
	public ImageContainer IC = new ImageContainer();
	DrawBoard DB = new DrawBoard();
	private int b1,b2,b3,b4,b5,b6,b7;
	private boolean isGameOver;
	private JButton button_1, button_2,  button_3,  button_4,  button_5,  button_6,  button_7;
	

    public MainWindow() {

        getContentPane().add(DB);
        DB.setLayout(null);
        
        
        
        button_1 = new JButton("1");
        button_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(b1<6){
        			isGameOver = DB.LB.InsertObject(0);
        			repaint();
        			b1++;
        			checkWin();
        		}
        		else{
        			button_1.setEnabled(false);
        		}
        		
        	}
        });
        button_1.setBounds(160, 648, 67, 25);
        DB.add(button_1);
        
        button_2 = new JButton("2");
        button_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(b2<6){
        			isGameOver = DB.LB.InsertObject(1);
        			repaint();
        			b2++;
        			checkWin();
        		}
        		else{
        			button_2.setEnabled(false);
        		}
        		
        	}
        });
        button_2.setBounds(240, 648, 67, 25);
        DB.add(button_2);
        
        button_3 = new JButton("3");
        button_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(b3<6){
        			isGameOver = DB.LB.InsertObject(2);
        			repaint();
        			b3++;
        			checkWin();
        		}
        		else{
        			button_3.setEnabled(false);
        		}
        		
        	}
        });
        button_3.setBounds(320, 648, 67, 25);
        DB.add(button_3);
        
        button_4 = new JButton("4");
        button_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(b4<6){
        			isGameOver = DB.LB.InsertObject(3);
        			repaint();
        			b4++;
        			checkWin();
        		}
        		else{
        			button_4.setEnabled(false);
        		}
        		
        	}
        });
        button_4.setBounds(400, 648, 67, 25);
        DB.add(button_4);
        
        button_5 = new JButton("5");
        button_5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(b5<6){
        			isGameOver = DB.LB.InsertObject(4);
        			repaint();
        			b5++;
        			checkWin();
        		}
        		else{
        			button_5.setEnabled(false);
        		}
        		
        	}
        });
        button_5.setBounds(480, 648, 67, 25);
        DB.add(button_5);
        
        button_6 = new JButton("6");
        button_6.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(b6<6){
        			isGameOver = DB.LB.InsertObject(5);
        			repaint();
        			b6++;
        			checkWin();
        		}
        		else{
        			button_6.setEnabled(false);
        		}
        		
        	}
        });
        button_6.setBounds(560, 648, 67, 25);
        DB.add(button_6);
        
        button_7 = new JButton("7");
        button_7.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(b7<6){
        			isGameOver = DB.LB.InsertObject(6);
        			repaint();
        			b7++;
        			checkWin();
        		}
        		else{
        			button_7.setEnabled(false);
        		}
        		
        	}
        });
        button_7.setBounds(640, 648, 67, 25);
        DB.add(button_7);
        
        final JSpinner spinner = new JSpinner();
        spinner.setModel(new SpinnerNumberModel(5, 1, 11, 2));
        spinner.setBounds(64, 411, 45, 22);
        DB.add(spinner);
        
        JButton btnNewGame = new JButton("New Game");
        btnNewGame.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		DB.LB = new LogicBoard();
        		DB.LB.setMovesAhead((Integer) spinner.getValue());
        		repaint();
        		b1=0;b2=0;b3=0;b4=0;b5=0;b6=0;b7=0;
        		button_1.setEnabled(true);
        		button_2.setEnabled(true);
        		button_3.setEnabled(true);
        		button_4.setEnabled(true);
        		button_5.setEnabled(true);
        		button_6.setEnabled(true);
        		button_7.setEnabled(true);
        	}
        });
        btnNewGame.setBounds(12, 373, 97, 25);
        DB.add(btnNewGame);
        
        
        
        JButton btnUseComputer = new JButton("Use Computer");
        btnUseComputer.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		DB.LB.SwitchComputer();
        	}
        });
        btnUseComputer.setBounds(12, 648, 136, 25);
        DB.add(btnUseComputer);
        
        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		System.exit(0);
        		
        	}
        });
        btnExit.setBounds(12, 520, 97, 25);
        DB.add(btnExit);
        
        

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(804, 800);
        setLocationRelativeTo(null);
        setTitle("Connect Four - PC");
        setResizable(false);
        setVisible(true);
        
    }
    private void checkWin(){
    	if(isGameOver){
    		button_1.setEnabled(false);
    		button_2.setEnabled(false);
    		button_3.setEnabled(false);
    		button_4.setEnabled(false);
    		button_5.setEnabled(false);
    		button_6.setEnabled(false);
    		button_7.setEnabled(false);
        }
    }
    
    private void InitializeButtons(){
    	
    }

    public static void main(String[] args) {
        new MainWindow();
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