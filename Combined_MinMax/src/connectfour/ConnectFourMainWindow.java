package connectfour;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import minmax.Board;
import minmax.MinMax;
import minmax.Move;
import minmax.Node;
import resources.ImageContainer;
import tictactoe.TicTacToeBoard;

public class ConnectFourMainWindow extends JFrame {

	public JFrame frame;
	private Board board;
	private static ImageContainer IC = new ImageContainer();
	private JSpinner spinner;
	private JButton button_1, button_2, button_3, button_4, button_5, button_6, button_7;
	private int b1, b2, b3, b4, b5, b6, b7;
	boolean useAi = true;
	private MinMax minMax = new MinMax();


	/**
	 * Create the application.
	 */
	public ConnectFourMainWindow() {
		board = new ConnectFourBoard();
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame() {
			public void paint(Graphics g) {
				super.paint(g);
				Graphics2D g2d = (Graphics2D) g;
								for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 6; j++) {
						if (board.getBoard()[i][j] == 0) {
							g2d.drawImage(IC.getBlank(), 155 + 80 * i, 560 - 80 * j, this);
						} else if (board.getBoard()[i][j] == 1) {
							g2d.drawImage(IC.getCross(), 155 + 80 * i, 560 - 80 * j, this);
						} else if (board.getBoard()[i][j] == -1) {
							g2d.drawImage(IC.getCircle(), 155 + 80 * i, 560 - 80 * j, this);
						}
					}
				}
			}
		};
		frame.setTitle("Connect Four - PC");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 800, 800);
		frame.getContentPane().setLayout(null);
		

		JButton btnUsencomputer = new JButton("Use AI");
		btnUsencomputer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				useAi = !useAi;
				System.out.println("Computer switched to: " + useAi);
				JOptionPane.showMessageDialog(null, "Computer switched to: " + useAi);
			}
		});
		btnUsencomputer.setBounds(10, 495, 111, 25);
		frame.getContentPane().add(btnUsencomputer);

		button_1 = new JButton("1");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeMove(0, useAi);
			}
		});
		button_1.setBounds(158, 659, 67, 25);
		frame.getContentPane().add(button_1);

		button_2 = new JButton("2");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeMove(1, useAi);
			}
		});
		button_2.setBounds(238, 659, 67, 25);
		frame.getContentPane().add(button_2);

		button_3 = new JButton("3");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeMove(2, useAi);
			}
		});
		button_3.setBounds(318, 659, 67, 25);
		frame.getContentPane().add(button_3);

		button_4 = new JButton("4");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeMove(3, useAi);
			}
		});
		button_4.setBounds(398, 659, 67, 25);
		frame.getContentPane().add(button_4);

		button_5 = new JButton("5");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeMove(4, useAi);
			}
		});
		button_5.setBounds(478, 659, 67, 25);
		frame.getContentPane().add(button_5);

		button_6 = new JButton("6");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeMove(5, useAi);
			}
		});
		button_6.setBounds(558, 659, 67, 25);
		frame.getContentPane().add(button_6);

		button_7 = new JButton("7");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeMove(6, useAi);
			}
		});
		button_7.setBounds(638, 659, 67, 25);
		frame.getContentPane().add(button_7);

		JButton button_Exit = new JButton("Exit");
		button_Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0); // TODO: delete this
				frame.dispose();
			}
		});
		button_Exit.setBounds(10, 659, 111, 25);
		frame.getContentPane().add(button_Exit);

		spinner = new JSpinner();
		spinner.setBounds(76, 421, 45, 22);
		spinner.setModel(new SpinnerNumberModel(5, 1, 21, 1));
		frame.getContentPane().add(spinner);

		JButton button_NewGame = new JButton("New Game");
		button_NewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				board = new ConnectFourBoard();
				enableButtons();
				frame.repaint();
			}
		});
		button_NewGame.setBounds(10, 384, 111, 25);
		frame.getContentPane().add(button_NewGame);
		
		JButton button = new JButton("Make Move");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(board == null)System.out.println("Board null");
				if(spinner == null)System.out.println("Spinner null");
				if(board.getPlayer() == 0)System.out.println("Player zero");
				if(minMax == null)System.out.println("Minmax null");
				
				Node newNode = minMax.startMinMax(board, (Integer) spinner.getValue(), board.getPlayer());
				if(newNode.getBestMove() != null){
					Move newMove = new ConnectFourMove(board.getPlayer(), newNode.getBestMove().getMove(), (Integer) spinner.getValue());
					board = board.makeMove(newMove);
					frame.repaint();
					checkFinish();
				}
			}
		});
		button.setBounds(11, 531, 110, 23);
		frame.getContentPane().add(button);
	}

	
	public void test(){
		board.isGameFinished();
		if (useAi) {
			minMax = new MinMax();
			Node rootNode = minMax.startMinMax(board, (Integer) spinner.getValue(), board.getPlayer());
//			minMax.minMax(rootNode, (Integer) spinner.getValue());
//			rootNode.printValues();
//			System.out.println("Best move:" + rootNode.getBestMove().getMove());
//			board = board.makeMove(rootNode.getBestMove());
			
			//TODO TEMPORAR!
			Random generator = new Random();
//			int i = generator.nextInt(10);
			Move move1 = new ConnectFourMove(board.getPlayer(),generator.nextInt(7));
			board = board.makeMove(move1);
			frame.repaint();
		}
		
	}
	
	private void makeMove(int intMove, boolean useAi) {
		Move move = new ConnectFourMove(board.getPlayer(), intMove, (Integer) spinner.getValue());
		board = board.makeMove(move);
		
		frame.repaint(100);
		
		test();
		checkFinish();
//		frame.
		
		
//		board.isGameFinished();
	//	if (useAi) {
		//	minMax = new MinMax();
			//Node rootNode = minMax.startMinMax(board, (Integer) spinner.getValue(), board.getPlayer());
//			minMax.minMax(rootNode, (Integer) spinner.getValue());
//			rootNode.printValues();
			//System.out.println("Best move:" + rootNode.getBestMove().getMove());
			//board = board.makeMove(rootNode.getBestMove());
			
//			frame.repaint();

			// Node node = new Node(board);
			// ValueMove vm = minMax.minMax(board, move.getDepth());
			// System.out.println("Val: " + vm.getValue() + " Move: " +
			// vm.getMove().getMove());
			// Node result = minMax.generateTree(node, move.getDepth(), true);
			// System.out.println("Value: " + result.getValue() + ", Move: " +
			// result.getBestMove().getMove());

			// minMax = new MinMax();
			// Node node = new Node(board);
			//// int value = minMax.minMax(node, move.getDepth(),
			// board.getPlayer(), true);
			// ValueMove valueMove = minMax.alphaBeta(node, move.getDepth(),
			// board.getPlayer(), true, -10_000, 10_000);
			// Move responseMove = node.getBestMove();
			// board = board.makeMove(responseMove);
			//// node.printAllNodes();
			// node.printValues();
			//
			//// minMax.start(board);
			// frame.repaint();
			// System.out.println("Done minmax. Best value: " +
			// valueMove.getValue());
			// board.isGameFinished();
		//}
	}
	private void checkFinish(){
		if (board.checkWin((byte) 1)) {
			disableButtons();
			JOptionPane.showMessageDialog(null, "Player 1 wins");
		}
		else if (board.checkWin((byte) -1)) {
			disableButtons();
			JOptionPane.showMessageDialog(null, "Player 2 (AI) wins");
		}
		else if (board.isGameFinished()) {
			disableButtons();
			JOptionPane.showMessageDialog(null, "Nobody wins");
		}
	}
	
	private void disableButtons(){
		button_1.setEnabled(false);
		button_2.setEnabled(false);
		button_3.setEnabled(false);
		button_4.setEnabled(false);
		button_5.setEnabled(false);
		button_6.setEnabled(false);
		button_7.setEnabled(false);
	}
	
	private void enableButtons(){
		button_1.setEnabled(true);
		button_2.setEnabled(true);
		button_3.setEnabled(true);
		button_4.setEnabled(true);
		button_5.setEnabled(true);
		button_6.setEnabled(true);
		button_7.setEnabled(true);
	}
}
