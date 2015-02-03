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
 * 所有爬虫类的父类，初始化了唯一的url集合和url队列,定义了必要的方法
 * @author wangjie3
 *
 */
public abstract class Fetcher implements Runnable{

	private static Logger log = Logger.getLogger(Fetcher.class);

	/**
	 * 待抓取url队列
	 */
	protected static BlockingQueue<String> urlToFetch = new LinkedBlockingDeque();

	/**
	 * 全部url集合
	 */
	protected static Set<String> urlSet = new HashSet<String>();

	/**
	 * 初始化待抓取url队列
	 */
	static{
		urlToFetch.offer("http://shop73005687.taobao.com/");
		log.info("http://shop73005687.taobao.com/");
	}
	
	/**
	 * 爬虫类的主要抓取方法,具体方法逻辑在子类中实现
	 */
	protected abstract void fetch();
}
