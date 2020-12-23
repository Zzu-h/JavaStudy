
public class Tblock extends Block{

	public Tblock() {
		// TODO Auto-generated constructor stub
		 this.shape = new int[][][]
	                {
	                        {//[rotation][][] rotation에 따른 1번 모양
	                                {1, 0, 0},
	                                {1, 1, 0},
	                                {1, 0, 0},

	                        },
	                        {//[rotation][][] rotation에 따른 2번 모양
	                                {1, 1, 1},
	                                {0, 1, 0},
	                                {0, 0, 0},
	                        },
	                        {//[rotation][][] rotation에 따른 3번 모양
	                                {0, 1, 0},
	                                {1, 1, 0},
	                                {0, 1, 0},

	                        },
	                        {//[rotation][][] rotation에 따른 4번 모양
	                                {0, 1, 0},
	                                {1, 1, 1},
	                                {0, 0, 0},
	                        },
	                };

	                this.x = 3;
	                this.y = 0;
	                this.width = 3;
	                this.height = 3;
	                this.rotation = 0;
	                this.type = 2;
	                this.NumOfRotation = 4;
	}
	
}
