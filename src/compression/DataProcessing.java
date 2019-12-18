package compression;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import decompression.Decompression;
import huffman.huffmanTree;
import java.util.ArrayList;
import java.util.BitSet;

public class DataProcessing {

	char[] charArray;
	ArrayList<Character> chars = new ArrayList<Character>();
	public huffmanTree tree = new huffmanTree();
	Compression d = new Compression();
	Decompression read = new Decompression();
	int original_size = 0;
	int compress_size = 0;
	File file;

	public void Read_data(String filename) {

		String data = "";

		d.Clear_output_file();

		try {
			
			file = new File(filename);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String currentLine;
			
			while ((currentLine = br.readLine()) != null) {
				System.out.println(data);
				data = data + currentLine ;
			}
			
			br.close();
		} catch (Exception e) {
			System.out.println("File Not Found");
		}

		charArray = data.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if (!(chars.contains(charArray[i]))) {
				chars.add(charArray[i]);
			}
		}

		tree.setNumberOfChar(chars.size());

		int[] countOfChar = new int[chars.size()];
		for (int x = 0; x < countOfChar.length; x++) {
			countOfChar[x] = 0;
		}

		for (int i = 0; i < chars.size(); i++) {
			char checker = chars.get(i);
			for (int x = 0; x < charArray.length; x++) {
				if (checker == charArray[x]) {
					countOfChar[i]++;
				}
			}
		}

		for (int i = 0; i < countOfChar.length - 1; i++) {
			for (int j = 0; j < countOfChar.length - 1; j++) {
				if (countOfChar[j] < countOfChar[j + 1]) {
					int temp = countOfChar[j];
					countOfChar[j] = countOfChar[j + 1];
					countOfChar[j + 1] = temp;

					char tempChar = chars.get(j);
					chars.set(j, chars.get(j + 1));
					chars.set(j + 1, tempChar);
				}
			}

		}

		for (int x = 0; x < countOfChar.length; x++) {
			System.out.println(chars.get(x) + " - " + countOfChar[x]);
			original_size += countOfChar[x];
		}

		tree.buildHuffman(countOfChar, chars);
		d.WriteTableToFile(chars, countOfChar);
		d.Read_original_msg(data, tree.Huffman_codes); /// put msg in file
		System.out.println("ORIGINAL SIZE IS " + (original_size * 8));
	}

	public float ratio() {
		
		float original = file.length();
		float compressed = d.file1.length();
		float ratio = original / compressed;		
		return ratio;
	}
}
