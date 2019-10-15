package esp.quickstart.demo.category

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import esp.quickstart.demo.book.Book
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class Category (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @get: NotBlank
    val categoryName: String = "",


    @JsonBackReference
    @OneToMany(mappedBy = "category", cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
    var books: MutableList<Book>? = null
)