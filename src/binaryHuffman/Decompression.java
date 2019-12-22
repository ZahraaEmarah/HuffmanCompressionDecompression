package binaryHuffman;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Decompression {

	StringBuilder string = new StringBuilder();
	HuffmanTree newtree = new HuffmanTree();
	ArrayList<Byte> chars = new ArrayList<Byte>();
	ArrayList<Byte> message = new ArrayList<Byte>();
	String msg_stream;
	String result = "";
	int counter = -1, tablecounter = 0, charcounter = 0;
	int table_size = 0;
	int[] tableBytes;
	int[] count_of_char;
	int bigcounter=0;

	public void DecodeTable(String filename) {

		try {

			System.out.println("*******************");
			String temp = "";
			byte[] content = Files.readAllBytes(Paths.get("compressedBinary.bin"));
			System.out.println(Arrays.toString(content));
			table_size = concat(content[0+bigcounter], content[1+bigcounter]);
			System.out.println("RECEIVING TABLE "+ table_size);
			count_of_char = new int[table_size / 4];
			int index = 0;

			for (int i = 2; i < table_size; i = i + 4) {
				chars.add(content[i]);
				byte[] freq = new byte[3];
				freq[0] = content[bigcounter+i+1];
				freq[1] = content[bigcounter+i+2];
				freq[2] = content[bigcounter+i+3];
				count_of_char[charcounter++] = new BigInteger(freq).intValue();
				index = i + 3;
			}

			newtree.setNumberOfChar(table_size / 4);
			newtree.buildHuffman(count_of_char, chars);
			StringBuilder msg_stream = new StringBuilder();
			StringBuilder string = new StringBuilder();
			for (int i = index + 1; i < content.length; i++) {

				temp = Integer.toBinaryString(content[i+bigcounter] & 0xFF);
				temp = String.format("%08d", Integer.parseInt(temp));
				string.append(temp);
				msg_stream.append(string.reverse());
				string.delete(0, string.length());
			}

			byte[] data = translate(msg_stream, msg_stream.length());
			File myFile = new File(filename);
			OutputStream out = new FileOutputStream(myFile);
			try {
				out.write(data); // Just dump the database content to disk
			} finally {
				out.close();
			}
			System.out.println("file written successfully");

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	int resultcounter = 0;
	ArrayList<Byte> bo = new ArrayList<Byte>();
	

	public byte[] translate(StringBuilder stream, int length) {
		String found = "";
		for (int i = 0; i < length; i++) {
			found = found + stream.charAt(i);
			for (int j = 0; j < newtree.Huffman_codes.length - 1; j++) {
				if (found.equals(newtree.Huffman_codes[j]) && j % 2 != 0) {
					bo.add((byte) Integer.parseInt(newtree.Huffman_codes[j - 1]));
					found = "";
				}
			}
		}
        
		byte[] b = new byte[bo.size()];
		
		for(int i=0; i< bo.size(); i++)
		{
			b[i] = bo.get(i);
		}

		return b;
}

	public int concat(int high, int low) {
		String highh = Integer.toBinaryString(high & 0xFF);
		String loww = Integer.toBinaryString(low & 0xFF);

		highh = String.format("%08d", Integer.parseInt(highh));
		loww = String.format("%08d", Integer.parseInt(loww));

		String result = highh + loww;
		int r = Integer.parseInt(result, 2);
		return r;
	}
}
