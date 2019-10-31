package esp.quickstart.demo.book

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/books")
class BookController(
    private val bookService: BookService
) {

    @GetMapping()
    fun all(
        @RequestParam("page", defaultValue = "0") page: Int,
        @RequestParam("size", defaultValue = "10") size: Int
    ): ResponseEntity<List<BookDTO>> {

        val bookList =  bookService.allByPagination(page, size)

        if (bookList.isEmpty()){
            return ResponseEntity.notFound().build()
        }

        return ResponseEntity.ok(bookList)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable(value = "id") id: Long): ResponseEntity<BookDTO> {

        val bookDTO = bookService.findById(id)

        if (bookDTO.id == 0L){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(bookDTO)
    }

    @PostMapping()
    fun create(@Valid @RequestBody book: BookDTO): ResponseEntity<BookDTO> {

        val newBook: BookDTO = bookService.save(book)
        if (newBook.id == 0L){
            return ResponseEntity(newBook, HttpStatus.CONFLICT)
        }
        return ResponseEntity(newBook, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody book: BookDTO): ResponseEntity<BookDTO> {
        if (bookService.existsById(id)) {
            return ResponseEntity.ok(bookService.update(id, book))
        }
        return ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable(value = "id") id: Long): ResponseEntity<Void> {
        if (bookService.existsById(id)) {
            bookService.deleteById(id)
            return ResponseEntity.ok().build()
        }
        return ResponseEntity.notFound().build()
    }
}