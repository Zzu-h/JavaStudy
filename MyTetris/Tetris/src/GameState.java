
public class GameState extends Thread{
	/*
	 * Game Initial Set State: 1
	 * Game Run State: 2
	 * Game Pause State:3
	 * Game End State: 4
	 */
	private static TetrisUI ui;
	private static GameRun R;
	private static ScoreBoard point;
	private static DataManage data;
	static int State = 1;


	public GameState(TetrisRun Run) {
		this.ui = Run.UI;
		this.R = Run.run;
		this.point = Run.SB;
		this.data = new DataManage();
	}

	public static boolean GameRun(){
		if(State == 1){
			State = 2;
			R.start();
			return true;
		}
		else if(State == 4){
			State = 2;
			point.setScore(0);
			point.update(0);
			R.initPlace();
			ui.setLayout(R.getPlace());
		}
		return false;
	}
	public static boolean GamePause(){
		try {
			if(State == 2) {
				State = 3;
				System.out.println("Pause: " + State);
				R.suspend();
				return true;
			}
			else if(State == 3) {
				State = 2;
				System.out.println("Play: " + State);
				R.resume();
				return true;
			}
			else {
				GameRun();
				return false;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
	public static boolean GameEnd(){
		if(State != 4) {
			State = 4;
			ui.setText("Game End \n Score: " + point.getScore());
			return true;
		}
		return false;
	}
	public static boolean GameSave(){
		GamePause();
		if(data.saveData(R.getPlace(), point.getScore())){
			ui.setText("Successfully saved");
			return true;
		}
		else{
			ui.setText("save failed");
			GamePause();
			return false;
		}
	}
	public static boolean GameLoad(){
		GameEnd();
		int[][] LoadData = data.loadData();
		int y = LoadData.length - 1;
		int x = LoadData[0].length - 1;
		int score = LoadData[y][x];
		System.out.println(score);
		LoadData[y][x] = 1;
		if(LoadData == null){
			ui.setText("Load failed");
			return false;
		}
		else {
			point.setScore(score);
			R.setPlace(LoadData);
			ui.setLayout(LoadData);
			ui.setText("Successfully Loaded");
			State = 1;
			GameRun();
			return true;
		}
	}
}
