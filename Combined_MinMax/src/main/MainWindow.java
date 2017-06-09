package main;

import java.util.Scanner;

import javax.xml.ws.Dispatch;

import connectfour.ConnectFourBoard;
import minmax.Board;
import minmax.MinMax;
import minmax.Node;

public class MainWindow {
	public static void main(String[] args) {

		int[][] b = new int[7][6];

		MinMax mm = new MinMax();
		Board board = new ConnectFourBoard(b, 1, 7);
		 Node node = mm.start(board);
//		Node node = new Node(board);

		System.out.println("Started");

		displayingResults(node);

		System.out.println("Finished");
	}

	private static void displayingResults(Node node) {
		int option;
		Node newNode = node;
		Scanner scanIn = new Scanner(System.in);
		whileloop: while (true) {
			option = Integer.parseInt(scanIn.nextLine());
			switch (option) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
				newNode = newNode.getChildren().get(option-1);
				newNode.printBoard();
				break;
			default:
				break whileloop;
			}

		}
		scanIn.close();
	}
}
