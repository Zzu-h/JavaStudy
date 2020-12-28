# Tetris
### 게임 규칙
1. 게임은 실행과 동시에 자동 시작한다.
2. 블럭의 회전 및 좌우 움직임은 방향키로 수행한다.
3. 내려오기는 스페이스바 이다.
4. 게임 종료는 ESC또는 메뉴 바에 있는 게임 종료 버튼을 하면 게임이 종료된다.
5. 프로그램 종료는 메뉴 바에 있는 프로그램 종료와 동시에 프로그램이 종료가 된다.
6. 게임 불러오기는 ESC를 통한 게임이 종료된 후 게임을 불러온다.

---
## UI 만들기    
테트리스를 만들기 위해 JFrame을 통해 게임 틀을 생성한다.
```java
import javax.swing.JFrame;

public class Tetris extends JFrame{
	int x_size = 500;
	int y_size = 800;
	public Tetris() {
		// TODO Auto-generated constructor stub
		setTitle("테트리스");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(x_size, y_size);
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Tetris();
	}
}

```
그 다음 상단 메뉴 바를 만들어 준다.    
상단 메뉴 바의 조건은 다음과 같다.
- 파일저장(2) / 가져오기(3)
- 블록 랜덤시작(3)
- 종료 : 게임종료(3) / 프로그램 종료(2)
```java
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

        item = new JMenuItem("게임 저장 후 종료", KeyEvent.VK_N);
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

	@Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem mi = (JMenuItem) (e.getSource());
        switch (mi.getText()) {
            case "새 게임 시작하기":
                endFlag = true;
                endFlag = false;
                setData0();
                setupLayout();
                PlayRun();
                break;
            case "게임 불러오기":
                endFlag = false;
                load();
                setupLayout();
                PlayRun();
                break;
            case "게임 저장 후 종료":
                endFlag = false;
                save();
            case "프로그램 종료":
                System.exit(0);
                break;
            case "게임 종료":
                endFlag = true;
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

```

다음은 LayoutGrid를 통해 Layout을 만들어 준다.
```java

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

	private void makeLayout() {
		Tet.setLayout(new GridLayout(Grid_y, Grid_x));
		for (int y = 0; y < Grid_y; y++)
			for (int x = 0; x < Grid_x; x++) {
				Tlayout[y][x] = new JPanel();
				Tlayout[y][x].add(new JLabel());
				Tlayout[y][x].setBorder(new LineBorder(Color.BLACK));
				Tlayout[y][x].setBackground(Color.LIGHT_GRAY);
			}

	}
}
```
Tet라는 main Panel을 만들어주고, Tlayout을 통해 10 * 22 grid layout을 만들어 준다.    
구분을 하기 위해 setBorder라는 메소드를 이용하여 검은 선을 그어주었다.    

<br>

----
## Block 형태 갖추기
다음은 각각의 모양을 만들어 준다.    
전체 모양의 개수는 7가지 이다. 회전을 위한 알고리즘은 나중에 알아보도록 하자.    
<br>
Block이라는 최상위 모양클래스를 만들어주고 그의 파생 클래스로 각 블럭을 만든다.     
각 모양에 따라 임의로 O, J, L, I, Z, S, T라 칭하겠다.     
- Block class
    ```java
    public class Block {
        public int x;
        public int y;
        public int width;
        public int height;
        public int rotation;
        public int typeN;
        public int[][][] shape;

        public void rotateRight(){
            rotation = (rotation + 1) % typeN;
        }
        public void rotateLeft(){
           rotation = (typeN + rotation - 1) % typeN;
        }
        public void leftShift(){
            this.x--;
        }
        public void rightShift(){
            this.x++;
        }
        public void downShift(){
            this.y++;//2차원 배열: 숫자가 커질 수록 우측 하향
        }
        public int[][] getShape(){
            return shape[rotation];
        }
    }

    ```
    - Rotate
        - Right: shape 배열의 첫번째 칸이 각 블럭의 모양을 의미한다.    
            0번일때, 1번일때, 등 각각을 의미하므로 rotation이라는 변수로 각 타입을 정해둔다.    
            rotation횟수에 따라 각 모양을 바꾼다.    
            하지만, 오른쪽으로 도는 횟수가 무한적으로 더해질 수 없으므로 typeN에 전체 모양 수를 미리 저장해 두어 각 회전 수를 rotate한 전체 모양의 수로 제한한다.
        - Left: 왼쪽으로 도는 횟수는 rotation -1만큼 이전 모양으로 돌려준다.    
            하지만 index는 음의 값을 가질 수 없으므로 0 이상에서 돌아야 한다.    
            rotateRight에서 미리 rotation 수를 0 ~ typeN만큼 제한을 두었으므로 여기서 또한 0~ typeN만큼의 제한 안에서 회전을 한다.
- 각 변수 설명
    - x, y: 각 블럭이 UI에 표시될 좌표    
        초기에 내려올 위치를 미리 설정해둔다. (따라서, 항상 y = 0이다.)
    -  width, height: 각 블럭이 rotation하는데 필요한 최소 공간이다.    
            예를 들면, Iblock 같은 경우, 가로 4 세로 4만큼의 공간이 필요하다.    
            따라서, width = height = 4이다.
    - rotation: 총 회전 횟수겸, 현 블럭의 상태이다. rotation 횟수에 따라 각 블럭의 모양이 변한다.
    - typeN: 각 블럭이 가질 수 있는 모양의 총 수 이다.
    - shape: 각 블럭이 가지는 형태이다. 각 모양은 1로 표현한다.

<br>

---
## 게임 동작 메소드 구현

1. 블럭 생성하기
	```java
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
	```
	- Math.random함수를 통해 각 블럭을 임의로 생성한다.
		- Math.random함수는 0~1.0 사이의 임의의 수 이므로 10을 곱하고, 전체 경우의 수인 7로 제한을 한다.
		- 각각의 경우에 따라 Iblock부터 Zbloclk까지 임의 생성을하여  반환한다.

2. 블럭 내려오기
	```java
	private void BlockRun(Block newBlock){
				saveData();

				try {

					for (int y = 0; y < Grid_y; y++) {
						//하강 for문
						int x = newBlock.x;
						int r = newBlock.rotation;

						for (int h = 0; h < newBlock.height; h++){
							for(int w = 0; w< newBlock.width; w++){
								if(newBlock.shape[r][w][h] == 1) {
									jld[h + y][w + x].data = newBlock.shape[r][w][h];
								}
								if((newBlock.shape[r][w][h] == 1)&&(newBlock.shape[r][w][h] == jld[h + y + 1][w+x].data)){
									flag = true;
								}

							}
						}
						setupLayout();
						if(flag && (y == 0)) {
							endFlag = true;
							return;
						}
						else if(flag) {
							lineCheck();
							setupLayout();
							return;
						}
						loadData();
						Thread.sleep(500);
					}

				}catch (Exception e){
					endFlag = true;
					System.out.println("Error: " + e.getMessage());
				}
			}
	```
	- jld라는 jlabel 배열 객체에 내려오는 블럭과 비교하여 초기조건 0과 비교를한다.
	- 내려오는 블럭의 모양이 즉, 모양을 표시해야할 데이터 배열이 1일 경우 jld에도 1로 새로 저장해준다.
	- 그 후 setLayout메소드를 통해 ui를 갱신해준다.
	- 이를 0.5초 동안의 텀을 주고 바딕 또는 다른 블럭과 맞닿을 때가지 반복한다.
	- 혹여 다른 블럭과 또는 바닥과 닿을 경우 flag 변수를 통해 이 블럭의 동작이 끝남을 알리고 이 메소드를 종료한다.
	- 또한 첫 생성과 동시에 바닥 또는 다른 블럭과 맞닿았다면 이미 끝난 블럭, 게임이므로 종료하는 endFlag에 True를 넣어준다.

3. 레이아웃 재설정
	```java
	private void setupLayout(){
					for (int y = 0; y < Grid_y; y++)
						for (int x = 0; x < Grid_x; x++){
							if(jld[y][x].data == 0)
								Tlayout[y][x].setBackground(LG);
							else
								Tlayout[y][x].setBackground(Color.BLUE);
						}
				}
	```
	- 전체 ui를 갱신한다.
4. 레이아웃 데이터 불러오기 가져오기
	```java
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
	```
	- jld_temp 객체에 이전 레이아웃 데이터를 현재 블럭 내려오는 데이터를 갱신하기 위해 저장한다.

5. 블럭 좌우, 회전 이동
	```java
		private void BlockMove(Block newBlock, int key){
			switch (key) {
				case 37:
					if(newBlock.x == 0)
						break;
					newBlock.leftShift();
					break;
				case 38:
					newBlock.rotateRight();
					break;
				case 39:
					if((newBlock.x+newBlock.width) == (Grid_x + 3))
						break;
					newBlock.rightShift();
					break;
				case 40:
					newBlock.rotateLeft();
					break;
				case 32:
					/*endFlag = false;
					PlayRun();*/
					break;
				case 27:
					//게임 종료
					endFlag = true;
					break;
				default:
			}
		}
		private void downMove(Block newBlock){
			for (int y = 0; y < Grid_y; y++) {
				//하강 for문
				int x = newBlock.x;
				int r = newBlock.rotation;

				for (int h = 0; h < newBlock.height; h++) {
					for (int w = 0; w < newBlock.width; w++) {
						if (newBlock.shape[r][w][h] == 1) {
							jld[h + y][w + x].data = newBlock.shape[r][w][h];
						}
						if ((newBlock.shape[r][w][h] == 1) && (newBlock.shape[r][w][h] == jld[h + y + 1][w + x].data)) {
							flag = true;
						}

					}
				}
				if (flag)
					return;
				loadData();
			}
		}
	```
	- 좌우 및 회전 키 값 즉, 방향키 값을 받아와서 다음과 같은 함수들을 수행한다.
	- downMove는 수행함과 동시에 그 블럭이 내려갈 수 있는 가장 아래에 내려가도록 동작한다.
	- 또한 esc 버튼을 통해 게임을 종료시킨다.
6. 레이아웃 초기화 메소드
	```java
		private void setData0(){
			for (int y = 0; y < Grid_y; y++)
				for (int x = 0; x < Grid_x; x++) {
					jld[y][x].data = 0;
				}
			for (int i = 0; i < null_; i++)
				for (int j = 0; j < Grid_x; j++) {
					jld[Grid_y+i][j].data = 1;
				}
		}
		
	```
	 - 이전의 레이아웃 값이 무엇이든 초기화한다.
7. 한 줄 삭제 메소드
```java
	void lineCheck(){
			for (int y = Grid_y-1; y >= 0; y--) {
				for (int x = 0; x < Grid_x; x++) {
					if(jld[y][x].data == 1) {
						if (x == (Grid_x - 1)) {
							lineDel(y);
						}
					}
					else
						break;

				}
			}
		}
    void lineDel(int index){
        for (int y = index-1; y > 0; y--) {
            for (int x = 0; x < Grid_x; x++) {
                jld[y + 1][x].data = jld[y][x].data;
            }
        }
    }
```
	- 블럭의 한 동작이 끝날 때마다 이 메소드를 수행한다.
	- 가장 아래서부터 탐색을 수행하여 해당 라인의 모든 데이터값이 1일경우 그 줄을 없애기 위해
	- 그 윗 라인부터 덮어씌우기를 수행한다.

8. 게임을 수행하는 메소드 PlayRun
	```java
	private void PlayRun(){

			final Block[] newBlock = new Block[1];
			Runnable blockrun = new Runnable() {
				@Override
				public void run() {
					while(!endFlag) {
						newBlock[0] = createBlock();
						BlockRun(newBlock[0]);
						flag = false;
					}
				}
			};


			Thread t1 = new Thread(blockrun);

			t1.start();

			this.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					super.keyPressed(e);
					BlockMove(newBlock[0], e.getKeyCode());
					//System.out.println(e);
				}
			});

		}
	```
	- play run 메소드를 통해 블럭 생성과 블럭의 동작을 감지한다.
	- 블럭 생성과 아래로의 움직임을 수행함과 동시에 키의 입력에 따른 블럭의 이동이 필요하므로
	- BlockRun메소드를 쓰레드 위에 수행하여 멀티 태스킹이 가능하도록 만든다.

---

## 파일 저장과 가져오기
- 로드 및 세이브 메소드
	```java
	private void save() {
			String path = "C:\\homework\\Tetris.dat";
			ObjectOutputStream objOut = null;

			FileOutputStream fileOut = null;

			try{

				fileOut = new FileOutputStream(path);

				objOut = new ObjectOutputStream (fileOut);

				objOut.writeObject(jld_temp);
				if (objOut!=null){

					objOut.close();

				}

				else if (fileOut!=null){

					fileOut.close();

				}

			}catch(Exception e){


			}
		}
		private void load(){
			String path = "C:\\homework\\Tetris.dat";
			ObjectInputStream objInputStream = null;

			FileInputStream inputStream = null;

			try{

				inputStream = new FileInputStream(path);

				objInputStream = new ObjectInputStream (inputStream);



				jld = (JLabelData[][]) objInputStream.readObject();

				objInputStream.close();



			} catch (FileNotFoundException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();

			} catch (ClassNotFoundException e) {

				e.printStackTrace();

			}finally{

				if (objInputStream != null){

					try{objInputStream.close();}catch (Exception e){}

				}

				else if (inputStream != null){

					try{inputStream.close();}catch (Exception e){}

				}

			}
		}
	```
	- Load 및 Save메소드를 통해 현재 진행했던 게임 데이터를 저장하고
	- 이후 실행 했을 때 불러오기를 통해 저장했던 게임 데이터를 토대로 수행할 수 있다.
---
## 추가 기능
