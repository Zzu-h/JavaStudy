public class BlockDrop extends Thread {
	
	Block block;
	int[][] place;
	int[][] temp;
	int x_size;
	int y_size;
	TetrisUI ui;
	
	boolean flag = false;
	
	public BlockDrop(Block blk, int[][] arr, TetrisUI UI) {
		// TODO Auto-generated constructor stub
		this.block = blk;
		this.place = arr;
		this.temp = arr;
		this.ui = UI;
		
		x_size = place[0].length;
		y_size = place.length;
		this.temp = new int[y_size][x_size];
		//setup();
		
		Run();
	}
	void setup() {
		for (int y = 0; y < y_size; y++) {
			for (int x = 0; x < x_size; x++) {
				temp[y][x] = 0;
			}
		}
	}

	void Run() {
		try {
			save();
			for (int k = 0; k < y_size; k++) {
				for (int y = 0; y < block.height; y++) {
					for (int x = 0; x < block.width; x++) {
						if(place[block.y + y][block.x + x] != 1)
							place[block.y + y][block.x + x] = block.shape[block.rotation][y][x];
						if((place[block.y + y + 1][block.x + x] == 1) && (block.shape[block.rotation][y][x] == 1))
							flag = true;
					}
				}
				block.downShift();
				ui.setLayout(place);
				if(flag)
					return;
				
				load();
				Thread.sleep(500);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
	}
	void save() {
		for (int y = 0; y < y_size; y++) {
			for (int x = 0; x < x_size; x++) {
				temp[y][x] = place[y][x];
			}
		}
	}
	void load() {
		for (int y = 0; y < y_size; y++) {
			for (int x = 0; x < x_size; x++) {
				place[y][x] = temp[y][x];
			}
		}
	}
	
	int[][] getData(){
		return place;
	}
}
