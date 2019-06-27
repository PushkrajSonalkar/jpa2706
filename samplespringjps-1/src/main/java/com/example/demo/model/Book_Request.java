package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="book_request")
public class Book_Request 
{
	@Id
	@Column(name="Sr_No")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int sr;
	@Column(name="book_id")
	private int book_id;

	@Column(name="user_id")
	private int user_id;

	public int getSr() {
		return sr;
	}

	public void setSr(int sr) {
		this.sr = sr;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public Book_Request() {
	}

	public Book_Request(int book_id, int user_id) {
		this.book_id = book_id;
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Book_Request [sr=" + sr + ", book_id=" + book_id + ", user_id=" + user_id + "]";
	}
}
