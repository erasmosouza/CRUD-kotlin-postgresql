package esp.quickstart.demo.category

import com.fasterxml.jackson.annotation.JsonManagedReference
import esp.quickstart.demo.livro.Livro
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class Category (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @get: NotBlank
    val categoryName: String = "",

    @JsonManagedReference
    @OneToMany(mappedBy = "categoria", cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
    var livros: MutableSet<Livro>? = null
)