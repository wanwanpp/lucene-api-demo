package com.html;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;

public class TestHttpClient {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		HttpClient httpClient=new DefaultHttpClient();
		HttpGet httpGet=new HttpGet("http://www.baidu.com/s?wd=httpClient");
		HttpResponse response=httpClient.execute(httpGet);
		HttpEntity entity=response.getEntity();
		System.out.println(entity.getContentEncoding());
		//wirteTo方法直接将其写入输出流中，但是不会进行格式压缩
//		entity.writeTo(new FileOutputStream(new File("F:\\IDEA\\Luence\\testHtml\\src\\com\\html\\out\\baidu.html")));
		InputStream ins=entity.getContent();
		BufferedReader reader=new BufferedReader(new InputStreamReader(ins,"GBK"));
		FileWriter writer=new FileWriter(new File("F:\\IDEA\\Luence\\testHtml\\src\\com\\html\\out\\baidu.html"));
		String strLine=reader.readLine();
		while(strLine!=null){
			writer.write(strLine);
			strLine=reader.readLine();
		}
		writer.close();
		ins.close();
		reader.close();
		httpClient.getConnectionManager().shutdown();
		System.out.println("页面生成完毕！");
	}

}
