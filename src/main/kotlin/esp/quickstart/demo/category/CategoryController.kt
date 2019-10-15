package esp.quickstart.demo.category

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class CategoryController(private val categoryRepository: CategoryRepository) {

    @GetMapping("/categories")
    fun getAllCateggorias(): List<Category> = categoryRepository.findAll()

    @PostMapping("/categories")
    fun createNewCategoria(@Valid @RequestBody category: Category): ResponseEntity<Category> {
        categoryRepository.save(category)
        return ResponseEntity.ok(category)
    }

    @GetMapping("/categories/{id}")
    fun getLivroById(@PathVariable(value = "id") categoryId: Long): ResponseEntity<Category> {
        return categoryRepository.findById(categoryId).map { category ->
            ResponseEntity.ok(category)
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/categories/{id}")
    fun deleteLivroById(@PathVariable(value = "id") categoryId: Long): ResponseEntity<Void> {

        return categoryRepository.findById(categoryId).map { category ->
            categoryRepository.delete(category)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }


}