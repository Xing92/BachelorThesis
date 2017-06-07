package connectfour;

import minmax.Move;

public class ConnectFourMove implements Move {

	private int player;
	private int move;

	public ConnectFourMove(int player, int move) {
		this.player = player;
		this.move = move;
	}

	public int getMove() {
		return move;
	}

	public int getPlayer() {
		return player;
	}

}
