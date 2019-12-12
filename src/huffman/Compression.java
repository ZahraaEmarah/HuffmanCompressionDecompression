package huffman;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class Compression {
	
	public void Clear_output_file()
	{ 
		try {
        String str = ""; 
        FileWriter fw=new FileWriter("compressed.txt"); 
        fw.write(str); 
        fw.close(); }
		catch(Exception e)
		{
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
        	}
        catch(Exception e)
        {
        	e.printStackTrace();
        }
	}
	
}
