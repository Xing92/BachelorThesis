package main;

import java.awt.Graphics;

import javax.swing.JFrame;

public class TestMain extends JFrame{
	
	public static void main(String[] args){
		TestMain tm = new TestMain();
	}
	
	TestMain(){
		System.out.println("start");
		repaint();
	}

	
	 public void paint(Graphics g) {
	        super.paint(g);
	        System.out.println("paint");
	 }
}
