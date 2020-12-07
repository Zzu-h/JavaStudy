package project.additional;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FactorialArray {
	// 한 자리씩 계산한다.
	private byte biginteger[] = new byte[213238];
	private int size = 0;

	public FactorialArray() {
		// TODO Auto-generated constructor stub
		biginteger[0] = 1;// factorial 기본값 1 초기화
		size = 1;
	}

	public void Factorial(int n) {

		for (int i = 1; i <= n; i++)
			this.calFact(i);
		
	}

	private void calFact(int n) {
		
		int carry = 0;
		for (int i = 0; i < size; i++) {
			int cal = biginteger[i] * n + carry;
			biginteger[i] = (byte) (cal % 10);
			carry = cal / 10;
		}

		// 최상위 자리연산 후 자리올림 변수가 더 남았을 경우
		while (carry != 0) {
			biginteger[size] = (byte) (carry % 10);
			carry /= 10;
			size++;
		}
		
	}

	public void printFact() {
		for (int i = size - 1; i >= 0; i--)
			System.out.print(biginteger[i]);
	}

	public void mkFile() {

		String path = "C:\\homework\\factorial.txt";
		try {
			OutputStream output = new FileOutputStream(path, false);

			for (int i = size - 1; i >= 0; i--) {
				output.write(biginteger[i]+'0');
			}

			output.close();
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	public static void main(String args[]) {
		FactorialArray f = new FactorialArray();
		f.Factorial(50000);
		f.printFact();
		f.mkFile();
	}

}
