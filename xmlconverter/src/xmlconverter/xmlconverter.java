// Developer:		Charles DeGrapharee Exum
// Contact: 		charlesexumdev@gmail.com
// Date:			2018.11.11
// Purpose:			A code sample which reads in XML files and generates: .json and .txt files.

package xmlconverter;

//supporting documentation found at: http://www.java2s.com/Code/Jar/j/Downloadjavajsonjar.htm
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class xmlconverter {	
	
	// fetches a raw XML from a URL passed in as String
	// prints raw XML to console before returning Document
	public static String get_XML_from_URL(String psURL) {
		System.out.println("get_XML_from_URL");		
		
		// declare
		String temp_output = null;
		
		DocumentBuilderFactory temp_document_builder_factory = null; 
		DocumentBuilder temp_documentbuilder = null;
		Document temp_document = null;
		
		Transformer temp_transformer = null;
		TransformerFactory temp_transformer_factory = null;	
		
		StringWriter temp_string_writer = null;		
		
		// prepare Document
		temp_document_builder_factory = DocumentBuilderFactory.newInstance();
		try {
			temp_documentbuilder = temp_document_builder_factory.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {			
			e1.printStackTrace();
		}
		
		// get raw XML from URL
		try {
			temp_document = temp_documentbuilder.parse(new URL(psURL).openStream());
		} catch (MalformedURLException e) {			
			e.printStackTrace();
		} catch (SAXException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		temp_transformer_factory = TransformerFactory.newInstance();		
		try {
			temp_transformer = temp_transformer_factory.newTransformer();
		} catch (TransformerConfigurationException e) {			
			e.printStackTrace();
		}

		// stream the raw XML into buffer
		temp_string_writer = new StringWriter();
		try {
			temp_transformer.transform(new DOMSource(temp_document), new StreamResult(temp_string_writer));
		} catch (TransformerException e) {			
			e.printStackTrace();
		}
		
		temp_output = temp_string_writer.toString();
		System.out.print(temp_output);
				
		return temp_output;		
	}
	
	public static String get_XML_from_file(String psXMLpath) {
		System.out.println("get_XML_from_file");		
		
		// declare
		String temp_output = null;
		
		File temp_file = null;
		DocumentBuilderFactory temp_document_builder_factory = null; 
		DocumentBuilder temp_documentbuilder = null;
		Document temp_document = null;
		
		Transformer temp_transformer = null;
		TransformerFactory temp_transformer_factory = null;	
		
		StringWriter temp_string_writer = null;		
		
		// prepare Document
		temp_document_builder_factory = DocumentBuilderFactory.newInstance();
		try {
			temp_documentbuilder = temp_document_builder_factory.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {			
			e1.printStackTrace();
		}
		
		// get raw XML from file
		try {
			temp_file = new File(psXMLpath);
			temp_document = temp_documentbuilder.parse(psXMLpath);			
		} catch (MalformedURLException e) {			
			e.printStackTrace();
		} catch (SAXException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		temp_transformer_factory = TransformerFactory.newInstance();		
		try {
			temp_transformer = temp_transformer_factory.newTransformer();
		} catch (TransformerConfigurationException e) {			
			e.printStackTrace();
		}

		// stream the raw XML into buffer
		temp_string_writer = new StringWriter();
		try {
			temp_transformer.transform(new DOMSource(temp_document), new StreamResult(temp_string_writer));
		} catch (TransformerException e) {			
			e.printStackTrace();
		}
		
		temp_output = temp_string_writer.toString();
		System.out.print(temp_output);
				
		return temp_output;		
		
	}

	public static JSONObject convert_XML_to_JSON(String psXML) {
		System.out.println("convert_XML_to_JSON");
		
		JSONObject temp_JSON_object = null;
		String temp_buffer = null;
		
		// read XML string into JSONObject
		try {
			temp_JSON_object = XML.toJSONObject(psXML);
		} catch (JSONException e) {			
			e.printStackTrace();
		}		
		
		// prove that the JSON object is populated with the data correctly		
		try {
			temp_buffer = temp_JSON_object.toString(4);
		} catch (JSONException e) {			
			e.printStackTrace();
		}
		System.out.print(temp_buffer);

		return temp_JSON_object;
		}
	
	public static String convert_XML_to_CSV(String psXML) {
		System.out.println("convert_XML_to_CSV");
		
		// declare 
		String temp_output = null;		
		File temp_stylesheet = null;
	    DocumentBuilderFactory temp_codument_builder_factory = null;
	    DocumentBuilder temp_document_builder = null;
	    Document temp_document = null;
	    InputSource temp_input_source = null;
	    StringWriter temp_string_writer = null;
	    Transformer temp_transformer = null;
	    StreamSource temp_stylesource = null;		
	    
	    // set up document	    
	    temp_codument_builder_factory = DocumentBuilderFactory.newInstance();
		try {
			temp_document_builder = temp_codument_builder_factory.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {			
			e1.printStackTrace();
		}
	    
		try {
			temp_input_source = new InputSource(new StringReader(psXML));
			temp_document = temp_document_builder.parse(temp_input_source);
		} catch (SAXException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}		
		
		temp_stylesheet = new File("resources/style.xsl");
	    temp_stylesource = new StreamSource(temp_stylesheet);
	    
		try {
			temp_transformer = TransformerFactory.newInstance().newTransformer(temp_stylesource);
		} catch (TransformerConfigurationException e) {			
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {			
			e.printStackTrace();
		}
		
		// stream the raw XML into buffer
		temp_string_writer = new StringWriter();
		try {			
			temp_transformer.transform(new DOMSource(temp_document), new StreamResult(temp_string_writer));
		} catch (TransformerException e) {			
			e.printStackTrace();
		}
		
		temp_output = temp_string_writer.toString();
		System.out.print(temp_output);
		return temp_output;
	}
	
	public static void write_CSV_to_file(String pspath, String psdata) {
		System.out.println("write_CSV_to_file");
		
		PrintWriter temp_print_writer = null;	
		try {
			temp_print_writer = new PrintWriter(pspath, "UTF-8");
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {			
			e.printStackTrace();
		}
		
		temp_print_writer.write(psdata);
		temp_print_writer.close();
	}	
	
	public static void write_JSON_to_file(String pspath, JSONObject podata) {
		System.out.println("write_JSON_to_file");
		
		String temp_buffer = null;
		PrintWriter temp_print_writer = null;	
		
		try {
			temp_buffer = podata.toString(4);
		} catch (JSONException e) {
			e.printStackTrace();
		}		
		
		try {
			temp_print_writer = new PrintWriter(pspath, "UTF-8");
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {			
			e.printStackTrace();
		}
		
		temp_print_writer.write(temp_buffer);
		temp_print_writer.close();
	}

    public static void main(String[] args) {
    	
    	System.out.println("xml conversion utility.");     	
    	// read in XML
    	String s_temp_URL = "http://www.bindows.net/documentation/download/ab.xml";
    	String s_temp_XML = get_XML_from_URL(s_temp_URL);
    	
    	// alternatively, read XML from local file
    	//String s_temp_XML_file_path = "resources/ab.xml";
    	//s_temp_XML = get_XML_from_file(s_temp_XML_file_path);
    	
    	// stream into JSONObject 
    	// write .json file to specified path
    	JSONObject o_temp_JSON = convert_XML_to_JSON(s_temp_XML);
    	String s_temp_JSON_file_path = "output/myJSON.json";
    	write_JSON_to_file(s_temp_JSON_file_path, o_temp_JSON);
    	
    	// stream CSV into string   
    	// write CSV to file
    	String s_temp_CSV = convert_XML_to_CSV(s_temp_XML);    	
    	String s_temp_CSV_file_path = "output/myCSV.txt";
    	write_CSV_to_file(s_temp_CSV_file_path, s_temp_CSV);    	
    }
}
