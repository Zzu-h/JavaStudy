public abstract class Block {
	int x, y;
	int type;
	int rotation;
	int NumOfRotation;
	int[][][] shape;
	int width;
	int height;
	

	public int RightRotate() {
		return ((rotation + 1) % NumOfRotation);
	}

	public int LeftRotate() {
		return (((rotation - 1) + NumOfRotation) % NumOfRotation);
	}
	
	public int rShift() {
		return (x++);
	}
	public int lShift() {
		return (x--);
	}
	public int downShift() {
		return (y++);
	}
	public int[][] getShape(){
		return shape[rotation];
	}
}
