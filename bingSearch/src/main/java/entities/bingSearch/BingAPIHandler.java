package entities.bingSearch;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BingAPIHandler extends DefaultHandler {

	public List<Entry> entries = new ArrayList<Entry>();
	public Entry entry = null;
	
	private boolean title = false;
	private boolean url = false;
	private boolean displayUrl = false;
	
	public List<Entry> getEntries(){
		return entries;
	}
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
		if (qName.equalsIgnoreCase("entry")){
			entry = new Entry();
			System.out.println(entries.isEmpty());


		}
		else if (qName.equalsIgnoreCase("d:Title"))
			title = true;
		else if(qName.equalsIgnoreCase("d:DisplayUrl"))
			displayUrl = true;
		else if(qName.equalsIgnoreCase("d:Url"))
			url = true;
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException{
		System.out.println("End Element :" + qName);
		if (qName.equalsIgnoreCase("entry")){
			System.out.println("End Element ENTRY :" + qName);
			System.out.println(entry.getUrl());
			entries.add(entry);
			}
	}
	
	public void characters(char[] ch, int start, int length) throws SAXException{
		if(title){
			entry.setTitle(new String(ch,start,length));
			title = false;
		}
		else if(displayUrl){
			entry.setDisplayUrl(new String(ch,start, length));
			displayUrl = false;
		}
		else if(url){
			entry.setUrl(new String(ch, start, length));
			url = false;
		}
	}	
}
