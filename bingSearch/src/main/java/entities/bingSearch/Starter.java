package entities.bingSearch;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class Starter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String query = "paris";
		search(query);
        
	}
	
	public static void search(String query){
//		System.out.println( "Searching for Query: " + query );
//        InputStream is = Request.BuildRequest( query, 10);
//        String result = Request.streamToString(is);
//        System.out.println("output \n" + result);
//        Request.stringToFile(result, "./src/main/outputs/" + query+".xml");
        
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
			SAXParser saxParser = saxParserFactory.newSAXParser();
	        BingAPIHandler handler = new BingAPIHandler();
	        saxParser.parse(new File("./src/main/outputs/paris.xml"), handler);
	        //saxParser.parse(is, handler);
	        List<Entry> entries = handler.getEntries();
	        String s = ""; 
	        for(Entry entry: entries){
	        	s += query + "\t" + entry.getDisplayUrl() + "\t" + entry.getUrl() + "\t" + entry.getTitle() + "\n";
	        
	        }
	        System.out.println("Writing results to the file");
	        Request.stringToFile(s,"./src/main/outputs/results.txt");
	        
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (SAXException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());

		}
	}

}
