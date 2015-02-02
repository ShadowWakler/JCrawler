package crawler;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Fetcher implements Runnable {

	private static Logger log = Logger.getLogger(Fetcher.class);

	private static BlockingQueue<String> urlToFetch = new LinkedBlockingDeque();

	private static Set<String> urlSet = new HashSet<String>();

	/**
	 * 根据url抓取页面
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public JDocument getDocumentFromURL(String url)
			throws ClientProtocolException, IOException {
		Document content = null;
		// 设置GET超时时间
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params, 10 * 1000);
		HttpConnectionParams.setSoTimeout(params, 10 * 1000);
		AbstractHttpClient httpClient = new DefaultHttpClient(params);
		// 构造请求
		HttpGet get = new HttpGet(url);
		HttpResponse response = httpClient.execute(get);
		// 先从响应头得到实体
		HttpEntity entity = response.getEntity();
		String responeString = EntityUtils.toString(entity, "utf-8");
		content = Jsoup.parse(responeString);
		return JDocument.getJDocument(content);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		urlToFetch.offer("http://shop73005687.taobao.com/");
		while (true) {
			try {
				if (urlToFetch.peek() != null) {
					JDocument document = getDocumentFromURL(urlToFetch.poll());
					List<String> urls = document.getURL();

					// 避免url重复抓取
					urls.removeAll(urlSet);
					for (String url : urls) {
						if (urlSet.add(url)) {
							urlToFetch.offer(url);
							log.info(url);
						}
					}
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

}
