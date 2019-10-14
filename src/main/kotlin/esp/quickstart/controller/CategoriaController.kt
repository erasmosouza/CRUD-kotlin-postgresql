package esp.quickstart.controller

import esp.quickstart.model.Categoria
import esp.quickstart.repository.CategoriaRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class CategoriaController(private val categoriaRepository: CategoriaRepository) {

    @GetMapping("/categorias")
    fun getAllCateggorias(): List<Categoria> = categoriaRepository.findAll()

    @PostMapping("/categorias")
    fun createNewCategoria(@Valid @RequestBody categoria: Categoria): ResponseEntity<Categoria> {
        categoriaRepository.save(categoria)
        return ResponseEntity.ok(categoria)
    }

    @GetMapping("/categorias/{id}")
    fun getLivroById(@PathVariable(value = "id") categoriaId: Long): ResponseEntity<Categoria> {
        return categoriaRepository.findById(categoriaId).map { categoria ->
            ResponseEntity.ok(categoria)
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/categorias/{id}")
    fun deleteLivroById(@PathVariable(value = "id") categoriaId: Long): ResponseEntity<Void> {

        return categoriaRepository.findById(categoriaId).map { categoria ->
            categoriaRepository.delete(categoria)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }


}