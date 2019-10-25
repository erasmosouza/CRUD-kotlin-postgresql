package esp.quickstart.demo.book

import esp.quickstart.demo.category.CategoryRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/books")
class BookController(
    private val bookRepository: BookRepository,
    private val categoryRepository: CategoryRepository
) {

    @GetMapping()
    fun getAllBooks(): List<Book> = bookRepository.findAll().toList()

    @GetMapping("/{id}")
    fun getBookById(@PathVariable("id") bookId: Long): ResponseEntity<Book> {
        return bookRepository.findById(bookId).map { book -> ResponseEntity.ok(book) }
            .orElse(ResponseEntity.notFound().build())
    }

    @PostMapping()
    fun createNewBook(@Valid @RequestBody book: Book): ResponseEntity<Book> {
        book.category?.let { categoryRepository.save(it) }
        bookRepository.save(book)
        return ResponseEntity.ok(book)
    }

    @PutMapping()
    fun updateBook(@PathVariable id: Long, @Valid @RequestBody book: Book): ResponseEntity<Book> {
        book.category?.let { categoryRepository.save(it) }
        if (bookRepository.existsById(id) ){
            val safe = book.copy(id)
            bookRepository.save(safe)
        }
        return ResponseEntity.ok(book)
    }

    @DeleteMapping("/{id}")
    fun deleteBookById(@PathVariable("id") bookId: Long): ResponseEntity<Void> {
        return bookRepository.findById(bookId).map { book ->
            bookRepository.delete(book)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }
}