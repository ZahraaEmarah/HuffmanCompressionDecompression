package decompression;

import java.io.*;
import java.util.ArrayList;

import huffman.huffmanTree;

public class Decompression {

	StringBuilder string = new StringBuilder();
	huffmanTree newtree = new huffmanTree();
	ArrayList<Character> chars = new ArrayList<Character>();
	String msg_stream = "";
	String result = "";
	int counter = -1, tablecounter=0, charcounter = 0;
	int table_size = 0;
	int[] tableAscii;
	int[] count_of_char;

	public void Read_file() {
		try (InputStream inputStream = new FileInputStream("compressed.bin");) {
			String temp;
			BufferedWriter writer = new BufferedWriter(new FileWriter("decompressed.txt"));
			int byteRead;
			while ((byteRead = inputStream.read()) != -1) {

				if (counter == -1) {

					table_size = byteRead;
					tableAscii = new int[table_size/2];
					count_of_char = new int[table_size/2];
					counter++;

				} else if (counter < table_size && counter != -1) {
					
					if(counter%2 == 0)
					{
						tableAscii[tablecounter++] = byteRead;
						counter++;
					}else
					{
						count_of_char[charcounter++] = byteRead;
						counter++;
					}
				}
				else {

					temp = Integer.toBinaryString(byteRead);
					temp = String.format("%08d", Integer.parseInt(temp));
					System.out.println("Incoming");
					System.out.println(byteRead);
					string.append(temp);
					msg_stream = msg_stream + string.reverse();
					string.delete(0, string.length());
				}
			}	
			
			for(int i=0; i< tableAscii.length;i++)
			{
				chars.add((char)tableAscii[i]);
			}
			
			for(int i=0; i<table_size/2; i++)
			{
				System.out.println("received char " + chars.get(i));
				System.out.println("received freq " + count_of_char[i]);
			}
			
			newtree.setNumberOfChar(count_of_char.length);
			newtree.buildHuffman(count_of_char, chars);
			writer.write(translate(msg_stream, msg_stream.length()));
			writer.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public String translate(String stream, int length)
	{
		String found = "";
		for(int i=0; i< length;i++)
		{
			found = found + stream.charAt(i);
			for(int j=0; j< newtree.Huffman_codes.length-1; j++)
			{
				if(found.equals(newtree.Huffman_codes[j]))
				{
					result = result + newtree.Huffman_codes[j-1];
					found = "";
				}
			}
		}
		
		return result;
	}

}
