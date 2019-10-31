package esp.quickstart.demo.book

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.fasterxml.jackson.annotation.JsonProperty
import esp.quickstart.demo.audit.Audit
import esp.quickstart.demo.category.Category
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class Book (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    val id: Long = 0,

    @get: NotBlank
    var title: String = "",

    @get: NotBlank
    var author: String = "",

    @get: NotBlank
    var description: String = "",

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.MERGE))
    @JoinColumn(name = "category_id")
    var category: Category = Category()
)/*: Audit()*/