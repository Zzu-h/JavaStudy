package project.assignment;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;

public class Tetris extends JFrame implements ActionListener {

	int x_size = 500;
	int y_size = 800;
	int Grid_y = 22;
	int Grid_x = 10;
	JPanel Tet;
	JPanel[][] Tlayout;

	public Tetris() {
		// TODO Auto-generated constructor stub
		setTitle("테트리스");

		Tet = new JPanel(new GridLayout((Grid_y), (Grid_x)));
		Tlayout = new JPanel[Grid_y][Grid_x];

		add(Tet);
		makeMenu();
		makeLayout();

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
				Tlayout[y][x].add(new JLabel());
				Tlayout[y][x].setBorder(new LineBorder(Color.BLACK));
				Tlayout[y][x].setBackground(Color.LIGHT_GRAY);
				Tet.add(Tlayout[y][x]);
			}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Tetris();
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
		case "프록르램 종료":
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
