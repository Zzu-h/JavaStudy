
public class BlockMove extends Thread{
	Block block;
	int x,y;
	int[][] place;
	TetrisUI ui;
	public BlockMove(Block blk, int[][] arr, TetrisUI UI) {
		// TODO Auto-generated constructor stub
		this.block = blk;
		this.place = arr;
		this.ui = UI;
		
		x = place[0].length;
		y = place.length;
	}

}
