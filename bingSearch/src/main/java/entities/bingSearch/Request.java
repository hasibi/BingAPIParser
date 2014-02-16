package entities.bingSearch;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.codec.binary.Base64;

/**
 * Unit test for simple App.
 */
public class Request
{
	static String accountKey = "Z2ikXy/Spk3t5ay/CdGd5zCAtfIJWngGaaFSrGososg=";
	
	public static void main( String[] args )
    {
        String query = "paris";
        System.out.println( "Searching for Query: " + query );
        InputStream is = BuildRequest( query, 10);
        String result = streamToString(is);
        System.out.println("output \n" + result);
        stringToFile(result, "./src/main/outputs/" + query+".xml");
//        InputStream is = BuildRequest(query);

//        try
//        {
//            // Send the request; display the response.
//        	
//			//XMLParser.DisplayResponse(is);
//
//			//Convert response into String
//			String line;
//			StringBuilder builder = new StringBuilder();
//			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//			while((line = reader.readLine()) != null) {
//				builder.append(line);
//			}
//			String response = builder.toString();
//			System.out.println("writes output to file ...");
//			//Convert response into file
//			FileWriter fstream = new FileWriter(new File("dallass.xml"), true);
//			fstream.write(response);
//			fstream.close();
//        }
//        catch (Exception ex)
//        {
//            // An exception occurred while accessing the network.
//		    System.err.println("IOException: " + ex.getMessage());
//        }
	}
	
	static InputStream BuildRequest(String query, int count)
    {
        
        /*
            // Common request fields (required)
            + "Query=dallas"
            // Common request fields (optional)
            + "&Version=2.0"
            + "&Market=en-us"
            + "&Adult=Moderate"
            + "&Options=EnableHighlighting"

            // Web-specific request fields (optional)
            + "&Web.Count=10"
            + "&Web.Offset=0"
            + "&Web.Options=DisableHostCollapsing+DisableQueryAlterations";
*/
        // Create and initialize the request.
        InputStream is = null;
		try {
			String requestString = "https://api.datamarket.azure.com/Bing/SearchWeb/v1/Web?Query=" 
						+ URLEncoder.encode("\'\"" + query + "\"\'", "UTF-8")
//						+ "Market=" + URLEncoder.encode("\'en-GB\'", "UTF-8");
						+ "&$top=" + count;
			System.out.println("Generated URL:\n" + requestString);
			URL request = new URL(requestString);
			HttpsURLConnection urlConn = (HttpsURLConnection) request.openConnection();
			urlConn.setRequestMethod("GET");

			// Sets authentication for Bing request
			byte[] accountKeyBytes = Base64.encodeBase64((accountKey + ":" + accountKey).getBytes());
	        String accountKeyEnc = new String(accountKeyBytes);
			urlConn.setRequestProperty("Authorization", "Basic " + accountKeyEnc);
//			System.out.println( "connection code : " + urlConn.getResponseMessage());
			System.out.println("obtaining data from search service for query \"" + query + "\"...");
			is = urlConn.getInputStream();
		} catch (MalformedURLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return is;
    }
	
	private static String streamToString(InputStream is){
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder builder = new StringBuilder();
		String line = null;
		try {
			while((line = br.readLine()) != null)
				builder.append(line);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	private static void stringToFile(String str, String fileName){
//		File file = new File(fileName);
		
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(fileName));
			writer.write(str);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
