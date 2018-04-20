package kr.or.connect.bookserver.persistence;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.BookServerApplication;
import kr.or.connect.domain.Book;

/**
 * 
 * @author minchoba
 * Appconfig에 있던 요소들이 Spring Boot에 의해 자동으로 처리
 * 자동 처리 항목-------------------------------------------------------
 * @ComponentScan 선언
 * @PropertSource 선언으로 application.properties에 있던 속성을 값을 읽어오기
 * dataSource에 application.properties에 있던 속성을 주입하고 @Bean
 * transactionManager를 @Bean 으로 등록
 * ------------------------------------------------------------------
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookServerApplication.class)
@Transactional
public class BookDaoTest {
	@Autowired
	private BookDao dao;
	
	@Test
	public void shouldCount() {
		int count = dao.countBooks();
		System.out.println(count);
	}
	
	@Test
	public void shouldInsertAndSelect() {
		// given
		Book book = new Book("Java 웹개발", "네이버", 342);

		// when
		Integer id = dao.insert(book);

		// then
		Book selected = dao.selectById(id);
		System.out.println(selected);
		assertThat(selected.getTitle(), CoreMatchers.is("Java 웹개발"));
	}
	
	@Test
	public void shouldDelete() {
		// given
		Book book = new Book("네이버 자바", "네이버", 142);
		Integer id = dao.insert(book);

		// when
		int affected = dao.deleteById(id);

		// Then
		assertThat(affected, CoreMatchers.is(1));
	}
	
	@Test
	public void shouldUpdate() {
		// Given
		Book book = new Book("네이버 자바", "네이버", 142);
		Integer id = dao.insert(book);

		// When
		book.setId(id);
		book.setTitle("네이버 자바2");
		int affected = dao.update(book);

		// Then
		assertThat(affected, CoreMatchers.is(1));
		Book updated = dao.selectById(id);
		assertThat(updated.getTitle(), CoreMatchers.is("네이버 자바2"));
	}
	
	@Test
	public void shouldSelectAll() {
		List<Book> allBooks = dao.selectAll();
		assertThat(allBooks, CoreMatchers.is(notNullValue()));
	}
}
