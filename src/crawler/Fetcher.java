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

/**
 * ����������ĸ��࣬��ʼ����Ψһ��url���Ϻ�url����,�����˱�Ҫ�ķ���
 * @author wangjie3
 *
 */
public abstract class Fetcher implements Runnable{

	private static Logger log = Logger.getLogger(Fetcher.class);

	/**
	 * ��ץȡurl����
	 */
	protected static BlockingQueue<String> urlToFetch = new LinkedBlockingDeque();

	/**
	 * ȫ��url����
	 */
	protected static Set<String> urlSet = new HashSet<String>();

	/**
	 * ��ʼ����ץȡurl����
	 */
	static{
		urlToFetch.offer("http://shop73005687.taobao.com/");
		log.info("http://shop73005687.taobao.com/");
	}
	
	/**
	 * ���������Ҫץȡ����,���巽���߼���������ʵ��
	 */
	protected abstract void fetch();
}
