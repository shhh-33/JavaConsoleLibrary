package kr.co.mlec.lib.dao.src.kr.co.mlec.lib.ui;

import java.util.Scanner;

public abstract class BaseUI implements ILibUI {
	
	private Scanner sc;

	public BaseUI() {
		sc = new Scanner(System.in);
	}

	public String scanStr(String msg) {

		System.out.print(msg);
		String str = sc.nextLine();
		return str;
	}

	public int scanInt(String msg) {
		System.out.print(msg);
		int num = Integer.parseInt(sc.nextLine());
		return num;
	}

}
