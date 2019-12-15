package huffman;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

import javax.swing.JTextArea;



public class huffmanTree {
	 
	private int numberOfChar;
	PriorityQueue<node> queue ;
	Compression c = new Compression();
	ArrayList<String> Huffman_table = new ArrayList<String>();
	
	//this class is used to implement huffman tree with its nodes using min heap as priority queue
	
	class compare implements Comparator<node> {  //compares between the frequencies of both x and y
	    public int compare(node x, node y) 
	    { 
	  
	        return x.freq - y.freq;  //to add it to the priority queue
	    } 
	}
	
	public void buildHuffman(int[] countOfChar , ArrayList<Character> chars ){
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
			// c is the character in the node 
			c.WriteToFile(root.character + ":" );
			//c.Str_Compress_char(Character.toString(root.character));
			c.Str_Compress(s);
			c.WriteToFile(" ");
			Huffman_table.add(root.character+ ":" + s);
	        System.out.println(Huffman_table.get(i++).toString());
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
		for(int i=0; i < Huffman_table.size(); i++)
		{
			String str = Huffman_table.get(i).toString();
			String arrOfStr[] = str.split(":", 2); 
			int ascii = (int) arrOfStr[0].charAt(0);
			line = line +" "+ ascii + "\t" +  "0" + Integer.toBinaryString(ascii)  +"\t" + arrOfStr[1] +"\n";

		}
		return line;
	}
	
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
	}

}
