package esp.quickstart

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HealthControllerTest {

    @LocalServerPort
    var port: Int? = null

    @Autowired
    var restTemplate: TestRestTemplate? = null

    @Test
    fun healthRestTest() {

        var health: Health? =
            restTemplate?.getForObject("http://localhost:$port/check?name=Kotlin", Health::class.java, 200)

        Assert.assertEquals("Hello, Kotlin", health?.content)
    }
}