import java.awt.event.KeyAdapter;

public class BlockMove extends Thread {
	/*
	 * 블럭 무브는 기본적으로 숫자 패드
	 * 일단 방향키도 추가한다.
	 * 스페이스 바는 수직 하강
	 * 게임 종료는 End Key
	 * Pause는 Enter
	 */
	private	Block block;
	private	TetrisUI ui;
	private	Thread t;
	private	int x,y;
	private	int[][] place;
	private	int[][] temp;
	private	int k;
	public BlockMove(GameRun run, int key) {
		// TODO Auto-generated constructor stub
		this.block = run.newBlock;
		this.place = run.place;
		this.t = run.T;
		this.ui = run.ui;
		this.k = key;

		x = place[0].length;
		y = place.length;
		this.temp = new int[y][x];

		ActionRouter();

		System.out.println(k);

	}

	private void ActionRouter(){
		switch (k){
			case 37:
			case 100:
				//왼쪽
				LeftShift();
				break;
			case 39:
			case 102:
				//오른쪽
				RightShift();
				break;
			case 40:
			case 101:
				//아래
				DownShift();
				break;
			case 38:
			case 105:
				RightRotate();
				//R Rotate
				break;
			case 103:
				//L Rotate
				LeftRotate();
				break;
			case 32:
			case 98:
				//수직하강
				VerticalDrop();
				break;
			case 10:
				//Pause
				PauseGame();
				break;
			case 27:
				//EndGame
				EndGame();
				break;
		}
		setLayout();
	}
	private void LeftShift(){
		if(block.x == 0)
			return;
		else{
			boolean flag = false;
			for (int x = 0; x < block.width; x++) {
				for (int y = 0; y < block.height; y++) {
					if (block.shape[block.rotation][y][x] == 1 && place[block.y + y][block.x + x - 1] == 1)
						flag = true;
				}
			}
			if(flag)
				return;
		}
		block.lShift();
	}
	private void RightShift(){
		int x_size = x - 5;
		boolean flag = false;

		for (int x = 0; x < block.width; x++) {
			for (int y = 0; y < block.height; y++) {
				if (block.shape[block.rotation][y][x] == 1 && place[block.y + y][block.x + x + 1] == 1)
					flag = true;
			}
		}
		if(flag)
			return;
		block.rShift();
	}
	private boolean DownShift(){
		boolean flag = false;
		for (int y = 0; y < block.height; y++) {
			for (int x = 0; x < block.width; x++) {
				if((place[block.y + y + 1][block.x + x] == 1) && (block.shape[block.rotation][y][x] == 1))
					flag = true;
			}
		}
		if(flag)
			return true;
		block.downShift();
		return false;
	}
	private void RightRotate(){
		block.RightRotate();
	}
	private void LeftRotate(){
		block.LeftRotate();
	}
	private void VerticalDrop(){
		for (int y_ = 0; y_ < y; y_++) {
			if(DownShift())
				break;
		}
		t.interrupt();
	}
	private void PauseGame(){
		try {
			GameState.GamePause();
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	private void EndGame(){
		GameState.State = 4;
	}

	private void setLayout(){
		save();
		for (int y = 0; y < block.height; y++) {
			for (int x = 0; x < block.width; x++) {
				if (temp[block.y + y][block.x + x] != 1)
					place[block.y + y][block.x + x] = block.shape[block.rotation][y][x];
			}
		}

		ui.setLayout(place);
		load();
	}
	private void save() {
		for (int y = 0; y < this.y; y++) {
			for (int x = 0; x < this.x; x++) {
				temp[y][x] = place[y][x];
			}
		}
	}
	private void load() {
		for (int y = 0; y < this.y; y++) {
			for (int x = 0; x < this.x; x++) {
				place[y][x] = temp[y][x];
			}
		}
	}
}
