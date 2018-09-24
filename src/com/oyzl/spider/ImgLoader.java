package com.oyzl.spider;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class ImgLoader implements Runnable {

	private Book book;

	public ImgLoader(Book book) {
		this.book = book;
	}

	@Override
	public void run() {

		File file = new File("bookCover");
		if (!file.exists())
			file.mkdirs();

		String url = book.getCover();
		int index = url.lastIndexOf('/');
		// 获取图片文件名
		String name = url.substring(index + 1, url.length());
		try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(file, name)))) {
			// 使用OkHttp发送请求，获取图片数据
			byte[] data = new OkHttpClient.Builder()
					.connectTimeout(60, TimeUnit.SECONDS)
					.readTimeout(60, TimeUnit.SECONDS)
					.writeTimeout(60, TimeUnit.SECONDS)
					.build()
					.newCall(new Request.Builder().url(url).build())
					.execute().body().bytes();
			out.write(data);
			out.close();
			
		} catch (Exception e) {
		}

	}

}
