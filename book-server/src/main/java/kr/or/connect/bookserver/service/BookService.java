package kr.or.connect.bookserver.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import kr.or.connect.bookserver.persistence.BookDao;
import kr.or.connect.domain.Book;

/**
 * 
 * @author minchoba
 * BookService 클래스
 * 요청의 흐름: @Controller가 붙은 클래스 -> @Service가 붙은 클래스 -> @Repository가 붙은 클래스
 * 
 * ConcurrentHashMap의 구현을 없애고, BookDao를 참조 호출
 * 
 */

@Service
public class BookService {
	private BookDao dao;
	
	public BookService(BookDao dao) {
		this.dao = dao;
	}
	
	public Book findById(Integer id) {		// id를 통한 해당 book read
		return dao.selectById(id);
	}
	
	public Collection<Book> findAll(){		// book 리스트 전체 read
		return dao.selectAll();
	}
	
	public Book create(Book book) {			// book 생성
		Integer id = dao.insert(book);
		book.setId(id);
		
		return book;
	}
	
	public boolean update(Book book) {			// update 메소드로 내용을 수정하고, 내용 수정이 실행 되었는가? 1
		int affected = dao.update(book);
		return affected == 1;
	}
	
	public boolean delete(Integer id) {			// delteById 메소드로 내용 삭제 후, 내용 삭제가 실행 되었는가? 1
		int affected = dao.deleteById(id);
		return affected == 1;
	}
}
