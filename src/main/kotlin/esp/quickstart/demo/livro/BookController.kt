package esp.quickstart.demo.book

import esp.quickstart.demo.category.CategoryRepository
import esp.quickstart.demo.livro.Book
import esp.quickstart.demo.livro.BookRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class BookController(
    private val bookRepository: BookRepository,
    private val categoryRepository: CategoryRepository
) {

    @GetMapping("/books")
    fun getAllBooks(): List<Book> = bookRepository.findAll()

    @PostMapping("/books")
    fun createNewBook(@Valid @RequestBody book: Book) {
        book.category?.let { categoryRepository.save(it) }
        bookRepository.save(book)
    }

    @GetMapping("/books/{id}")
    fun getBookById(@PathVariable("id") bookId: Long): ResponseEntity<Book> {
        return bookRepository.findById(bookId).map { book -> ResponseEntity.ok(book) }
            .orElse(ResponseEntity.notFound().build())
    }

    fun deleteBookById(@PathVariable("id") bookId: Long): ResponseEntity<Void> {
        return bookRepository.findById(bookId).map { book ->
            bookRepository.delete(book)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }
}