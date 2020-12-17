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
		setTitle("��Ʈ����");

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
		JMenu m1 = new JMenu("����");
		m1.setMnemonic(KeyEvent.VK_F);
		JMenu m2 = new JMenu("������");
		m1.setMnemonic(KeyEvent.VK_C);

		item = new JMenuItem("�� ���� �����ϱ�", KeyEvent.VK_O);
		item.addActionListener(this);
		m1.add(item);

		m1.addSeparator();

		item = new JMenuItem("���� ����", KeyEvent.VK_N);
		item.addActionListener(this);
		m1.add(item);

		item = new JMenuItem("���� �ҷ�����", KeyEvent.VK_O);
		item.addActionListener(this);
		m1.add(item);

		m1.addSeparator();

		item = new JMenuItem("���� ����", KeyEvent.VK_O);
		item.addActionListener(this);
		m1.add(item);

		item = new JMenuItem("���α׷� ����", KeyEvent.VK_O);
		item.addActionListener(this);
		m1.add(item);

		item = new JMenuItem("�Ķ���");
		key = KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK);
		item.setAccelerator(key);
		item.addActionListener(this);
		m2.add(item);

		item = new JMenuItem("������");
		key = KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK);
		item.setAccelerator(key);
		item.addActionListener(this);
		m2.add(item);

		item = new JMenuItem("�����");
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
		case "�� ���� �����ϱ�":
			System.out.println("�� ����");
			break;
		case "���� ����":
			System.out.println("�� ����");
			break;
		case "���� �ҷ�����":
			System.out.println("���� ����");
			break;
		case "���� ����":
			System.out.println("���� ����");
			break;
		case "���ϸ��� ����":
			System.out.println("���� ����");
			break;
		case "�Ķ���":
			this.getContentPane().setBackground(Color.BLUE);
			break;
		case "������":
			this.getContentPane().setBackground(Color.RED);
			break;
		case "�����":
			this.getContentPane().setBackground(Color.YELLOW);
			break;
		}
	}
}
