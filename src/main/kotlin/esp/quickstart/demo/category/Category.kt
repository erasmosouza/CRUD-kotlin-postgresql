package esp.quickstart.demo.category

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.fasterxml.jackson.annotation.JsonProperty
import esp.quickstart.demo.audit.Audit
import esp.quickstart.demo.book.Book
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
data class Category(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "id"/*, access = JsonProperty.Access.READ_ONLY*/)
    var id: Long = 0,

    @NotBlank
    @Size(min = 3, max = 60, message = "First name cannot be longer than 60 characters")
    var categoryName: String = ""

)/* : Audit()*/
{
    fun toDTO(): CategoryDTO = CategoryDTO(id=this.id, categoryName = this.categoryName)

    fun fromDTO(dto: CategoryDTO): Category = Category(categoryName = dto.categoryName)
}