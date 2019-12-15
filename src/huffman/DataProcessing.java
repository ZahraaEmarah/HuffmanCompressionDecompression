package huffman;
import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.Scanner;
import java.util.ArrayList;
import java.util.BitSet;


public class DataProcessing {
	
	char[] charArray ;
	ArrayList<Character> chars = new ArrayList<Character>();
	huffmanTree tree = new huffmanTree();
	Compression d = new Compression();
	int original_size=0;
	int compress_size = 0;
    
	public void Read_data(String filename) {
		
		String data = "";  
		
		
		d.Clear_output_file();
		
	    try {
	      File file = new File(filename);
	      Scanner myReader = new Scanner(file);
	      while (myReader.hasNextLine()) {
	         data = data + myReader.nextLine(); 
	      }
	      myReader.close();
	    } catch (FileNotFoundException e) {
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
		d.WriteToFile("-");
		translate();
		System.out.println("ORIGINAL SIZE IS " + (original_size*8));
	  }
	
	public void translate()
	{
		String[] arrOfStr;
		int length = tree.Huffman_table.size();
		d.WriteToFile("\n");
		for (int i = 0; i < charArray.length; i++) {
			for(int j=0; j< length; j++)
			{
				String str = tree.Huffman_table.get(j);
				arrOfStr = str.split(":", 2); 
				if(charArray[i] == arrOfStr[0].charAt(0))
				{
					compress_size += d.Str_Compress(arrOfStr[1]);
				}
			}
		}
		
		System.out.println("COMPRESSED " +(compress_size + tree.table_size())/8);
	}
	
	public String ratio()
	{
		double x = (original_size*8) / ((compress_size + tree.table_size())/8);
       	
		return Double.toString(x);
	}
}
