package huffman;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

import javax.swing.JTextArea;

import compression.Compression;



public class huffmanTree {
	 
	private int numberOfChar;
	PriorityQueue<node> queue ;
	Compression c = new Compression();
	public String[] Huffman_codes; 
	
	int counter =0;
	
	//this class is used to implement huffman tree with its nodes using min heap as priority queue
	
	class compare implements Comparator<node> {  //compares between the frequencies of both x and y
	    public int compare(node x, node y) 
	    { 
	  
	        return x.freq - y.freq;  //to add it to the priority queue
	    } 
	}
	
	public void buildHuffman(int[] countOfChar , ArrayList<Character> chars ){
		Huffman_codes = new String[numberOfChar*2+1]; //size 58
		System.out.println("NOO "+numberOfChar*2);
		 queue  = new PriorityQueue<node>(numberOfChar, new compare()); //make the min heap as priority queue
		 //build the min heap --QUEUE--
		 node root = null;
		 for (int i = 0; i < numberOfChar; i++) { 
			  
	            // creating a Huffman node object 
	            // and add it to the priority queue. 
	            node nodeH = new node(); 
	  
	            nodeH.character = chars.get(i); 
	           
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
			 node first = queue.peek();
			 queue.poll();
			 
			 node second = queue.peek();
			 queue.poll();
			 
			 //new node 
			 node newN = new node();
			 
			 newN.character = '-';
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
	public  void printCode(node root, String s) 
	{  
		
		if (root.left == null && root.right == null ) 
		{ 
			if(i < numberOfChar*2+1) {
			// c is the character in the node 
			Huffman_codes[i] = Character.toString(root.character);
			System.out.println("new " + Huffman_codes[i]);
			i++;
			Huffman_codes[i] = s;
			System.out.println("new " +Huffman_codes[i]);
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
			int ascii = (int) Huffman_codes[i].charAt(0);
			line = line +" "+ ascii + "\t" +  "0" + Integer.toBinaryString(ascii)  +"\t" + code +"\n";

		}
		return line;
	}
	/**
	public int table_size()
	{
		int table_size =0;
		table_size = Huffman_table.size();
		for(int i=0; i < Huffman_table.size(); i++)
		{
			String str = Huffman_table.get(i).toString();
			String arrOfStr[] = str.split(":", 2); 
		
			table_size += arrOfStr[1].length();
		}
		
		return table_size;
	}**/

}
