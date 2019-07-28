package PackageThreeTwoFiveK;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BABAAL {
	   public  void removeComments(){
		   
		final File folder = new File("E:\\Results");

		    for (final File fileEntry : folder.listFiles()) {

	            String xfilename=fileEntry.getName();
	            String filename="";
		            
		            for(int i=0;i<xfilename.length()-4;i++)
		            {
		            	filename=filename+xfilename.charAt(i);
		            	
		            }
			        System.out.println(filename);

		         
		try {
			  String file="";
		  File inputFile = new File("E:\\Results\\"+xfilename); 	    	
         InputStreamReader fileReader = new FileReader(inputFile);
	        BufferedReader bufReader = new BufferedReader(fileReader);
	        
	        StringBuilder sb = new StringBuilder();
	        String line = bufReader.readLine();
	        while( line != null){
	            sb.append(line).append("\n");
	            line = bufReader.readLine();
	        }
	        file = sb.toString();
	        //System.out.println(file);
			  Pattern p = Pattern.compile("(?s)<!--.*?-->");  
			  //(.*)(\\d+)(.*)
		      // get a matcher object
		      Matcher m = p.matcher(file); 
		      file = m.replaceAll(""); 
			  
			
			   PrintWriter pw=new PrintWriter(new File("E:\\Resultss\\"+xfilename));
			   pw.print(file);
			   pw.close();

		     } catch (Exception e) {
		         e.printStackTrace();
		      }
		   
	   }
		    

	}
	
	   
	

}

