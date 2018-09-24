package com.oyzl.spider;

public class Book implements Comparable<Book> {
	
	private int rand; // 排名
	private String name; //书名
	private String author; // 作者
	private double price; // 价格
	private String cover; // 书籍封面
	
	public int getRand() {
		return rand;
	}
	public void setRand(int rand) {
		this.rand = rand;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}

	@Override
	public String toString() {
		return "Book [ rand: " + rand + ", name: " + name + 
				", author: " + author + ", price: " + price +
				", cover: " + cover + " ]";
	}
	@Override
	public int compareTo(Book o) {
		return rand-o.rand;
	}
}
