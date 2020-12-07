package project.additional;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;

public class FactorialLinkedLIst {

	protected LinkedList<Short> saveFact = new LinkedList<Short>();

	public FactorialLinkedLIst() {
		saveFact.add((short) 1);
	}

	public void calFact(char num) {
		int temp1 = 0; // 연산 결과 임시 저장 변수1
		int temp2 = 0; // 연산 결과 임시 저장 변수2
		int size = saveFact.size();

		LinkedList<Short> tempFact = new LinkedList<Short>();

		try {
			for (int i = 0; i < size; i++) {
				temp1 = saveFact.remove() * num; // 맨 뒷자리부터 4자리씩 연산을 수행함
				temp1 += temp2; // 연산 후 뒤에서 올라온 자리올림을 더해줌
				if (temp1 > 9999) {
					// 4자리를 벗어날 경우 자리올림을 수행함
					temp2 = temp1 / 10000; // 다음의 자리올림할 수 저장
					temp1 %= 10000; // 연산 후 4자리 저장
				} else
					temp2 = 0;
				tempFact.add((short) temp1);
			}

			// 연산이 끝난 후 saveFact는 비어있다.
			while (tempFact.size() != 0)
				saveFact.add(tempFact.remove());

			if (temp2 > 0)
				saveFact.add((short) temp2);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	public String printFact() {

		String str = "";
		try {
			while (saveFact.size() != 1) {
				short temp = saveFact.remove();
				if (temp == 0)
					str = "0000" + str;
				else if (temp < 10)
					str = "000" + temp + str;
				else if (temp < 100)
					str = "00" + temp + str;
				else if (temp < 1000)
					str = "0" + temp + str;
				else
					str = temp + str;
			}
			str = saveFact.remove() + str;
			System.out.println(str);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		return str;
	}

	public String convertStr(short temp) {
		String str = "";
		if (temp == 0)
			str = "0000" + str;
		else if (temp < 10)
			str = "000" + temp + str;
		else if (temp < 100)
			str = "00" + temp + str;
		else if (temp < 1000)
			str = "0" + temp + str;
		else
			str = temp + str;
		return str;
	}

	public void mkFile() {
		
		String path = "C:\\homework\\factorial.txt";
		try {
			OutputStream output = new FileOutputStream(path, false);

			byte[] by = Short.toString(saveFact.removeLast()).getBytes();
			output.write(by);
			while (saveFact.size() != 0) {
				by = this.convertStr(saveFact.removeLast()).getBytes();
				output.write(by);
			}
			
			output.close();
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	public void print() {
		System.out.println("링크드 리스트 사이즈: "+saveFact.size());
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		FactorialLinkedLIst F = new FactorialLinkedLIst();
		int num = 15000;

		for (char i = 1; i <= num; i++) {
			F.calFact(i);
		}
		/*
		for (char i = 1; i <= 10000; i++) {
			F.calFact(i);
		}
		F.print();
		for (char i = 10001; i <= 20000; i++) {
			F.calFact(i);
		}
		F.print();
		for (char i = 20001; i <= 30000; i++) {
			F.calFact(i);
		}
		F.print();
		for (char i = 30001; i <= 40000; i++) {
			F.calFact(i);
		}
		F.print();
		for (char i = 40001; i <= 50000; i++) {
			F.calFact(i);
		}
		F.print();
		*/
		F.mkFile();

	}

}
