package esp.quickstart.demo.category

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.fasterxml.jackson.annotation.JsonProperty
import esp.quickstart.demo.audit.Audit
import esp.quickstart.demo.book.Book
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class Category(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    val id: Long = 0,

    @get: NotBlank
    val categoryName: String = ""

)/* : Audit()*/