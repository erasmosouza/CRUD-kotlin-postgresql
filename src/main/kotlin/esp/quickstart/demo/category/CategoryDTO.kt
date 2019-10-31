package esp.quickstart.demo.category

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class CategoryDTO (

    @JsonProperty(value = "id"/*, access = JsonProperty.Access.READ_ONLY*/)
    var id: Long = 0,

    @NotBlank
    @Size(min = 3, max = 60, message = "First name cannot be longer than 60 characters")
    var categoryName: String = ""
)