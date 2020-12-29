public class BlockDrop extends Thread {

	static int dropdelay = 1000;

	private Block block;
	private int[][] place;
	private int[][] temp;
	private int x_size;
	private int y_size;
	private TetrisUI ui;

	private boolean flag = false;
	
	public BlockDrop(GameRun run) {
		// TODO Auto-generated constructor stub
		this.block = run.newBlock;
		this.place = run.place;
		this.temp = run.place;
		this.ui = run.ui;

		x_size = place[0].length;
		y_size = place.length;
		this.temp = new int[y_size][x_size];

		randRotate();

		Run();
	}

	private void Run() {
		try {
			save();
			for (int k = 0; k < y_size; k++) {
				for (int y = 0; y < block.height; y++) {
					for (int x = 0; x < block.width; x++) {
						if(temp[block.y + y][block.x + x] != 1)
							place[block.y + y][block.x + x] = block.shape[block.rotation][y][x];
						if((temp[block.y + y + 1][block.x + x] == 1) && (block.shape[block.rotation][y][x] == 1))
							flag = true;
					}
				}
				block.downShift();
				ui.setLayout(place);
				if(flag)
					return;
				
				load();
				Thread.sleep(dropdelay);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
	}
	private void save() {
		for (int y = 0; y < y_size; y++) {
			for (int x = 0; x < x_size; x++) {
				temp[y][x] = place[y][x];
			}
		}
	}
	private void load() {
		for (int y = 0; y < y_size; y++) {
			for (int x = 0; x < x_size; x++) {
				place[y][x] = temp[y][x];
			}
		}
	}

	private void randRotate(){
		int i = ((int)(Math.random()*10)) % block.NumOfRotation;
		for(; i > 0 ; i--)
			block.RightRotate();
	}
}
