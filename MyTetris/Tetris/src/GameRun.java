import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class GameRun extends Thread {
	protected Thread T;
	protected int[][] place;
	protected int x, y;
	protected TetrisUI ui;
	protected Block newBlock;
	protected ScoreBoard point;

	public GameRun(TetrisUI UI, ScoreBoard SB) {
		// TODO Auto-generated constructor stub
		System.out.println(GameState.State);
		this.ui = UI;
		this.T = new Thread();
		this.point = SB;
		newBlock = createBlock();
		x = 10;
		y = 22;

		setup();
		ui.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				new BlockMove(GameRun.this,e.getKeyCode());
			}
		});

	}

	@Override
	public void run() {
		point.update(0);
		try {
			while(true){
				System.out.print("");
				while (GameState.State == 2) {
					T = new BlockDrop(this);
					T.start();
					//place = ((BlockDrop) T).getData();
					newBlock = createBlock();
					print();
					delCheck();
					endCheck();
				}
			}
		}catch (Exception e){
			GameState.State = 4;
		}
	}

	private void setup() {
		int null_x = x + 5;
		int null_y = y + 5;
		place = new int[null_y][null_x];

		initPlace();
	}
	private void print() {
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
			case 1:
				return (new Iblock());
			case 2:
				return (new Tblock());
			case 3:
				return (new Jblock());
			case 4:
				return (new Lblock());
			case 5:
				return (new Oblock());
			case 6:
				return (new Sblock());
			case 0:
				return (new Zblock());
			default:
				return (new Iblock());
		}

	}

	private void delCheck(){
		int count = 0;
		boolean flag = true;
		for(int i = 0; i < y; i++){
			for(int k = 0; k < x ; k++){
				if(place[i][k] != 1)
					break;
				else if(k == (x-1)) {
					delLine(i);
					count++;
				}
			}
		}
		if(count != 0) {
			//score Board
			int score = 100;
			count--;
			score = score * (1 << count);
			System.out.println(score);
			point.update(score);
		}
	}
	private void delLine(int idx){
		for(int i = idx; i > 0; i--)
			for(int k = 0; k< x;k++)
				place[i][k] = place[i - 1][k];
	}

	private void endCheck(){
		for(int k = 0; k < x; k++)
			if(place[0][k] == 1) {
				GameState.GameEnd();
				break;
			}
	}

	public int[][] getPlace(){
		return place;
	}
	public void setPlace(int arr[][]){
		this.place = arr;
	}
	public void initPlace(){

		int null_x = x + 5;
		int null_y = y + 5;
		for (int j = 0; j < null_y; j++)
			for (int i = 0; i < null_x; i++)
				if (j < y && i < x)
					place[j][i] = 0;
				else
					place[j][i] = 1;
	}
}
