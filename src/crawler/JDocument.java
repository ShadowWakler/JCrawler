package crawler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JDocument extends Document{
	
	private Document document;
	
	private JDocument(String baseUri) {
		super(baseUri);
		// TODO Auto-generated constructor stub
	}
	
	public static JDocument getJDocument(Document document){
		JDocument jDocument = new JDocument(document.baseUri());
		jDocument.document = document;
		return jDocument;
	}
	
	/**
	 * 获取JDocument对象中的url连接
	 * @return
	 */
	public List<String> getURL(){
		Elements urlElements = this.document.getElementsByTag("a");
		Iterator<Element> it = urlElements.iterator();
		List<String> urls = new ArrayList<String>();
		while(it.hasNext()){
			Element e = it.next();
			for(String url : e.attr("href").split("#")){
				if(url.matches("(http://|ftp://|https://|www){0,1}[^\u4e00-\u9fa5\\s]*?\\.(com|net|cn|me|tw|fr)[^\u4e00-\u9fa5\\s]*")){
					urls.add(url);
				}
			}
		}
		return urls;
	}
}
