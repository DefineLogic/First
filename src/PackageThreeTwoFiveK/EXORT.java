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

//Program to generate logs for 
public class EXORT {
	
	
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
		ReaderIter ri=new ReaderIter();

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
			  
			  PrintWriter	out = new PrintWriter(new File("E:\\ParameteriztionLogs\\"+filename+".txt"));	
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
		    		  String log="";
		      NamedNodeMap attri = TC.getAttributes();
		      String testCaseName=attri.getNamedItem("name").getTextContent();	
		      log=log+"TEST_CASE_NAME>>>>>>>>>>>>> "+testCaseName+" <<<<<<<<<<<<<<<<<<<"+"\n";
		      NodeList NL=TC.getChildNodes();
		      
		     
		      //Getting lines within SoapRequest without Parameterization 
		      for(int i=0;i<NL.getLength();i++){
		    	     Node TS=NL.item(i);
			    	 if(TS.getNodeName().equals("con:testStep")){
			    	 NamedNodeMap attr = TS.getAttributes();
			         Node nodeAttr = attr.getNamedItem("type");
			         
			         if(nodeAttr.getTextContent().equals("request"))
			         {
			        	 String requestname=attr.getNamedItem("name").getTextContent();
		        	 	 log=log+"\n"+"  TEST_STEP_NAME ## "+requestname+"\n  ************************************\n";
		        	 	 NodeList NL1=TS.getChildNodes();
			        	 for(int j=0;j<NL1.getLength();j++){
			        		 Node TS1=NL1.item(j);
			        		 if(TS1.getNodeName().equals("con:config")){
			        			 NodeList NL2=TS1.getChildNodes();
			        			 for(int k=0;k<NL2.getLength();k++)
			        			 {
			        				Node TS2=NL2.item(k);
			        				
	        				   if(TS2.getNodeName().equals("con:request"))
			        				{
			        					NodeList NL3=TS2.getChildNodes();
			        					for(int m=0;m<NL3.getLength();m++)
			        					{
			        						Node TS3=NL3.item(m);
		        							if(TS3.getNodeName().equals("con:request"))
		        							{ 
		        								String testStep=nodeToString(TS3);
		        								log=log+ri.methodOne(testStep);
		        							}
		        						
			        					}  								
			        				}
			        			 }
			        		 }
			        	 }
			         }
			    	 }
		      }
			        	 		 
		      out.println(log);
		      out.println("-----------------------------------------------------------------------------------------------------");
		          }
		    	  }
		     
		         out.close();

		         
		      } catch (Exception e) {
		         e.printStackTrace();
		      }
		   
	   }
		    

	}
	
	   
	

}
