package crawler;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import org.apache.http.client.ClientProtocolException;

public class Main {
	private BlockingQueue<String> urlToFetch;

	public static void main(String[] args) {
		Fetcher fetcher = new Fetcher();
		try {
			JDocument doc = fetcher.getDocumentFromURL("http://shop73005687.taobao.com/");
			for(String str: doc.getURL()){
				System.out.println(str);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
