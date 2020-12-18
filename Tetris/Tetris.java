import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;
import javax.swing.border.LineBorder;

class JLabelData extends JLabel{
    public int data = 0;
}

public class Tetris extends JFrame implements ActionListener{
    Thread t1;
    Thread t2;

    int x_size = 500;
    int y_size = 800;
    int Grid_y = 22;
    int Grid_x = 10;

    final int null_ = 5;

    int temp_count = 22;
    final Color LG = Color.LIGHT_GRAY;
    JPanel Tet;
    JPanel[][] Tlayout;
    JLabelData[][] jld;
    JLabelData[][] jld_temp;
    public Tetris() {
        // TODO Auto-generated constructor stub
        setTitle("테트리스");

        Tet = new JPanel(new GridLayout((Grid_y), (Grid_x)));
        Tlayout = new JPanel[Grid_y + null_][Grid_x + null_];
        jld = new JLabelData[Grid_y + null_][Grid_x + null_];
        jld_temp = new JLabelData[Grid_y + null_][Grid_x + null_];
        add(Tet);
        makeMenu();
        makeLayout();

        t1 = new Thread();
        t1.start();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(x_size, y_size);
        setVisible(true);
    }

    private void makeMenu() {
        JMenuItem item;
        KeyStroke key;

        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("게임");
        m1.setMnemonic(KeyEvent.VK_F);
        JMenu m2 = new JMenu("더보기");
        m1.setMnemonic(KeyEvent.VK_C);

        item = new JMenuItem("새 게임 시작하기", KeyEvent.VK_O);
        item.addActionListener(this);
        m1.add(item);

        m1.addSeparator();

        item = new JMenuItem("게임 저장", KeyEvent.VK_N);
        item.addActionListener(this);
        m1.add(item);

        item = new JMenuItem("게임 불러오기", KeyEvent.VK_O);
        item.addActionListener(this);
        m1.add(item);

        m1.addSeparator();

        item = new JMenuItem("게임 종료", KeyEvent.VK_O);
        item.addActionListener(this);
        m1.add(item);

        item = new JMenuItem("프로그램 종료", KeyEvent.VK_O);
        item.addActionListener(this);
        m1.add(item);

        item = new JMenuItem("파란색");
        key = KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK);
        item.setAccelerator(key);
        item.addActionListener(this);
        m2.add(item);

        item = new JMenuItem("빨간색");
        key = KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK);
        item.setAccelerator(key);
        item.addActionListener(this);
        m2.add(item);

        item = new JMenuItem("노란색");
        key = KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK);
        item.setAccelerator(key);
        item.addActionListener(this);
        m2.add(item);

        mb.add(m1);
        mb.add(m2);
        setJMenuBar(mb);
    }

    private void makeLayout() {
        Tet.setLayout(new GridLayout(Grid_y, Grid_x));
        for (int y = 0; y < Grid_y; y++)
            for (int x = 0; x < Grid_x; x++) {
                Tlayout[y][x] = new JPanel();
                jld[y][x] = new JLabelData();
                jld_temp[y][x] = new JLabelData();
                Tlayout[y][x].add(jld[y][x]);
                Tlayout[y][x].setBorder(new LineBorder(Color.BLACK));
                Tlayout[y][x].setBackground(LG);
                Tet.add(Tlayout[y][x]);
            }
        for (int i = 0; i < null_; i++)
            for (int j = 0; j < Grid_x; j++) {
                Tlayout[Grid_y+i][j] = new JPanel();
                jld[Grid_y+i][j] = new JLabelData();
                jld[Grid_y+i][j].data = 1;
                Tlayout[Grid_y+i][j].add(jld[Grid_y+i][j]);
                jld_temp[Grid_y+i][j] = new JLabelData();
            }

    }



    private Block createBlock(){
        int RandNum = (int)((Math.random() * 10) % 7);
        if(RandNum == 0)
            return new Iblock();
        else if(RandNum == 1)
            return new Jblock();
        else if(RandNum == 2)
            return new Lblock();
        else if(RandNum == 3)
            return new Oblock();
        else if(RandNum == 4)
            return new Sblock();
        else if(RandNum == 5)
            return new Tblock();
        else if(RandNum == 6)
            return new Zblock();
        return new Iblock();
    }
    private void BlockRun(){
        saveData();

        try {
            boolean flag = false;
            Block newBlock = createBlock();
            for (int y = 0; y < Grid_y; y++) {
                //하강 for문
                int x = newBlock.x;
                int r = newBlock.rotation;

                for (int h = 0; h < newBlock.height; h++){
                    for(int w = 0; w< newBlock.width; w++){
                        //int temp =
                        if(newBlock.shape[r][w][h] == 1) {
                            jld[h + y][w + x].data = newBlock.shape[r][w][h];
                        }
                        if((newBlock.shape[r][w][h] == 1)&&(newBlock.shape[r][w][h] == jld[h + y + 1][w+x].data)){
                            flag = true;
                        }
                        /*if (newBlock.shape[r][w][h] == 0) {
                            Tlayout[h + y][w + x].setBackground(LG);
                        } else {
                            Tlayout[h + y][w + x].setBackground(Color.BLUE);
                            if (Tlayout[h + y + 1][w + x].getBackground() == Tlayout[h + y][w + x].getBackground())
                                break;//쌓인 블럭과 만났을 때
                            else if ((h + y) == Grid_y)
                                break;//바닥에 닿였을 때
                        }*/
                    }
                }
                setupLayout();
                if(flag)
                    return;
                loadData();
                Thread.sleep(200);
            }

            Thread.sleep(800);
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    private void setupLayout(){
        for (int y = 0; y < Grid_y; y++)
            for (int x = 0; x < Grid_x; x++){
                if(jld[y][x].data == 0)
                    Tlayout[y][x].setBackground(LG);
                else
                    Tlayout[y][x].setBackground(Color.BLUE);
            }
    }
    void saveData(){
        for (int y = 0; y < Grid_y; y++)
            for (int x = 0; x < Grid_x; x++){
                jld_temp[y][x].data = jld[y][x].data;
            }
    }
    void loadData(){
        for (int y = 0; y < Grid_y; y++)
            for (int x = 0; x < Grid_x; x++){
                jld[y][x].data = jld_temp[y][x].data;
            }
        for (int i = 0; i < null_; i++)
            for (int j = 0; j < Grid_x; j++) {
                jld[Grid_y+i][j].data = 1;
            }
    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Tetris T = new Tetris();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem mi = (JMenuItem) (e.getSource());
        switch (mi.getText()) {
            case "새 게임 시작하기":
                System.out.println("새 파일");
                break;
            case "게임 저장":
                System.out.println("새 파일");
                break;
            case "게임 불러오기":
                System.out.println("파일 열기");
                break;
            case "게임 종료":
                System.out.println("파일 저장");
                break;
            case "프로그램 종료":
                System.out.println("파일 저장");
                break;
            case "파란색":
                this.getContentPane().setBackground(Color.BLUE);
                break;
            case "빨간색":
                this.getContentPane().setBackground(Color.RED);
                break;
            case "노란색":
                this.getContentPane().setBackground(Color.YELLOW);
                break;
        }
    }

}
