package entities.bingSearch;

public class Entry {

	private String title;
	private String url;
	private String displayUrl;
	
	public void setUrl(String s){
		url = s;
	}
	public String getUrl(){
		return this.url;
	}
	
	public void setDisplayUrl(String s){
		displayUrl = s;
	}
	public String getDisplayUrl(){
		return this.displayUrl;
	}
	
	public void setTitle(String s){
		title = s;
	}
	public String getTitle(){
		return this.title;
	}
}
