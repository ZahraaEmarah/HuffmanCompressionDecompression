package huffman;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.BitSet;

public class Compression {
	
	public void Clear_output_file() {
		try {
			String str = "";
			FileWriter fw = new FileWriter("compressed.txt");
			fw.write(str);
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void WriteToFile(String line) {

		try {

			File file = new File("compressed.txt");
			FileWriter fr = new FileWriter(file, true);
			BufferedWriter br = new BufferedWriter(fr);
			br.write(line);

			br.close();
			fr.close();
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("File not found");
		}
	}

	public int Str_Compress(String line) {

		int compressed_size =0;

		try {
			BitSet buffer = new BitSet();
			int bitIndex = 0;
			FileOutputStream fos = new FileOutputStream(new File("compressed.txt"), true);
			StringBuilder s = new StringBuilder();

			for (int i = 0; i < line.length(); i++) {
				if (line.charAt(i) == '1') {
					buffer.set(bitIndex);
					s.append("1");
					compressed_size++;
				} else {
					buffer.clear(bitIndex);
					s.append("0");
					compressed_size++;
				}
				bitIndex++;
			}
			fos.write(buffer.toByteArray());
			System.out.println(s);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return compressed_size;
	}

	public void Str_Compress_char(String character) {
		FileOutputStream fos;
		try {
			
			fos = new FileOutputStream(new File("compressed.txt"), true);
	        byte[] b = character.getBytes(Charset.forName("UTF-8"));
	        System.out.println("byte byte " + b);
			fos.write(b);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
