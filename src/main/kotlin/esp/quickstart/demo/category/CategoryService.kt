package esp.quickstart.demo.category

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class CategoryService(private val repository: CategoryRepository) {

    fun all(): List<CategoryDTO> = repository.findAll().map { it.toDTO() }.toList()

    fun allByPagination(page: Int, size: Int): List<CategoryDTO> {
        val pag: Pageable = PageRequest.of(page, size);
        return repository.findAll(pag).map { it.toDTO() }.toList()
    }

    fun findById(id: Long): CategoryDTO = repository.findById(id).map { category -> category.toDTO() }.orElse(CategoryDTO())

    fun existsById(id: Long): Boolean = repository.existsById(id)

    fun save(category: CategoryDTO): CategoryDTO {

        if (category.categoryName == "") { // Em breve, trocar por validação de form
            return category
        }
        return repository.save(Category(categoryName = category.categoryName)).toDTO()
    }

    fun update(id: Long, categoryDTO: CategoryDTO): CategoryDTO {

        var categoryEntity: Category = repository.findById(id).get()
        categoryEntity.categoryName = categoryDTO.categoryName
        repository.save(categoryEntity)

        return categoryEntity.toDTO()
    }

    fun deleteById(id: Long) = repository.deleteById(id)
}