package pearson;


import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.commons.io.FileUtils;

public class Files {
	
	    public static void main(String[] args) throws Exception {
	    	 
	    	//Can the value of j to desired No of output files.
	    	int j =40000;
	    	
	    	
	    	File file = new File("D:\\Pearson\\TestData\\"); 
	    	
	    	//Deletes the existing files every time the program is executed.
	    	FileUtils.cleanDirectory(file);
	    	
	    	// For loop to create the desired No. of files
	        for(int i=0;i<j;i++){
	        PrintStream ps;
	        try {
	        	
	        	// Change the file extension to the desired format.
	        	String filename = i+".txt";
	        	//Location for the files to be created.
	        	//Make sure the given location exists, if not error will occur.
	            File files = new File("D:\\Pearson\\TestData\\" +filename);
	            ps = new PrintStream(files);
	            
	            //Change the Content to increase the file size. Current 1kb sized files will be generated.
	            String Content = "Content";
	            // Change Size value for increase in file size; 560 for 20 kb and 1160 for 40 kb
	            int Size = 1;
	            for(int k=0;k<Size;k++){
	            ps.println (Content);
	            
	            }
	            
	            ps.close(); 
	            
	        } catch ( IOException e ) {
	            e.printStackTrace();
	        } 
	        }
	    }
	}

