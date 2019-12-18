package compression;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.BitSet;

public class Compression {

	FileOutputStream fos, fos1;
	BitSet buffer = new BitSet();
	int bitIndex = 0;
    File file1;

	public void Clear_output_file() {
		try {
			String str = "";
			file1 = new File("compressed.bin");
			FileWriter fw = new FileWriter(file1);
			fw.write(str);
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Read_original_msg(String data, String[] table) {
		char[] charArray = data.toCharArray();
		for (int i = 0; i < data.length(); i++) {
			for (int j = 0; j < table.length - 1; j = j + 2) {
				if (charArray[i] == table[j].charAt(0)) {
					//System.out.println(charArray[i] + " code is " + table[j + 1]);
					Add_to_buffer(table[j + 1]);
				}
			}
		}

		try {

			fos1 = new FileOutputStream(new File("compressed.bin"), true);
			fos1.write(buffer.toByteArray());
			fos1.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void Add_to_buffer(String binary) {
		for (int i = 0; i < binary.length(); i++) {
			if (binary.charAt(i) == '1')
				buffer.set(bitIndex);
			else if (binary.charAt(i) == '0')
				buffer.clear(bitIndex);
			bitIndex ++;
		}
	}

	public void WriteTableToFile(ArrayList<Character> chars, int[] numberOfChar) {
		try {

			Byte extrabyte = 0;
			fos = new FileOutputStream(new File("compressed.bin"), false);
            fos.write(chars.size()*2);
			
			for (int i = 0; i < chars.size(); i++) {

				int ascii = (int) chars.get(i);
				fos.write(ascii);
				// fos.write(extrabyte);
				fos.write(numberOfChar[i]);
			}

			fos.close();
		} catch (Exception e) {

			System.out.println("Compression File not found");
		}
	}

}
