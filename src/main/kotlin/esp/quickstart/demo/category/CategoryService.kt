package esp.quickstart.demo.category

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CategoryService(private val repository: CategoryRepository) {

    fun all(): List<Category> = repository.findAll().toList()

    fun allByPagination(page: Int, size: Int): List<Category> {
        val pag: Pageable = PageRequest.of(page, size);
        return repository.findAll(pag).toList()
    }

    fun findById(id: Long): Optional<Category> = repository.findById(id)

    fun existsById(id: Long): Boolean = repository.existsById(id)

    fun save(category: Category): Category = repository.save(category)

    fun update(id: Long, category: Category): Category {
        var safeCategory = category.copy(id = id)
        return this.save(safeCategory)
    }

    fun deleteById(id: Long) = repository.deleteById(id)
}