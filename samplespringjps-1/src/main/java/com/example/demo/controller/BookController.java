package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Book;
import com.example.demo.model.Book_Request;
import com.example.demo.model.User;
import com.example.demo.service.BookRequestService;
import com.example.demo.service.BookService;
import com.example.demo.service.UserService;

@Controller

@RestController
@RequestMapping("/rest")
public class BookController 
{
	public static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BookRequestService bookRequestService;
	
	@RequestMapping(value="/list_books" , method = RequestMethod.GET)
	public ModelAndView ListBooks(ModelAndView model,HttpServletRequest request) throws IOException {
		List<Book> listBooks = bookService.getAllBooks();
		int uid= Integer.parseInt(request.getParameter("uid"));
		model.addObject("listBooks", listBooks);
		model.addObject("uid", uid);
		model.setViewName("books/books");
		
		return model;
	}
	
	  @RequestMapping(value="/request" , method = RequestMethod.GET) 
	  public ModelAndView ReqBooks(HttpServletRequest request) 
	  {
		  int id= Integer.parseInt(request.getParameter("id"));
		  int uid= Integer.parseInt(request.getParameter("uid"));
		  ModelAndView model=new ModelAndView(); 
		  Book book = bookService.getOne(id);
		  model.addObject("uid", uid);	  
		  model.addObject("book", book);
		  model.setViewName("books/req");
		  return model; 
	  }
	 
	
	  @RequestMapping(value= {"/request"}, method=RequestMethod.POST)
	  public ModelAndView createRequest(@Valid Book_Request book, BindingResult bindingResult,HttpServletRequest req) 
	  {
		   ModelAndView model = new ModelAndView();
		   int i=Integer.parseInt(req.getParameter("bid"));
		   int u=Integer.parseInt(req.getParameter("uid"));
		  
		   if(bindingResult.hasErrors()) 
		   {
			  model.setViewName("/request");
		   } 
		   else 
		   {
			book.setBook_id(i);
			book.setUser_id(u);
		    bookRequestService.saveRequest(book);
		    model.addObject("msg", "Book has been requested successfully!");
		    model.addObject("book_request", book);
		    model.addObject("userid", u);
		    model.setViewName("books/books");
		   }
		   return model;
	  }
}