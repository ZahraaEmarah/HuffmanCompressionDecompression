package binaryHuffman;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class folderClass {
	
	ArrayList<String> fileNames = new ArrayList<String>();
	ArrayList<Byte> combinedBytes = new ArrayList<Byte>();
	BinaryCompression c = new BinaryCompression();
	int combinedcounter = 0;
	Byte[] data;
	
	public void compressFolder(String foldername)
	{
		final File folder = new File(foldername);
		listFilesForFolder(folder);
		
		for(int i=0; i< fileNames.size(); i++)
		{
			System.out.println(fileNames.get(i));
			readFile(fileNames.get(i), foldername);
		}
		
		Byte[] b = new Byte[combinedBytes.size()];
		for(int i=0; i<combinedBytes.size(); i++)
		{
			b[i] = combinedBytes.get(i);
		}
		
		c.GenerateTable(b);
	}
	
	public void readFile(String filename, String foldername)
	{
		filename = foldername + "/" + filename;
		try {

			byte[] b = Files.readAllBytes(Paths.get(filename));
			data = new Byte[b.length];
			for (int i = 0; i < b.length; i++) {
				data[i] = Byte.valueOf(b[i]);
			}
			
			for(int i=0; i<data.length ; i++)
			{
				combinedBytes.add(data[i]);
				combinedcounter++;
			}
			
			System.out.println(Arrays.toString(data));
			
			for(int i=0; i<combinedBytes.size(); i++)
			{
				System.out.println(combinedBytes.get(i));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public  void listFilesForFolder(final File folder) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				fileNames.add(fileEntry.getName());
				System.out.println(fileEntry.length());
			}
		}
	}

}
