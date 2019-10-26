package esp.quickstart.demo.book

import esp.quickstart.Application
import esp.quickstart.demo.category.Category
import org.junit.Assert
import org.junit.Test
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.TestMethodOrder
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class BookControllerTest {

    @LocalServerPort
    var port: Int? = null

    @Autowired
    var restTemplate: TestRestTemplate? = null

    @Test
    fun createAndUpdateTest() {

        postCategory("Minha Categoria")
        postCategory("Minha Categoria2")
        postCategory("Minha Categoria3")

        var cat = getCategory(2)

        println("==================================>Categoria Criada: $cat")

        // Create 3 itens
        for (title in listOf("Primeira", "Segunda", "Terceira")) {
            postBook(
                Book(
                    title = "Titulo do Livro",
                    description = "descricao",
                    author = "Erasmo Pinheiro",
                    category = cat
                )
            )
        }

        // Update the second item
        putBook(2, Book(1, "Segunda Alterada"))

        // Get itens list with limit 2
        var list = listAllBook(0, 2)

        println(list)

        Assert.assertEquals(2, list?.size)
    }

    @Test
    @Order(4)
    fun deleteCategoryByIdTest() {

        // Delete the second record
        restTemplate?.delete("${getTestUrl()}/2")

        Assert.assertTrue(true)
    }


    fun postCategory(categoryName: String): Category? = restTemplate?.postForObject(
        "http://localhost:$port/api/categories",
        Category(categoryName = categoryName),
        Category::class.java
    )

    fun getCategory(id: Long): Category? = restTemplate?.getForObject("http://localhost:$port/api/categories/$id", Category::class.java, 200)

    fun getTestUrl(): String = "http://localhost:$port/api/books"

    fun postBook(book: Book): Book? = restTemplate?.postForObject(
        getTestUrl(),
        book,
        Book::class.java
    )

    fun putBook(id: Long, book: Book): Unit? = restTemplate?.put(
        "${getTestUrl()}/$id",
        book,
        Book::class
    )

    fun listAllBook(page: Int, size: Int): List<*>? =
        restTemplate?.getForObject("${getTestUrl()}?page=0&size=2", List::class.java, 200)
}