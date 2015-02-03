package crawler;

import java.io.IOException;
import java.util.List;

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

/**
 * �������������
 * @author wangjie3
 *
 */
public class BFSFetcher extends Fetcher{
	private static Logger log = Logger.getLogger(BFSFetcher.class);


	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			fetch();
		}

	}

	@Override
	protected void fetch() {
		// TODO Auto-generated method stub
		try {
			if (super.urlToFetch.peek() != null) {
				JDocument document = getDocumentFromURL(super.urlToFetch.poll());
				List<String> urls = document.getURL();
				// ����url�ظ�ץȡ
				urls.removeAll(super.urlSet);
				//��ΪurlToFetch���Ƚ��ȳ��Ķ��У������ǹ�����ȱ���
				for (String url : urls) {
					if (super.urlSet.add(url)) {
						super.urlToFetch.offer(url);
						System.out.println(url);
					}
				}
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			log.error(e.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.toString());
		}
	}

	/**
	 * ����urlץȡҳ��
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private JDocument getDocumentFromURL(String url)
			throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		Document content = null;
		// ����GET��ʱʱ��
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params, 10 * 1000);
		HttpConnectionParams.setSoTimeout(params, 10 * 1000);
		AbstractHttpClient httpClient = new DefaultHttpClient(params);
		// ��������
		HttpGet get = new HttpGet(url);
		HttpResponse response = httpClient.execute(get);
		// �ȴ���Ӧͷ�õ�ʵ��
		HttpEntity entity = response.getEntity();
		String responeString = EntityUtils.toString(entity, "utf-8");
		content = Jsoup.parse(responeString);
		log.info("ץȡ������" + url);
		return JDocument.getJDocument(content);
	}
}
