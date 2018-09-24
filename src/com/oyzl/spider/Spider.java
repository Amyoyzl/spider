package com.oyzl.spider;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Spider implements Runnable {

	private String url;
	private List<Book> books;
	
	public Spider(String url, List<Book> books) {
		this.url = url;
		this.books = books;
	}

	@Override
	public void run() {
		
		try {
			// 获取网页的DOM树
			Document doc = Jsoup.connect(url).timeout(10000).get();
			// 获取列表的所有项
			Elements elements = doc.select(".bang_list_box .bang_list li");
			for (Element e : elements) {
				
				Book b = new Book();
				
				String rand = e.select(".list_num").text();
				// 所有的排名后都有.  如14.  要处理字符串
				rand = rand.substring(0, rand.length()-1);
				String name = e.select(".name a").text();
				String author = e.select(".publisher_info a").first().text();
				String price = e.select(".price .price_n").first().text();
				// 价格有¥符号，需要处理
				price = price.substring(1, price.length());
				String cover = e.select(".pic img").attr("src");
				
				b.setRand(Integer.parseInt(rand));
				b.setAuthor(author);
				b.setName(name);
				b.setPrice(Double.parseDouble(price));
				b.setCover(cover);
				
				books.add(b);
//				System.out.println(b);
				System.out.println(name);
			}
			
//			System.out.println(Thread.currentThread() + " over!");
			
		} catch (IOException e) {
			e.printStackTrace();
		} 

	}

}
