import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class TetrisUI extends JFrame implements ActionListener {
	int x_size = 10;
	int y_size = 22;

	JPanel[][] tetris = new JPanel[y_size][x_size];
	JLabel score = new JLabel("0");
	public TetrisUI() {
		// TODO Auto-generated constructor stub
		setTitle("MyTetris");
		this.setBackground(Color.blue);
		menuBar();
		mainUI();
		scoreBoard();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(x_size*50,y_size*45);
		setVisible(true);
	}
	void menuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenuItem item;
		JMenu menu = new JMenu("����");
		
		item = new JMenuItem("�� ���� ����");
		item.addActionListener(this);
		menu.add(item);
		
		menu.addSeparator();
		
		item = new JMenuItem("���� ����");
		item.addActionListener(this);
		menu.add(item);
		
		item = new JMenuItem("���� �ҷ�����");
		item.addActionListener(this);
		menu.add(item);
		
		menu.addSeparator();
		
		item = new JMenuItem("���� ����");
		item.addActionListener(this);
		menu.add(item);
		
		item = new JMenuItem("���α׷� ����");
		item.addActionListener(this);
		menu.add(item);
		
		menuBar.add(menu);
		
		
		menu = new JMenu("�߰�");
		item = new JMenuItem("����");
		item.addActionListener(this);
		menu.add(item);
		
		menuBar.add(menu);
		
		setJMenuBar(menuBar);
	}
	void mainUI() {
		JPanel mainUI = new JPanel();
		mainUI.setLayout(new GridLayout(y_size,x_size));
		
		for(int y = 0;y<y_size;y++){
			for(int x=0;x<x_size;x++)  {
				tetris[y][x] = new JPanel();
				tetris[y][x].setBackground(Color.white);
				tetris[y][x].setBorder(new LineBorder(Color.BLACK));
				mainUI.add(tetris[y][x]);
			}
		}
		
		
		mainUI.setBackground(Color.orange);
		add(mainUI, BorderLayout.CENTER);
	}
	void setLayout(int arr[][]) {
		for(int y = 0;y<y_size;y++){
			for(int x=0;x<x_size;x++)  {
				if(arr[y][x] == 1)
					tetris[y][x].setBackground(Color.magenta);
				else
					tetris[y][x].setBackground(Color.white);
			}
		}
	}
	
	void scoreBoard() {
		JPanel board = new JPanel();
		
		board.add(score);
		board.setBorder(new LineBorder(Color.BLACK));
		board.setBackground(Color.white);
		add(board, BorderLayout.SOUTH);
	}
	void setScore(int point) {
		this.score.setText(""+point);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
		case "�� ���� ����":
			GameState.State = 2;
			break;
		case "���� ����":
			GameState.State = 3;
			break;
		case "���� �ҷ�����":
			GameState.State = 4;
			break;
		case "���α׷� ����":
			System.exit(0);
			break;
		case "���� ����":
			GameState.State = 4;
			break;
		default:
			break;
		}
	}
}
