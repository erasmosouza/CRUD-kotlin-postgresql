package esp.quickstart.demo.livro

import esp.quickstart.demo.category.CategoryRepository
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

    @GetMapping("/livros")
    fun getAllLivros(): List<Book> = bookRepository.findAll()

    @PostMapping("/livros")
    fun createNewLivro(@Valid @RequestBody book: Book) {
        book.category?.let { categoryRepository.save(it) }
        bookRepository.save(book)
    }

    @GetMapping("/livros/{id}")
    fun getLivroById(@PathVariable("id") livroId: Long): ResponseEntity<Book> {
        return bookRepository.findById(livroId).map { livro -> ResponseEntity.ok(livro) }
            .orElse(ResponseEntity.notFound().build())
    }

    fun deleteLivroById(@PathVariable("id") livroId: Long): ResponseEntity<Void> {
        return bookRepository.findById(livroId).map { livro ->
            bookRepository.delete(livro)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }

}