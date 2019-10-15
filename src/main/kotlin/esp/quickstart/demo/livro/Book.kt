package esp.quickstart.demo.livro

import com.fasterxml.jackson.annotation.JsonBackReference
import esp.quickstart.demo.category.Category
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class Book (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @get: NotBlank
    val titulo: String = "",

    @get: NotBlank
    val autor: String = "",

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id")
    val category: Category? = null
)