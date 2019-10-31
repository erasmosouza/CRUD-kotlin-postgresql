package esp.quickstart.demo.book

import esp.quickstart.demo.category.Category
import esp.quickstart.demo.category.CategoryDTO
import esp.quickstart.demo.category.CategoryRepository
import esp.quickstart.demo.category.CategoryService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class BookService(private val repository: BookRepository, private val categoryRepository: CategoryRepository) {

    fun all(): List<BookDTO> = repository.findAll().map { it.toDTO() }.toList()

    fun allByPagination(page: Int, size: Int): List<BookDTO> {
        val pag: Pageable = PageRequest.of(page, size);
        return repository.findAll(pag).map { it.toDTO() }.toList()
    }

    fun findById(id: Long): BookDTO = repository.findById(id).map { book -> book.toDTO() }.orElse(BookDTO())

    fun existsById(id: Long): Boolean = repository.existsById(id)

    fun save(bookDTO: BookDTO): BookDTO {

        val categoryEntity: Category = categoryRepository.findById(bookDTO.category.id).get()

        return repository.save(
            Book(
                title = bookDTO.title,
                author = bookDTO.author,
                description = bookDTO.description,
                category = categoryEntity
            )
        ).toDTO()
    }

    fun update(id: Long, bookDTO: BookDTO): BookDTO {

        val categoryEntity: Category = categoryRepository.findById(bookDTO.category.id).get()

        val bookEntity: Book = repository.findById(id).get()

        bookEntity.title = bookDTO.title
        bookEntity.description = bookDTO.description
        bookEntity.author = bookDTO.author
        bookEntity.category = categoryEntity

        return repository.save(bookEntity).toDTO()
    }

    fun deleteById(id: Long) = repository.deleteById(id)
}