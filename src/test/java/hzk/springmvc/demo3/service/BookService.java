package hzk.springmvc.demo3.service;

import java.util.ArrayList;
import java.util.List;

import hzk.springmvc.demo3.model.Book;

import org.springframework.stereotype.Service;

@Service
public class BookService {

	public Book getByIsbn(String isbn){
		Book b=new Book();
		b.setIsbn("0000000");
		b.setName("AAA");
		return b;
	}

	public List<Book> getAll() {
		Book b=new Book();
		b.setIsbn("0000000");
		b.setName("AAA");
		Book b2=new Book();
		b.setIsbn("0000000");
		b.setName("ABB");
		List<Book> l=new ArrayList<Book>();
		l.add(b);l.add(b2);
		return l;
	}
}
