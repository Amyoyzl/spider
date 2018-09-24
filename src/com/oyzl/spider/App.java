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
		// �����������������ߵ���
		String url = "http://bang.dangdang.com/books/bestsellers";

		List<Book> books = Collections.synchronizedList(new LinkedList<>());
		
		// �̳߳�
		ExecutorService pool = Executors.newFixedThreadPool(5);
		
		for(int i = 1; i <= 25; i++) {
			url = "http://bang.dangdang.com/books/bestsellers/1-" + i;
			pool.execute(new Spider(url, books));
		}
		// �ر��̳߳�
		pool.shutdown();
		
		// �߳����
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
		// ����
		Collections.sort(books);
		
		String json = new Gson().toJson(books);
		
		try (FileWriter out = new FileWriter("book.json")) {
			out.write(json);
			System.out.println("�ļ��ѱ��棡");
		} catch (Exception e) {
		}
		
	}

}
