package esp.quickstart.demo.book

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/books")
class BookController(
    private val bookService: BookService
) {

    @GetMapping()
    fun allBook(
        @RequestParam("page", defaultValue = "0") page: Int,
        @RequestParam("size", defaultValue = "10") size: Int
    ): List<Book> = bookService.allByPagination(page, size)

    @GetMapping("/{id}")
    fun findByIdBook(@PathVariable(value = "id") id: Long): ResponseEntity<Book> {
        return bookService.findById(id).map { book ->
            ResponseEntity.ok(book)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PostMapping()
    fun createBook(@Valid @RequestBody book: Book): ResponseEntity<Book> {
        println("==============>createBook")
        return ResponseEntity.ok(bookService.save(book))
    }

    @PutMapping("/{id}")
    fun updateBook(@PathVariable id: Long, @Valid @RequestBody book: Book): ResponseEntity<Book> {
        if (bookService.existsById(id)) {
            return ResponseEntity.ok(bookService.update(id, book))
        }
        return ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun deleteByIdBook(@PathVariable(value = "id") id: Long): ResponseEntity<Void> {
        if (bookService.existsById(id)) {
            bookService.deleteById(id)
            return ResponseEntity.ok().build()
        }
        return ResponseEntity.notFound().build()
    }
}