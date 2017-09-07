package main;

import java.awt.Graphics;
import java.util.Scanner;

import javax.swing.JFrame;

import connectfour.ConnectFourBoard;
import connectfour.ConnectFourMove;
import minmax.Board;
import minmax.MinMax;
import minmax.Node;

public class TestMain extends JFrame{
	
	public static void main(String[] args){
		

		System.out.println("Max mem: " + Runtime.getRuntime().maxMemory());

		System.out.println("Started");

		long s1, s2, sum;
		s1 = Runtime.getRuntime().freeMemory();
		System.out.println("Before: " + s1);
		Board board = new ConnectFourBoard();
		s2 = Runtime.getRuntime().freeMemory();
		System.out.println("After: " + s2);
		sum = s1 - s2;
		System.out.println("Sum: " + sum);
	
	}
	
}
