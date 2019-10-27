package esp.quickstart.demo.category

import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc

@RunWith(SpringRunner::class)
class CategoryControllerTest {

    @Test
    fun shouldReturnCategoryCreatedTest() {

        // Cenarios
        var categorySucess = Category(id = 1,categoryName = "Minha Categoria")
        var categoryToCreate = Category(categoryName = "Minha Categoria")
        var categoryConflict = Category()

        // mock the Service
        val mock: CategoryService = mock()
        whenever(mock.save(categoryToCreate)).thenReturn(categorySucess)
        whenever(mock.save(categoryConflict)).thenReturn(categoryConflict)
        val controller: CategoryController = CategoryController(mock)

        // 409
        val conflictTest: ResponseEntity<Category> = controller.create(categoryConflict)
        Assert.assertEquals(HttpStatus.CONFLICT, conflictTest.statusCode)

        // 201
        val createdTest: ResponseEntity<Category> = controller.create(categoryToCreate)
        Assert.assertEquals(HttpStatus.CREATED, createdTest.statusCode)
    }
}