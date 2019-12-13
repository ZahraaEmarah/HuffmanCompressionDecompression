package huffman;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.BitSet;

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
	
	public void Str_Compress(String line) {
		
        try {
        	BitSet buffer = new BitSet();
        	int bitIndex = 0;
        	FileOutputStream fos = new FileOutputStream(new File("compressed.txt"),true);
        	
        	for(int i=0; i< line.length();i++)
        	{
        		if (line.charAt(i) == '1') {
        		    buffer.set(bitIndex);
        		} else {
        		    buffer.clear(bitIndex);
        		}
        		bitIndex ++;
        	}
        	System.out.println("b "+buffer.toByteArray());
        	
        	fos.write(buffer.toByteArray());
        	fos.close();
        	}
        catch(Exception e)
        {
        	e.printStackTrace();
        }
	}
	
}
