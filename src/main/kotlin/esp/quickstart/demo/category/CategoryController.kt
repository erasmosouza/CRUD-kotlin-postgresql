package esp.quickstart.demo.category

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/categories")
class CategoryController(private val service: CategoryService) {

    @GetMapping()
    fun all(
        @RequestParam("page", defaultValue = "0") page: Int,
        @RequestParam("size", defaultValue = "10") size: Int
    ): ResponseEntity<List<Category>>{

        val catList = service.allByPagination(page, size)

        if (catList.isEmpty()){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(catList)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable(value = "id") categoryId: Long): ResponseEntity<Category> {
        return service.findById(categoryId).map { category ->
            ResponseEntity.ok(category)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PostMapping()
    fun create(@Valid @RequestBody category: Category): ResponseEntity<Category> {

        val newCategory: Category = service.save(category)
        if (newCategory.id == 0L){
            return ResponseEntity(newCategory, HttpStatus.CONFLICT)
        }
        return ResponseEntity(newCategory, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody category: Category): ResponseEntity<Category> {
        if (service.existsById(id)) {
            return ResponseEntity.ok(service.update(id, category))
        }
        return ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable(value = "id") categoryId: Long): ResponseEntity<Void> {
        if (service.existsById(categoryId)) {
            service.deleteById(categoryId)
            return ResponseEntity.ok().build()
        }
        return ResponseEntity.notFound().build()
    }
}