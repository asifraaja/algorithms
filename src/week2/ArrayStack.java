package week2;

public class ArrayStack {
	String[] stack = null;
	int pointer = 0;
	
	public ArrayStack(int capacity) {
		stack = new String[capacity];
	}
	
	public void push(String str) {
		stack[pointer++] = str;
	}
	
	public String pop() {
		String data = stack[--pointer];
		stack[pointer] = null;
		return data;
	}
	
	public boolean isEmpty() {
		return pointer == -1 ? true : false;
	}
	
	public String toString() {
		for(int i=0; i<stack.length; i++) {
			System.out.print(stack[i] + "\t");
		}
		return "\n";
	}
	
	public static void main(String[] args) {
		ArrayStack stack = new ArrayStack(10);
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
