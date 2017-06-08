package main;

import connectfour.ConnectFourBoard;
import minmax.Board;
import minmax.MinMax;

public class MainWindow {
	public static void main(String[] args) {

		int[][]b = new int[7][6];
		
		MinMax mm = new MinMax();
		Board board = new ConnectFourBoard(b, 1, 7);
		mm.start(board);
		
		System.out.println("Finished");
	}
}
