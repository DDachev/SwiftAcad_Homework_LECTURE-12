package bg.swift.HW12_Ex02;

import java.io.File;
import java.util.Scanner;

public class Task2_ListRelativeFileStructure {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		File f = new File(sc.nextLine());
		printListFileStructure(f);
		sc.close();
	}
	
	private static void printListFileStructure(File file) {
		String[] files = file.list();
		if (files == null) {
			return;
		}
		printInfo(files);
		for(File a : file.listFiles()) {
			printListFileStructure(a);
		}
	}

	private static void printInfo(String[] files) {
		for (String a : files) {
			System.out.println(a);
		}
	}

}
