public class Jblock extends Block{
    public Jblock() {
        this.shape = new int[][][]
                {
                        {//[rotation][][] rotation에 따른 1번 모양
                                {0, 1, 0},
                                {0, 1, 0},
                                {1, 1, 0},

                        },
                        {//[rotation][][] rotation에 따른 2번 모양
                                {1, 0, 0},
                                {1, 1, 1},
                                {0, 0, 0},
                        },
                        {//[rotation][][] rotation에 따른 3번 모양
                                {1, 1, 0},
                                {1, 0, 0},
                                {1, 0, 0},

                        },
                        {//[rotation][][] rotation에 따른 4번 모양
                                {1, 1, 1},
                                {0, 0, 1},
                                {0, 0, 0},
                        }
                };
        this.x = 4;
        this.y = 0;
        this.width = 3;
        this.height = 3;
        this.rotation = 0;
        this.type = 3;
        this.NumOfRotation = 4;
    }

}
