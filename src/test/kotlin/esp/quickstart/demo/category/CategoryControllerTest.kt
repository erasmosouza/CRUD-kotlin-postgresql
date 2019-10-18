package esp.quickstart.demo.category

import org.junit.Assert
import org.junit.Test
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.TestMethodOrder
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.test.context.junit4.SpringRunner
import javax.annotation.Resource

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class CategoryControllerTest {

    @LocalServerPort
    var port: Int? = null

    @Autowired
    var restTemplate: TestRestTemplate? = null

    var categoryId: Long? = null;

    @Test
    @Order(1)
    fun createNewCategoryTest() {
        // Create
        var category: Category = Category(categoryName = "Primeira Categoria")
        var retCategory = restTemplate?.postForObject(
            "http://localhost:$port/api/categories?name=Kotlin",
            category,
            Category::class.java
        )
        this.categoryId = retCategory?.id

        println("createNewCategoryTest ====> $retCategory")
        Assert.assertEquals(category.categoryName, retCategory?.categoryName)
    }

    @Test
    @Order(2)
    fun getCategoryByIdTest() {

        var isValid = false
        // Get
        var category =
            restTemplate?.getForObject(
                "http://localhost:$port/api/categories/${this.categoryId}",
                Category::class.java,
                200
            )

        isValid = if (category != null) true else false

        println("getCategoryByIdTest ====> $category")
        Assert.assertTrue(isValid)
    }

    @Test
    @Order(3)
    fun getAllCategoriesTest() {
        //getAll
        var listAllCategory =
            restTemplate?.getForObject("http://localhost:$port/api/categories/", List::class.java, 200)

        @Suppress("UNCHECKED_CAST")
        val list: List<Category>? = listAllCategory as? List<Category>

        var isValid: Boolean = true

        if (list?.isEmpty() == true)
            isValid = false

        println("getAllCategoriesTest ====> $list")
        Assert.assertTrue(isValid)
    }

    @Test
    @Order(4)
    fun deleteCategoryByIdTest() {

        // delete
        restTemplate?.delete("http://localhost:$port/api/categories/${this.categoryId}")

        println("getAllCategoriesTest ====> ${this.categoryId}")
        Assert.assertTrue(true)
    }
}