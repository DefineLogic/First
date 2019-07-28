package PackageThreeTwoFiveK;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class YO {
	
	private static String nodeToString(Node node) {
		StringWriter sw = new StringWriter();
		try {
		 Transformer t = TransformerFactory.newInstance().newTransformer();
		 t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		 t.setOutputProperty(OutputKeys.INDENT, "yes");
		 t.transform(new DOMSource(node), new StreamResult(sw));
		} catch (TransformerException te) {
		 System.out.println("nodeToString Transformer Exception");
		}
		return sw.toString();
		}
	
	
	public static void main(String[] args){
		
	
		final File folder = new File("E:\\TestSuites");

		    for (final File fileEntry : folder.listFiles()) {

	            String xfilename=fileEntry.getName();
	            String filename="";
		            
		            for(int i=0;i<xfilename.length()-4;i++)
		            {
		            	filename=filename+xfilename.charAt(i);
		            	
		            }
		        System.out.println(filename);
		         
		try {
			  int deletedcount=0;	
			  PrintWriter	out = new PrintWriter(new File("E:\\logs\\"+filename+".txt"));	
			  File inputFile = new File("E:\\TestSuites\\"+xfilename); 	    	
		      DocumentBuilderFactory docFactory =DocumentBuilderFactory.newInstance();
		      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		      Document doc = docBuilder.parse(inputFile);
		      Node SU = doc.getFirstChild();
		      NodeList NLTC=SU.getChildNodes();
		      for(int ii=0;ii<NLTC.getLength();ii++)
		      {   
		    	  
		    	  Node TC=NLTC.item(ii);
		    	  if(TC.getNodeName().equals("con:testCase")){
		      String unusedpro="";
		      NamedNodeMap attri = TC.getAttributes();
		      String testCaseName=attri.getNamedItem("name").getTextContent();	
		      unusedpro=unusedpro+"TEST_CASE_NAME>> "+testCaseName+"\n";
		      String xml ="";
		      NodeList NL=TC.getChildNodes();
		      
		      //creating text within which search is done

		      for(int i=0;i<NL.getLength();i++){
		    	  
		      
		    	 Node TS=NL.item(i);
		    	 if(TS.getNodeName().equals("con:testStep")){
		    	 NamedNodeMap attr = TS.getAttributes();
		         Node nodeAttr = attr.getNamedItem("type");
		         
		         if(nodeAttr.getTextContent().equals("properties"))
		         {
		        	 
		         } 
		         
		         else{
			        	xml=xml+nodeToString(TS); 

		         }
		         
		    	 }
		    	 }
		      String xml1=xml.toLowerCase();
		      //System.out.println(xml1);
		      
		      
		      //Getting Static and Dynamic Properties and Removing them
		      for(int i=0;i<NL.getLength();i++){
		    	  
			      
			    	 Node TS=NL.item(i);
			    	 if(TS.getNodeName().equals("con:testStep")){
			    	 NamedNodeMap attr = TS.getAttributes();
			         Node nodeAttr = attr.getNamedItem("type");
			         
			         if(nodeAttr.getTextContent().equals("properties"))
			         {
			        	 String propertiesname=attr.getNamedItem("name").getTextContent();

			        	 unusedpro=unusedpro+"\n"+propertiesname+"\n**********\n";

			        	 NodeList NL1=TS.getChildNodes();
			        	 for(int j=0;j<NL1.getLength();j++){
			        		 Node TS1=NL1.item(j);
			        		 if(TS1.getNodeName().equals("con:config")){
			        			 NodeList NL2=TS1.getChildNodes();
			        			 for(int k=0;k<NL2.getLength();k++)
			        			 {
			        				Node TS2=NL2.item(k);
			        				
			        				if(TS2.getNodeName().equals("con:properties"))
			        				{
			        					NodeList properties=TS2.getChildNodes();
			        					int a=properties.getLength();			    
			        					for(int l=0;l<a;l++)
			        					{
			        						Node property=properties.item(l);
			        						NodeList NL3=property.getChildNodes();
			        						for(int m=0;m<NL3.getLength();m++)
			        						{
			        							Node name=NL3.item(m);
			        							if(name.getNodeName().equals("con:name"))
			        							{
			        								
			        								String naam=name.getTextContent().toLowerCase();
			        								String naam1=("${"+propertiesname+"#"+naam).toLowerCase();
			        								if(xml1.contains(naam)||(xml1.contains(naam1))){
			        									
			        								}
			        								else{
			        									
			        									unusedpro=unusedpro+(name.getTextContent())+"\n";
//			        									System.out.println(nodeToString(property));
//			        									System.out.println(l);
//			        									System.out.println(a);	
														a--;
														l--;
													deletedcount++;	
			        								TS2.removeChild(property);	
			        								}
			        							
			        								
			        							}
			        						}
			        					}
			        				}
			        			 }
			        		 }
			        	 }
			        	 
			         } 
			         
			         
			    	 }
			    	 }
		      // two brackets
		      out.println(unusedpro);
		      out.println("-------------------------------------------------------------------------");
		          }
		    	  }
		    
		      
		   

		      
		         TransformerFactory transformerFactory = TransformerFactory.newInstance();
		         Transformer transformer = transformerFactory.newTransformer();
		         DOMSource source = new DOMSource(doc);
		         
		         StreamResult file=new StreamResult(new File("E:\\Results\\"+xfilename));
		         transformer.transform(source,file);
		         
		         
		         out.println("TOTAL>>NO>>OF>>PROPERTIES>>DELETED::"+deletedcount);
		         out.close();

		         
		      } catch (Exception e) {
		         e.printStackTrace();
		      }
		   
	   }
		    System.out.println("\n");
		    BABAAL ba=new BABAAL();
		    ba.removeComments();
	}
	

	

}
