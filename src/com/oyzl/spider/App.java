package com.oyzl.spider;

import java.io.FileWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.Gson;

public class App {

	public static void main(String[] args) {
		// 当当网上书店销量最高的书
		String url = "http://bang.dangdang.com/books/bestsellers";

		List<Book> books = Collections.synchronizedList(new LinkedList<>());
		
		// 线程池
		ExecutorService pool = Executors.newFixedThreadPool(5);
		
		for(int i = 1; i <= 25; i++) {
			url = "http://bang.dangdang.com/books/bestsellers/1-" + i;
			pool.execute(new Spider(url, books));
		}
		// 关闭线程池
		pool.shutdown();
		
		// 线程完成
		if(pool.isTerminated()) {
			write(books);
		} else {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static void write(List<Book> books) {
		System.out.println(books.size());
		// 排序
		Collections.sort(books);
		
		String json = new Gson().toJson(books);
		
		try (FileWriter out = new FileWriter("book.json")) {
			out.write(json);
			System.out.println("文件已保存！");
		} catch (Exception e) {
		}
		
	}

}
