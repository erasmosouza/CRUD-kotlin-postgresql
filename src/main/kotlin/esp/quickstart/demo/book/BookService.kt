package esp.quickstart.demo.book

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class BookService(private val repository: BookRepository) {

    fun all(): List<Book> = repository.findAll().toList()

    fun allByPagination(page: Int, size: Int): List<Book> {
        val pag: Pageable = PageRequest.of(page, size);
        return repository.findAll(pag).toList()
    }

    fun findById(id: Long): Optional<Book> = repository.findById(id)

    fun existsById(id: Long): Boolean = repository.existsById(id)

    fun save(book: Book): Book = repository.save(book)

    fun update(id: Long, book: Book): Book {
        var safeBook = book.copy(id = id)
        return this.save(safeBook)
    }

    fun deleteById(id: Long) = repository.deleteById(id)
}