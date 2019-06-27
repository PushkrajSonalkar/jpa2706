package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Book_Request;
import com.example.demo.repository.BookRequestRepository;


@Service("bookRequestService")
public class BookRequestServiceImpl implements BookRequestService
{
	@Autowired
	private BookRequestRepository bookRequestRepository;
	@Override
	public Book_Request saveRequest(Book_Request book_request) 
	{
		return bookRequestRepository.saveAndFlush(book_request);
	}

}
