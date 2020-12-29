
public class TetrisRun {
	TetrisUI UI;
	GameRun run;
	ScoreBoard SB;
	public TetrisRun() {
		// TODO Auto-generated constructor stub
		UI = new TetrisUI();
		SB = new ScoreBoard(UI);
		run = new GameRun(UI, SB);

		new GameState(this);
		UI.setText("시작하기 위해 'Enter'를 누르거나 '새 게임 시작'을 누르세요");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TetrisRun();
		
	}
}
