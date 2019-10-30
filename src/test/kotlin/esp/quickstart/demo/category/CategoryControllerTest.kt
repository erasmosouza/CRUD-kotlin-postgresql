package esp.quickstart.demo.category

import com.nhaarman.mockitokotlin2.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@RunWith(SpringRunner::class)
class CategoryControllerTest {

    @Test
    fun shouldRetrieveAllCategories(){

        // Mock the Service
        val mock: CategoryService = mock()
        val controller: CategoryController = CategoryController(mock)

        whenever(mock.allByPagination(0, 10)).thenReturn(listOf(Category(), Category()))
        whenever(mock.allByPagination(1, 10)).thenReturn(listOf())

        val categorySuccess: ResponseEntity<List<Category>> = controller.all(0, 10)
        Assert.assertEquals(HttpStatus.OK, categorySuccess.statusCode)

        val categoryEmpty: ResponseEntity<List<Category>> = controller.all(1, 10)
        Assert.assertEquals(HttpStatus.NOT_FOUND, categoryEmpty.statusCode)
    }

    @Test
    fun shouldRetrieveACategoryTest(){

        val category = Category(id = 1,categoryName = "Minha Categoria")

        // Mock the Service
        val mock: CategoryService = mock()
        whenever(mock.findById(1)).thenReturn(Optional.of(category))
        whenever(mock.findById(2)).thenReturn(Optional.empty())

        // 200
        val controller: CategoryController = CategoryController(mock)
        val categorySuccess: ResponseEntity<Category> = controller.findById(1)
        Assert.assertEquals(HttpStatus.OK, categorySuccess.statusCode)

        // Not Found
        val categoryFail: ResponseEntity<Category> = controller.findById(2)
        Assert.assertEquals(HttpStatus.NOT_FOUND, categoryFail.statusCode)
    }

    @Test
    fun shouldCreateNewCategoryTest() {

        // Cenarios
        var categorySuccess = Category(id = 1,categoryName = "Minha Categoria")
        var categoryToCreate = Category(categoryName = "Minha Categoria")
        var categoryConflict = Category()

        // mock the Service
        val mock: CategoryService = mock()
        whenever(mock.save(categoryToCreate)).thenReturn(categorySuccess)
        whenever(mock.save(categoryConflict)).thenReturn(categoryConflict)
        val controller: CategoryController = CategoryController(mock)

        // 409
        val conflictTest: ResponseEntity<Category> = controller.create(categoryConflict)
        Assert.assertEquals(HttpStatus.CONFLICT, conflictTest.statusCode)

        // 201
        val createdTest: ResponseEntity<Category> = controller.create(categoryToCreate)
        Assert.assertEquals(HttpStatus.CREATED, createdTest.statusCode)
    }


    @Test
    fun shouldUpdateCategoryTest() {

        val mock: CategoryService = mock()
        val controller: CategoryController = CategoryController(mock)
        val category =  Category(1, "Atualizada")

        // Mock Service
        whenever(mock.existsById(1)).thenReturn(true)
        whenever(mock.existsById(2)).thenReturn(false)
        whenever(mock.update(1, category)).thenReturn(category)

        // 200
        val categorySuccess: ResponseEntity<Category> = controller.update(1, category)
        Assert.assertEquals(HttpStatus.OK, categorySuccess.statusCode)

        // Not Found
        val categoryFail: ResponseEntity<Category> = controller.update(2, category)
        Assert.assertEquals(HttpStatus.NOT_FOUND, categoryFail.statusCode)

    }


    @Test
    fun shouldDeleteCategoryByIdTest(){

        // Mock the Service
        val mock: CategoryService = mock()
        whenever(mock.existsById(1)).thenReturn(true)
        whenever(mock.existsById(2)).thenReturn(false)
        doNothing().`when`(mock).deleteById(1)

        // 200
        val controller: CategoryController = CategoryController(mock)
        val categorySuccess: ResponseEntity<Void> = controller.deleteById(1)
        Assert.assertEquals(HttpStatus.OK, categorySuccess.statusCode)

        // Not Found
        val categoryFail: ResponseEntity<Void> = controller.deleteById(2)
        Assert.assertEquals(HttpStatus.NOT_FOUND, categoryFail.statusCode)
    }
}