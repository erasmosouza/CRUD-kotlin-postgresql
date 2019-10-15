package esp.quickstart.demo.category

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class CategoryController(private val categoryRepository: CategoryRepository) {

    @GetMapping("/categories")
    fun getAllCategories(): List<Category> = categoryRepository.findAll()

    @PostMapping("/categories")
    fun createNewCategory(@Valid @RequestBody category: Category): ResponseEntity<Category> {
        categoryRepository.save(category)
        return ResponseEntity.ok(category)
    }

    @GetMapping("/categories/{id}")
    fun getCategoryById(@PathVariable(value = "id") categoryId: Long): ResponseEntity<Category> {
        return categoryRepository.findById(categoryId).map { category ->
            ResponseEntity.ok(category)
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/categories/{id}")
    fun deleteCategoryById(@PathVariable(value = "id") categoryId: Long): ResponseEntity<Void> {

        return categoryRepository.findById(categoryId).map { category ->
            categoryRepository.delete(category)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }


}