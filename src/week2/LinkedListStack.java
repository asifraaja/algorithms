package week2;

public class LinkedListStack {
	Node root = null;
	
	private class Node{
		String data;
		Node next;
	}
	
	public void push(String str) {
		Node newNode = new Node();
		newNode.data = str;
		newNode.next = root;
		root = newNode;
	}
	
	public String pop() {
		String data = root.data;
		root = root.next;
		return data;
	}
	
	public boolean isEmpty() {
		if(root == null) return true;
		return false;
	}
	
	public String toString() {
		Node head = new Node();
		head = root;
		while(head != null) {
			System.out.print(head.data + "\t");
			head = head.next;
		}
		return "\n";
	}
	
	public static void main(String[] args) {
		LinkedListStack stack = new LinkedListStack();
		String operation = "i am adding a - new - - data ";
		String[] operations = operation.split(" ");
		for(int i=0; i<operations.length; i++) {
			if(operations[i].equalsIgnoreCase("-"))
				stack.pop();
			else
				stack.push(operations[i]);
			System.out.println(stack.toString());
		}
	}
}
