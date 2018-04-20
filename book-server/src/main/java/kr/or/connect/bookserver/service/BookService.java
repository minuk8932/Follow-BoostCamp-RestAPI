package kr.or.connect.bookserver.service;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import kr.or.connect.domain.Book;

/**
 * 
 * @author minchoba
 * BookService 클래스
 * 요청의 흐름: @Controller가 붙은 클래스 -> @Service가 붙은 클래스 -> @Repository가 붙은 클래스
 * 
 */

@Service
public class BookService {
	// Book 데이터는 BookService에서 ConcurrentHashMap에 저장
	private ConcurrentMap<Integer, Book> repo = new ConcurrentHashMap<>();
	private AtomicInteger maxId  = new AtomicInteger(0);
	
	public Book findById(Integer id) {
		return repo.get(id);
	}
	
	public Collection<Book> findAll(){
		return repo.values();
	}
	
	public Book create(Book book) {
		Integer id = maxId.addAndGet(1);
		book.setId(id);
		repo.put(id, book);
		
		return book;
	}
}
