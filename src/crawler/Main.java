package crawler;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import org.apache.http.client.ClientProtocolException;

public class Main {
	

	public static void main(String[] args) {
		new Thread(new BFSFetcher()).start();
	}
}
