
public class GameRun extends Thread {
	int[][] place;
	int x, y;
	TetrisUI ui;

	public GameRun(TetrisUI UI) {
		// TODO Auto-generated constructor stub
		this.ui = UI;
		x = 10;
		y = 22;

		setup();

		while (GameState.State == 1) {
			Block newBlock = createBlock();
			Thread T = new BlockDrop(newBlock, place, UI);
			Thread M = new BlockMove(newBlock, place,UI);
			place = ((BlockDrop) T).getData();
			
			print();
		}
	}

	private void setup() {
		int null_x = x + 5;
		int null_y = y + 5;
		place = new int[null_y][null_x];

		for (int j = 0; j < null_y; j++)
			for (int i = 0; i < null_x; i++)
				if (j < y && i < x)
					place[j][i] = 0;
				else
					place[j][i] = 1;
	}
	void print() {
		for (int y_ = 0; y_ < y; y_++) {
			for (int x_ = 0; x_ < x; x_++) {
				System.out.print(place[y_][x_] + " ");
			}
			System.out.println();
		}
	}

	private Block createBlock() {
		int RandNum = (int) ((Math.random() * 10) % 7);
		switch (RandNum) {
		case 0:
			return (new Iblock());
		case 1:
			return (new Tblock());
		default:
			return (new Iblock());
		}

	}
}
