package bg.swift.HW12_Ex01;

import java.io.File;
import java.util.Scanner;

public class Task1_ListFileStructure {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		File f = new File(sc.nextLine());
		printListFileStructure(f);
		sc.close();
	}

	private static void printListFileStructure(File file) {
		File[] files = file.listFiles();
		if (files == null) {
			return;
		}
		printInfo(files);
		for (File a : files) {
			printListFileStructure(a);
		}
	}

	private static void printInfo(File[] files) {
		for (File a : files) {
			System.out.println(a);
		}
	}
}
