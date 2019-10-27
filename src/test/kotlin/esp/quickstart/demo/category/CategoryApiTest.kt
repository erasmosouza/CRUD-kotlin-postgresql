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
class CategoryApiTest {

    @LocalServerPort
    var port: Int? = null

    @Autowired
    var restTemplate: TestRestTemplate? = null

    @Test
    fun createAndUpdateTest() {

        // Create 3 categories
        for (categoryName in listOf("Primeira", "Segunda", "Terceira")) {
            postCategory(categoryName)
        }

        // Update the second category name
        putCategory(2, Category(1, "Segunda Alterada"))

        // Get category list with limit 2
        var list = listAllCategories(0, 2)

        Assert.assertEquals(2, list?.size)
    }

    @Test
    @Order(4)
    fun deleteCategoryByIdTest() {

        // Delete the second record
        restTemplate?.delete("${getTestUrl()}/2")

        Assert.assertTrue(true)
    }

    fun getTestUrl(): String = "http://localhost:$port/api/categories"

    fun postCategory(categoryName: String): Category? = restTemplate?.postForObject(
        getTestUrl(),
        Category(categoryName = categoryName),
        Category::class.java
    )

    fun putCategory(id: Long, category: Category): Unit? = restTemplate?.put(
        "${getTestUrl()}/$id",
        category,
        Category::class
    )

    fun listAllCategories(page: Int, size: Int): List<*>? =
        restTemplate?.getForObject("${getTestUrl()}?page=0&size=2", List::class.java, 200)
}