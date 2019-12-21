package binaryHuffman;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class BinaryDecompression {

	StringBuilder string = new StringBuilder();
	BinaryHuffmanTree newtree = new BinaryHuffmanTree();
	ArrayList<Byte> chars = new ArrayList<Byte>();
	ArrayList<Byte> message = new ArrayList<Byte>();
	String msg_stream;
	String result = "";
	int counter = -1, tablecounter = 0, charcounter = 0;
	int table_size = 0;
	int[] tableBytes;
	int[] count_of_char;

	public void DecodeTable(String filename) {

		try {

			String temp = "";
			byte[] content = Files.readAllBytes(Paths.get("compressedBinary.bin"));
			table_size = concat(content[0], content[1]);
			count_of_char = new int[table_size / 3];
			int index = 0;

			for (int i = 2; i < table_size; i = i + 3) {
				chars.add(content[i]);
				count_of_char[charcounter++] = concat(content[i + 1], content[i + 2]);
				index = i + 2;
			}

			newtree.setNumberOfChar(table_size / 3);
			newtree.buildHuffman(count_of_char, chars);
			String msg_stream = "";
			StringBuilder string = new StringBuilder();
			for (int i = index + 1; i < content.length; i++) {

				temp = Integer.toBinaryString(content[i] & 0xFF);
				temp = String.format("%08d", Integer.parseInt(temp));
				string.append(temp);
				msg_stream = msg_stream + string.reverse();
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
	

	public byte[] translate(String stream, int length) {
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
