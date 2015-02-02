package crawler;

import java.io.IOException;

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

public class Fetcher {

	private static Logger log = Logger.getLogger(Fetcher.class);
	
	/**
	 * ����urlץȡҳ��
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public JDocument getDocumentFromURL(String url)
			throws ClientProtocolException, IOException {
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
		return JDocument.getJDocument(content);
	}

}
