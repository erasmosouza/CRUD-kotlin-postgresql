package esp.quickstart.demo.book

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.fasterxml.jackson.annotation.JsonProperty
import esp.quickstart.demo.audit.Audit
import esp.quickstart.demo.category.Category
import esp.quickstart.demo.category.CategoryDTO
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class Book(

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
) {

    fun toDTO(): BookDTO = BookDTO(
        id = this.id,
        title = this.title,
        author = this.author,
        description = this.description,
        category = CategoryDTO(id = this.category.id, categoryName = this.category.categoryName)
    )

}