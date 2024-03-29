package binaryHuffman;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class HuffmanTree {
	
	private int numberOfChar;
	PriorityQueue<Node> queue ;
	public String[] Huffman_codes; 
	
	int counter =0;
	
	//this class is used to implement huffman tree with its nodes using min heap as priority queue
	
	class compare implements Comparator<Node> {  //compares between the frequencies of both x and y
	    public int compare(Node x, Node y) 
	    { 
	  
	        return (x.freq - y.freq);  //to add it to the priority queue
	    } 
	}
	
	public void buildHuffman(int[] countOfChar , ArrayList<Byte> chars ){
		Huffman_codes = new String[numberOfChar*2+1]; //size 58
		 queue  = new PriorityQueue<Node>(numberOfChar, new compare()); //make the min heap as priority queue
		 //build the min heap --QUEUE--
		 Node root = null;
		 for (int i = 0; i < numberOfChar; i++) { 
			  
	            // creating a Huffman node object 
	            // and add it to the priority queue. 
			 Node nodeH = new Node(); 
	  
	            nodeH.byt = chars.get(i); 
	           
	            nodeH.freq = countOfChar[i]; 
	  
	            nodeH.left = null; 
	            nodeH.right = null; 
	  
	            // add functions adds 
	            // the huffman node to the queue. 
	            queue.add(nodeH); 
	        } 
		 //create the tree by extracting the minimum value of the heap each time till the heap is empty 
		 while(queue.size()>1)
		 {
			 //extract the 2 minimum nodes from the queue
			 //and remove them from the queue 
			 Node first = queue.peek();
			 queue.poll();
			 
			 Node second = queue.peek();
			 queue.poll();
			 
			 //new node 
			 Node newN = new Node();
			 
			 newN.byt = '-';
			 newN.freq = first.freq + second.freq ;
			 newN.left = first;
			 newN.right = second;
			 root = newN; //left root start from the new node we created
			 queue.add(newN); //add the new node to the queue
			 
		 }
		 //print the code of each character 
		 printCode(root,"");
	}
	
	
	int i=0;
	public  void printCode(Node root, String s) 
	{  
		
		if (root.left == null && root.right == null ) 
		{ 
			if(i < numberOfChar*2+1) {
			// c is the character in the node 
			Huffman_codes[i] = Byte.toString(root.byt);
			System.out.print(Huffman_codes[i] + " ");
			i++;
			Huffman_codes[i] = s;
			System.out.println(Huffman_codes[i] + " ");
			i++;
			}
			return; 
		} 
		
		printCode(root.left, s + "0"); 
		printCode(root.right, s + "1"); 
		
	} 

	public int getNumberOfChar() {
		return numberOfChar;
	}

	public void setNumberOfChar(int numberOfChar) {
		this.numberOfChar = numberOfChar;
	} 

	public String print_table() /// for writing the table in the textArea
	{
		String line ="";
	
		for(int i=0; i < Huffman_codes.length-1; i = i+2)
		{
			String character = Huffman_codes[i];
			String code = Huffman_codes[i+1];
			line = line +" "+ character + "\t"  + Integer.toBinaryString(Integer.parseInt(Huffman_codes[i]) & 0xFF)  +"\t" + code +"\n";

		}
		return line;
	}

}
