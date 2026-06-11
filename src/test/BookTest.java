package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.model.Book;

public class BookTest {

	@Test
	public void testConstructorWithExplicitId() {
		Book book = new Book(5, "Dom Casmurro", "Machado de Assis", "978-85", 1899, 256, "Romance");

		assertEquals(5, book.getId());
		assertEquals("Dom Casmurro", book.getTitle());
		assertEquals("Machado de Assis", book.getAuthor());
		assertEquals("978-85", book.getIsbn());
		assertEquals(1899, book.getReleaseYear());
		assertEquals(256, book.getNumPages());
		assertEquals("Romance", book.getGenre());
	}

	@Test
	public void testSetters() {
		Book book = new Book(6, "Titulo", "Autor", "000", 2000, 100, "Genero");

		book.setTitle("Novo Titulo");
		book.setAuthor("Novo Autor");
		book.setIsbn("111");
		book.setReleaseYear(2010);
		book.setNumPages(150);
		book.setGenre("Ficcao");
		book.setId(42);

		assertEquals(42, book.getId());
		assertEquals("Novo Titulo", book.getTitle());
		assertEquals("Novo Autor", book.getAuthor());
		assertEquals("111", book.getIsbn());
		assertEquals(2010, book.getReleaseYear());
		assertEquals(150, book.getNumPages());
		assertEquals("Ficcao", book.getGenre());
	}

	@Test
	public void testToCsvLine() {
		Book book = new Book(3, "1984", "George Orwell", "978-01", 1949, 328, "Distopia");

		assertEquals("3,1984,George Orwell,978-01,1949,328,Distopia", book.toCsvLine());
	}

	@Test
	public void testCounterIncrementsOnAutoIdConstructor() {
		int before = Book.getNumBooksCreated();
		new Book("A Revolucao dos Bichos", "George Orwell", "978-02", 1945, 152, "Fabula");
		assertEquals(before + 1, Book.getNumBooksCreated());
	}
}
