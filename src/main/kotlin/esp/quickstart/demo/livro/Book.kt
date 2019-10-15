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
    val title: String = "",

    @get: NotBlank
    val author: String = "",

    @get: NotBlank
    val description: String = "",

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    val category: Category? = null
)