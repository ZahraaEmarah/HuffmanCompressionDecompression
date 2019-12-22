package binaryHuffman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class folderClass {

	ArrayList<String> fileNames = new ArrayList<String>();
	ArrayList<Integer> lengthsoffiles = new ArrayList<Integer>();
	ArrayList<Byte> combinedBytes = new ArrayList<Byte>();
	Compression c = new Compression();
	int combinedcounter = 0;
	int folderlength = 0;
	Byte[] data;
	int[] lengtharr;

	public void compressFolder(String foldername) {
		final File folder = new File(foldername);
		listFilesForFolder(folder);

		try {
			//FileOutputStream fos = new FileOutputStream(new File("compressedBinary.bin"), false);
			//fos.write(fileNames.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		c.binC(foldername + "/" + fileNames.get(0));
		c.binC(foldername + "/" + fileNames.get(1));
		
		
		try {
			byte[] content = Files.readAllBytes(Paths.get("compressedBinary.bin"));
			System.out.println(Arrays.toString(content));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < fileNames.size(); i++) {
			String name = foldername + "/" + fileNames.get(i);
			// c.binC(name);
		}
	}

	public void decompressFolder() {

	}

	public void readFile(String filename, String foldername) {
		filename = foldername + "/" + filename;
		try {

			byte[] b = Files.readAllBytes(Paths.get(filename));
			data = new Byte[b.length];
			for (int i = 0; i < b.length; i++) {
				data[i] = Byte.valueOf(b[i]);
			}

			for (int i = 0; i < data.length; i++) {
				combinedBytes.add(data[i]);
				combinedcounter++;
			}

			// System.out.println(Arrays.toString(data));

			for (int i = 0; i < combinedBytes.size(); i++) {
				// System.out.println(combinedBytes.get(i));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void listFilesForFolder(final File folder) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				fileNames.add(fileEntry.getName());
			}
		}
	}

}
