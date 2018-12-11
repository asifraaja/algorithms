package week1.assignment;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import week1.QuickFindUF;

public class Percolation {
	int size;
	int numOfSites = 0;
	boolean[] grid = null;
	WeightedQuickUnionUF uf = null;
//	QuickFindUF uf = null;
	
	public Percolation(int n) {               // create n-by-n grid, with all sites blocked
		size = n;
		grid = new boolean[size * size + 2];
		uf = new WeightedQuickUnionUF(size * size + 2);
//		uf = new QuickFindUF(size * size + 2);
	}
	
	public void open(int row, int col) {   // open site (row, col) if it is not open already
		int index = computeIndex(row, col);
		System.out.println("Index : " + index);
		if(!grid[index]) {
			grid[index] = true;
			numOfSites ++;
		}
		
		if(row == 1) 
			connect(index, 0);
		if(row == this.size) 
			connect(index, this.size * this.size + 1);
		if(row < this.size && isOpen(row + 1, col)) 
			connect(index, computeIndex(row + 1, col));
		if(row >  1 && isOpen(row - 1, col))
			connect(index, computeIndex(row - 1, col));
		if(col < this.size && isOpen(row, col + 1))
			connect(index, computeIndex(row, col + 1));
		if(col > 1 && isOpen(row, col - 1))
			connect(index, computeIndex(row, col - 1));
		print();
	}
	
	public boolean isOpen(int row, int col) {  // is site (row, col) open?
		if(!isIndicesInvalid(row, col))
			throw new IndexOutOfBoundsException("row index i out of bounds");
		return grid[computeIndex(row, col)];
	}
	
	public boolean isFull(int row, int col) {  // is site (row, col) full?
		if (isIndicesInvalid(row, col)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        int index = computeIndex(row, col);
        return uf.connected(index, 0);
	}
	
	public int numberOfOpenSites() {       // number of open sites
		return numOfSites;
	}
	
	public boolean percolates()  {            // does the system percolate?
		return uf.connected(0, this.size * this.size + 1);
	}
	
	public int computeIndex(int row, int col) {
		if(!isIndicesInvalid(row, col))
			throw new IndexOutOfBoundsException("row index i out of bounds");
		return (row - 1) * this.size + col;
	}
	
	private boolean isIndicesInvalid(int i, int j) {
        return (i >= 1 || i <= this.size || j >= 1 || j <= this.size);
    }
	
	private void connect(int i, int j) {
		uf.union(i, j);
	}
	
	public void print() {
		System.out.println("System starts");
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				System.out.print(grid[i*size + j + 1] + "\t");
			}
			System.out.println();
		}
//		uf.print();
		System.out.println("System ends");
	}
	
	public static void main(String[] args) {   // test client (optional)
		
	}
}
