package kr.or.connect.bookserver.presentation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.bookserver.service.BookService;
import kr.or.connect.domain.Book;

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
	
	@GetMapping("/{id}")
	Book read(@PathVariable Integer id) {			// id를 통한 목록 찾기
		return service.findById(id);
	}
}
