package esp.quickstart.demo.book

import org.junit.Assert
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.TestMethodOrder
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit4.SpringRunner

/*
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
*
 */
class BookControllerTest {
/*
    @LocalServerPort
    var port: Int? = null

    @Autowired
    var restTemplate: TestRestTemplate? = null


    @org.junit.Test
    @Order(1)
    fun createBookTest() {
        println("======>createBook")

        Assert.assertEquals(true, true)
    }

    @Order(2)
    @org.junit.Test
    fun getBookTest() {
        println("======>getBook")
        Assert.assertEquals(true, true)
    }

    @Order(3)
    @org.junit.Test
    fun getAllBookTest() {
        println("======>getAllBook")
        Assert.assertEquals(true, true)
    }

    @Order(4)
    @org.junit.Test
    fun deleteBookTest() {
        println("======>deleteBook")
        Assert.assertEquals(true, true)
    }

 */
}