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
	
	public Book findById(Integer id) {		// id를 통한 해당 book read
		return repo.get(id);
	}
	
	public Collection<Book> findAll(){		// book 리스트 전체 read
		return repo.values();
	}
	
	public Book create(Book book) {			// book 생성
		Integer id = maxId.addAndGet(1);
		book.setId(id);
		repo.put(id, book);
		
		return book;
	}
	
	public boolean update(Book book) {			// put으로 내용을 수정하고, 내용이 제대로 수정되었는지 진리값 반환
		Book old = repo.put(book.getId(), book);
		return old != null;
	}
	
	public boolean delete(Integer id) {			// remove로 내용 삭제 후, 내용이 제대로 삭제되었는지 진리값 반환
		Book old = repo.remove(id);
		return old != null;
	}
}
