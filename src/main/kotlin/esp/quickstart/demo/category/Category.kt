package esp.quickstart.demo.category

import com.fasterxml.jackson.annotation.JsonManagedReference
import esp.quickstart.demo.livro.Book
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class Category (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @get: NotBlank
    val categoryName: String = "",

    @JsonManagedReference
    @OneToMany(mappedBy = "category", cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
    var books: MutableSet<Book>? = null
)