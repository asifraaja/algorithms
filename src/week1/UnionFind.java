package week1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class UnionFind {
	boolean isConnected(int p, int q) {
		return false;
	}
	
	void union(int p, int q) {
		
	}
	
	int find(int p) {
		return 0;
	}
	
	int count() {
		return 0;
	}
	
	public static void main(String[] args) {
		try {
			String filename = System.getProperty("user.dir") + "/src/week1/union-find.txt";
			Scanner sn = new Scanner(new FileReader(filename));
			int N = sn.nextInt();
			QuickFindUF qf = new QuickFindUF(N);
			int O = sn.nextInt();
			qf.print();
			for(int i=0; i<O; i++) {
				int p = sn.nextInt();
				int q = sn.nextInt();
				
				qf.union(p, q);
				qf.print();
			}
			
			System.out.println(qf.find(0));
			System.out.println(qf.find(2));
			System.out.println(qf.find(4));
			System.out.println(qf.find(7));
			System.out.println(qf.find(9));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
