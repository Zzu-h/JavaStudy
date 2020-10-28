# Java 기본 문법
프로그램의 작성과 실행
 1. 프로젝트 생성
 2. 자바 프로그램 코딩
 3. 컴파일(바이트 코드 생성)
    * javac라는 명령어를 통해 컴파일을 해야하나
    * eclipse에서는 버튼으로 가능
 4. 실행

 ### 프로그램 구조
 자바 프로그램은 다음과 같이 이루어져 있다.
 * 소스 파일
    * 클래스
        * 메서드
            * 실행문
* 각 명칭의 정의
    * 클래스: 객체 지향 언어에스 프로그램을 개발하는 단위
    * 메서드: 수행할 작업을 나열한 코드의 모임
    * 실행문: 작업을 지시하는 변수 선언, 값 저장, 메서드 호출 등의 코드
***
## 자바 규칙
### 식별자
* 규칙: 반드시 지켜야 함
    * **문자**, **언더바**, $로시작해야한다.
    * 한글도 가능하다.(비추천)
    * 영문자는 대소문자를구분해야 한다. (대문자는비추천)
    * +, - 등 연산자를포함하면 안된다.
    * 길이에 제한이 없다.
* 관례: 반드시 지킬 필요는없지만 다수가 사용하는 규칙
    * `변수`와 `메서드`는 **모두 소문자**로 표기한다.
        * ex) public int su(){}
        * 복합 단어일 때는 두번째   단어부터 단어의 **첫 자만**   대문자로 표기한다.
            * ex) boolean is Empty;
    * `클래스`와 `인터페이스`는 **첫 자만 대문자**로표기하고 나머지는소문자로 표기한다.
        * ex) public classSource1{}
        * 복합 단어일 때는 첫번째   단어와 두 번째 단어**첫   자만** 대문자로표기한다.
            * public class HelloDemo {}

    * 상수는 전체를 대문자로 표기한다. (const 되어진 아이들을 말함)
        * const로 고정하려면, final이라는 keyword를 써준다.
        * ex) final double PI = 3.141592;
        * 단 복합 단어일 때는 단어를 언더바로 연결한다.
            * ex) final int NUMBER_ONE = 1;
***
### 데이터 타입
자료형 기초타입: 기본적으로 c++과 같다.    
    c++에서 bool은 자바에서 boolean이다.    
* 자바는 c++과 다르게 기본 2바이트로 저장 가능하다.
    * char 하나당 한 글자를 저장할 수 있다.
<br>

자료형 참조타입: 자바에서는 포인터가 없다. 포인터역할을 하는 자료형
### 변수
변수는 선언과 동시에 초기화를 해줘야 한다.    
class의 필드(멤버변수)는 **기본적으로 0**의 데이터를 가진다.
* 리터럴: 변하지 않는 데이터
* 정수는 c++과 유사
* 실수
    * double형은 소수점이 있으면 표기를 안해줘도 되지만
    * float형은 F로 표기해주어야 한다.
        * float pi = 3.14159F;
    * 5E-1 = 5*10^(-1)을 의미
* 문자: c++과 동일
    * char c = '\u0041'은 유니코드 값으로 대입
* 논리: 0과 1이 아닌 true / false로 초기화를 해준다.
***
## 타입 변환
c++과 유사  
* 자동 형변환 
    * 데이터 타입의 저장공간이 작은 곳에서 큰 곳으로 할당할 때 자동 형 변환이 일어난다.
    ```java
    double a = 5 * 3.14;
    ```
    * 5는 integer 이나 더 큰 데이터 타입으로 자동 형변환 되어서 할당되었다.
* 강제 형 변환
    * 데이터 타입의 저장공간이 큰 곳에서 작은 곳으로 할당해야 할 때 강제로 형변환을 해 주어야 한다.
    * c언어의 강제 형변환과 같음
    ```java
    double d = (double)3.14f;
    ```
참고
> 실수형보다 정수형을 데이터 크기가 더 크다고 인지한다.
#### 오버플로우: 어셈블리어할 때 오버플로우 함
***
## 기본 입출력
* 데이터 입력
    * `System.in`
        * next(): ***한 단어***를 읽고 String으로 반환한다.
        * nextInt(): ***정수***를 입력받고
         int로 반환한다.
        * nextLine(): ***한 줄***을 읽고 String으로 반환한다.
    * import java.util.Scanner; 해서 Scanner 클래스로 입력받아야 한다.
        * Scanner in = new Scanner(System.in);
    ```java
    Scanner in = new Scanner(System.in);
    int x = in.nextInt();
    ```
    * 정수를 입력받고 변수 x에 대입한다.

    ```c++
    int x;
    std::cin>>x;
    ```
* 데이터 출력
    * `System.out`
        * println(): 내부 내용 출력 후 행 바꿈
        * print(): 내부의 내용을 출력하고 행을 바꾸지 않는다.
        * printf(): 포맷을 지정해서 출력한다.
    ```java
    System.out.println("Test 입니다" + "test");
    System.out.print("Test 입니다" + "test");
    int i = 97;
    String s = "Java";
    double f = 3.14f;
    System.out.printf("%d\n", i);
    System.out.printf("%s\n", s);
    System.out.printf("%4.1f\n", f);
    ```
* System은 클래스
    * out은 변수명
        * print들은 메서드이다.
***
## 연산자
자바 가상 머신은 기본적으로 32비트 단위로 계산한다.
* 산술 연산자
    * 나머지 % 연산자는 정수 타입만 사용 가능하다
    * int a = n%2;
* 비교 연산자
* 논리 연산자
* 비트 시프트 연산자
    * 비트 연산자
        * 정수 타입에만 사용
        * &: and 연산
        * |: or 연산
        * ^: xor 연산
        * ~: not 연산
    * 시프트 연산자 (a ㅁ b)
        * 산술적 시프트
            * `<<`: a의 모든 비트를     b비트만큼 왼쪽으로  이동한다.  
                * 빈 공간을 0으로 채움
            * `>>`: a의 모든 비트를     b비트 만큼 오른쪽으로   이동한다. 
                * rotation shift이다.
        * 논리적 시프트
            * `>>>`: a의 모든 비트를     b비트만큼 오른쪽으로  이동한다.  
                * 빈 공간을 0으로 채움
* 조건 연산자
    * 조건식 ? 연산식1 : 연산식2
    * 조건식이 true : false
***
## 조건문
c++과 동일
* if문
* switch문
## 반복문
* while문
* do-while문
    ```java
    import java.util.Scanner;
    public class Source1{
        public static void main(String[] args){
            Scanner in = new Scanner(System.in);
            int count = in.nextInt();
            do{
                System.out.print(count + " ");
            }while(count != 0)
        }
    }
    ```
    * count가 0일 때 일반 while문은 돌지 않고 끝나지만
    * do-while문은 한번 돌고 비교하므로 최소 한번은 반복한다.
* for문
    * [for(auto a : 인스턴스)](./배열과-컬렉션.md/#배열)
        * auto 키워드: 대입되는 값에 따라 자동으로 데이터 타입이 지정된다.
        * (auto a : 인스턴스)는 a에 인스턴스의 값을 저장하고 인스턴스의 모든 요소를 다 돌때 for문을 종료한다.
* break / continue
***
## 메서드 구조
선언문: 접근 지정자 static 반환타입 메서드명 (매개변수){
    본체
}    
ex) public static int main(String[] args){}
* 메서드의 매개변수
    * call by value
        ```
        int sum(int num){
            return num
        }
        ```
    * call by reference
        ```
        int arrSum(int &arr){
            return arr[0];
        }
        ```
* UI메서드
    ```java
    import javax.swing.JOptionPane;

    String s ="kor";
    boolean a = s.equals("kor"); //string은 ==로 가 아닌 equals 함수로 같은지 비교한다.
    s = JOptionPane.showInputDialog("제목"); //UI 제공 메소드
    int num = Integer.parseInt(s) //string 내용을 int로 바꿈
    ```
* Time delay 메서드
    ```java
    Long start = System.nanoTime();
    Long end = System.nanoTime();
	Long dur = end - start;
    ```
