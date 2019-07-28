package PackageThreeTwoFiveK;
import java.util.regex.*;
import java.io.*;

/**
 * Print all the strings that match a given pattern from a file.
 */
public class ReaderIter {
  public String methodOne(String a) throws IOException {
	  String lineX="";
    // The RE pattern
    Pattern patt = Pattern.compile("(?s)<.*>(.*)</.*?>");
    //"(?s)<!--.*?-->"
    // A FileReader (see the I/O chapter)
    BufferedReader r = new BufferedReader(new StringReader(a));

    // For each line of input, try matching in it.
    String line;
    while ((line = r.readLine()) != null) {
      // For each match in the line, extract and print it.
      Matcher m = patt.matcher(line);
      while (m.find()) {
        // Simplest method:
        String linee=m.group(0);
        if (linee.equals("</soapenv:Envelope>]]></con:request>")|| linee.equals("</SOAP-ENV:Envelope>]]></con:request>"))
        {
        }
        else{
       if(!(linee.contains("$"))){
    	   
    	   lineX=lineX+"     "+linee+"\n";
    	   
    	 //System.out.println(m.group(0));
    	   
       }
        }

        // Get the starting position of the text
        //int start = m.start(0);
        // Get ending position
        //int end = m.end(0);
        // Print whatever matched.
        // Use CharacterIterator.substring(offset, end);
        //System.out.println(line.substring(start, end));
      }
    }
return lineX;
  }
}
