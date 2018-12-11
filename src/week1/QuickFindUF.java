package week1;

import java.util.ArrayList;
import java.util.List;

public class QuickFindUF {
	int[] id = null;
	int[] sz = null;
	int[] wt = null;
	
	public QuickFindUF(int N) {
		id = new int[N];
		sz = new int[N];
		wt = new int[N];
		for(int i = 0; i<N; i++) {
			id[i] = i;
			sz[i] = 1;
			wt[i] = i;
		}
	}
	
	/**
	 * This approach is not efficient - very slow approach
	 * @param p
	 * @param q
	 * 
	 * Follows a list + array approach of N*2
	 */
	public void unionInefficient(int p, int q) {
		List<Integer> indexes = new ArrayList<>();
		for(int i=0; i<id.length; i++) {
			if(id[i] == q || id[i] == p)
				indexes.add(i);
		}
		if(indexes.size() == 0) {
			// not connected already to any other node
			id[p] = id[q];
		}else {
			// already connected to some other nodes
			for(int i=0; i<indexes.size(); i++) {
				id[indexes.get(i)] = id[q];
			}
		}
	}
	
	/**
	 * An efficient method - slow approach - 
	 * @param p
	 * @param q
	 * 
	 * Follows a N * 2 approach
	 */
	public void unionSlow(int p, int q) {
		int pid = id[p];
		int qid = id[q];
		for(int i=0; i<id.length; i++) {
			if(id[i] == pid)
				id[i] = qid;
		}
	}
	
	/**
	 * A better implementation - lazy approach
	 * @param p
	 * @param q
	 * Cons : finding the root can eventually take N lookups in case of flat trees
	 * 	- so the algorithm may eventually reach to N * 2 approach. 
	 */
	public void unionLazy(int p, int q) {
		int pid = root(p);
		id[q] = pid;
	}
	
	/**
	 * Best method to find the root
	 * 
	 * O(n + m log n)
	 * @param i
	 * @return
	 */
	public int root (int i) {
		while(i != id[i]) { 
			id[i] = id[id[i]];
			i = id[i];
		}
		return i;
	}
	
	/**
	 * This is O(n) approach.
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}
	
	public boolean isConnectedSlow(int p, int q) {
		return id[p] == id[q];
	}
	
	/**
	 * Using weighted method. Calculate weights and assign less weight tree as child of more weight tree
	 * @param p
	 * @param q
	 */
	public void union(int p, int q) {
		int weight = -1;
		int pid = root(p);
		int qid = root(q);
		if(pid == qid) return;
		if(pid > qid) weight = pid;
		else weight = qid;
		if(sz[pid] > sz[qid]) {
			id[qid] = pid;
			sz[pid] += sz[qid];
			wt[pid] = weight;
		}else {
			id[pid] = qid;
			sz[qid] += sz[pid];
			wt[qid] = weight;
		}
	}
	
	public int find(int i) {
		return wt[root(i)];
	}
	
	/**
	 * path compression
	 */
	
	public void print() {
		for(int i=0; i<id.length; i++) {
			System.out.print(id[i] + " ");
		}
		System.out.println();
	}
}
