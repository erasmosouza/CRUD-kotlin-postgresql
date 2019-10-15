package esp.quickstart.demo.category

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class CategoryController(private val categoryRepository: CategoryRepository) {

    @GetMapping("/categorias")
    fun getAllCateggorias(): List<Category> = categoryRepository.findAll()

    @PostMapping("/categorias")
    fun createNewCategoria(@Valid @RequestBody category: Category): ResponseEntity<Category> {
        categoryRepository.save(category)
        return ResponseEntity.ok(category)
    }

    @GetMapping("/categorias/{id}")
    fun getLivroById(@PathVariable(value = "id") categoriaId: Long): ResponseEntity<Category> {
        return categoryRepository.findById(categoriaId).map { categoria ->
            ResponseEntity.ok(categoria)
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/categorias/{id}")
    fun deleteLivroById(@PathVariable(value = "id") categoriaId: Long): ResponseEntity<Void> {

        return categoryRepository.findById(categoriaId).map { categoria ->
            categoryRepository.delete(categoria)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }


}