package esp.quickstart.demo.book

import com.fasterxml.jackson.annotation.JsonManagedReference
import com.fasterxml.jackson.annotation.JsonProperty
import esp.quickstart.demo.category.CategoryDTO
import javax.validation.constraints.NotBlank

class BookDTO(

    @JsonProperty(value = "id"/*, access = JsonProperty.Access.READ_ONLY*/)
    val id: Long = 0,

    @get: NotBlank
    var title: String = "",

    @get: NotBlank
    var author: String = "",

    @get: NotBlank
    var description: String = "",

    @JsonManagedReference
    var category: CategoryDTO = CategoryDTO()

)