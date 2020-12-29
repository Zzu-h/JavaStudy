public class Oblock extends Block{
    public Oblock() {
        this.shape = new int[][][]
                {
                        {//[rotation][][] rotation에 따른 1번 모양
                                {1, 1},
                                {1, 1}
                        }
                };
        this.x = 4;
        this.y = 0;
        this.width = 2;
        this.height = 2;
        this.rotation = 0;
        this.type = 5;
        this.NumOfRotation = 1;
    }
    @Override
    public int RightRotate(){
        return (rotation = 0);
    }
    @Override
    public int LeftRotate(){
        return (rotation = 0);
    }
}
