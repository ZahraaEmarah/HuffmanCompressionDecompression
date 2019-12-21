package binaryHuffman;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.BitSet;

public class BinaryCompression {

	public BinaryHuffmanTree bintree;
	FileOutputStream fos, fos1;
	BitSet buffer = new BitSet();
	int bitIndex = 0;
	File file1, file;
	Byte[] data;

	public void binC(String filename) {

		try {

			byte[] b = Files.readAllBytes(Paths.get(filename));
			file = new File(filename);
			data = new Byte[b.length];

			for (int i = 0; i < b.length; i++) {
				data[i] = Byte.valueOf(b[i]);
			}

			GenerateTable(data);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void GenerateTable(Byte[] data) {
		ArrayList<Byte> bytes = new ArrayList<Byte>();
		bintree = new BinaryHuffmanTree();

		for (int i = 0; i < data.length; i++) { /// number of distinct bytes
			if (!(bytes.contains(data[i]))) {
				bytes.add(data[i]);
			}
		}

		bintree.setNumberOfChar(bytes.size());

		int[] countOfChar = new int[bytes.size()];
		for (int x = 0; x < countOfChar.length; x++) { /// initialize array to zero
			countOfChar[x] = 0;
		}

		for (int i = 0; i < bytes.size(); i++) { /// get frequencies
			Byte checker = bytes.get(i);
			for (int x = 0; x < data.length; x++) {
				if (checker == data[x]) {
					countOfChar[i]++;
				}
			}
		}

		for (int i = 0; i < countOfChar.length - 1; i++) { //// sorting
			for (int j = 0; j < countOfChar.length - 1; j++) {
				if (countOfChar[j] < countOfChar[j + 1]) {
					int temp = countOfChar[j];
					countOfChar[j] = countOfChar[j + 1];
					countOfChar[j + 1] = temp;

					Byte tempChar = bytes.get(j);
					bytes.set(j, bytes.get(j + 1));
					bytes.set(j + 1, tempChar);
				}
			}

		}

		bintree.buildHuffman(countOfChar, bytes);
		WriteTable(bytes, countOfChar);
		Encode_msg(data, bintree.Huffman_codes);

	}

	public void WriteTable(ArrayList<Byte> chars, int[] numberOfChar) {
		try {

			int extrabyte = 0;
			fos = new FileOutputStream(new File("compressedBinary.bin"), false);

			if (chars.size() * 3 > 127) {
				fos.write(bigIntToByteArray(chars.size() * 3));
			} else {
				fos.write(0);
				fos.write(bigIntToByteArray(chars.size() * 3));
			}

			for (int i = 0; i < chars.size(); i++) {
				Byte bytee = chars.get(i);
				fos.write(bytee); /// write byte
				if (numberOfChar[i] <= 127) { /// write frequency

					fos.write(extrabyte);
					fos.write(numberOfChar[i]);

				} else {

					fos.write(bigIntToByteArray(numberOfChar[i]));
				}

			}
			fos.close();
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Compression File not found");
		}
	}

	public void Encode_msg(Byte[] data, String[] table) {

		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < table.length - 1; j = j + 2) {

				if (data[i].toString().equals(table[j])) {
					Add_to_buffer(table[j + 1]);
				}
			}
		}

		try {

			fos1 = new FileOutputStream(new File("compressedBinary.bin"), true);
			fos1.write(buffer.toByteArray());
			fos1.close();
			file1 = new File("compressedBinary.bin");

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
			bitIndex++;
		}
	}

	private byte[] bigIntToByteArray(final int i) {
		BigInteger bigInt = BigInteger.valueOf(i);
		return bigInt.toByteArray();
	}

	public float ratio() {

		float original = file.length();
		float compressed = file1.length();
		float ratio = original / compressed;
		return ratio;
	}

}
