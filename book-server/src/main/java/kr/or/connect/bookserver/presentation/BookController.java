package kr.or.connect.bookserver.presentation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.bookserver.service.BookService;
import kr.or.connect.domain.Book;

/**
 * 
 * @author minchoba
 * BookController class
 * GetMapping: read api
 * PostMapping: input api
 * 
 */

@RestController
@RequestMapping("/api/books")					// 모든 메서드에 공통적인 부분을 따로 빼줌
public class BookController {
	private final BookService service;
	
	@Autowired
	public BookController(BookService service) {		// BookService 클래스 가져오기
		this.service = service;
	}
	
	@GetMapping
	Collection<Book> readList(){						// 전체목록 가져오기
		return service.findAll();
	}
	
	@GetMapping("/{id}")								// /api/books/{id} 경로에서 id 변수는 @PathVariable를 활용해 추출
	Book read(@PathVariable Integer id) {			// id를 통한 목록 찾기
		return service.findById(id);
	}
	
	@PostMapping								// post 요청으로 받을 주소 지정
	@ResponseStatus(HttpStatus.CREATED)		// HTTP 응답의 상태 코드
	Book create(@RequestBody Book book) {	// 응답 본문으로 부터 추출된 값을 파라미터로 넣을 것임을 알림
		return service.create(book);			// HTTP 응답의 본문은 입력된 book 객체를 JSON으로 다시 반환
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void update(@PathVariable Integer id, @RequestBody Book book) {
		book.setId(id);
		service.update(book);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void delete(@PathVariable Integer id) {
		service.delete(id);
	}
}
