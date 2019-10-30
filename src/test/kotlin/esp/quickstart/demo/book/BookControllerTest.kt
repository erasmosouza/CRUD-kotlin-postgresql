package esp.quickstart.demo.book

import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import esp.quickstart.demo.category.Category
import esp.quickstart.demo.category.CategoryController
import esp.quickstart.demo.category.CategoryService
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@RunWith(SpringRunner::class)
class BookControllerTest {

    @Test
    fun shouldRetrieveAllCategories() {

        // Mock the Service
        val mock: BookService = mock()
        val controller: BookController = BookController(mock)

        whenever(mock.allByPagination(0, 10)).thenReturn(listOf(Book(), Book()))
        whenever(mock.allByPagination(1, 10)).thenReturn(listOf())

        val categorySuccess: ResponseEntity<List<Book>> = controller.all(0, 10)
        Assert.assertEquals(HttpStatus.OK, categorySuccess.statusCode)

        val categoryEmpty: ResponseEntity<List<Book>> = controller.all(1, 10)
        Assert.assertEquals(HttpStatus.NOT_FOUND, categoryEmpty.statusCode)
    }

    @Test
    fun shouldRetrieveABookTest() {

        val book = Book(
            id = 1,
            title = "Test Book",
            description = "Descricao do Livro",
            author = "Erasmo",
            category = Category(1, "Categoria do Livro")
        )

        // Mock the Service
        val mock: BookService = mock()
        whenever(mock.findById(1)).thenReturn(Optional.of(book))
        whenever(mock.findById(2)).thenReturn(Optional.empty())

        // 200
        val controller: BookController = BookController(mock)
        val bookSuccess: ResponseEntity<Book> = controller.findById(1)
        Assert.assertEquals(HttpStatus.OK, bookSuccess.statusCode)

        // Not Found
        val bookFail: ResponseEntity<Book> = controller.findById(2)
        Assert.assertEquals(HttpStatus.NOT_FOUND, bookFail.statusCode)
    }


    @Test
    fun shouldCreateNewBookTest() {

        // Cenarios
        var bookSuccess = Book(
            id = 1,
            title = "Test Book",
            description = "Descricao do Livro",
            author = "Erasmo",
            category = Category(1, "Categoria do Livro")
        )

        var bookToCreate = Book(
            title = "Test Book",
            description = "Descricao do Livro",
            author = "Erasmo",
            category = Category(1, "Categoria do Livro")
        )
        var bookConflict = Book()

        // mock the Service
        val mock: BookService = mock()
        whenever(mock.save(bookToCreate)).thenReturn(bookSuccess)
        whenever(mock.save(bookConflict)).thenReturn(bookConflict)
        val controller: BookController = BookController(mock)

        // 409
        val conflictTest: ResponseEntity<Book> = controller.create(bookConflict)
        Assert.assertEquals(HttpStatus.CONFLICT, conflictTest.statusCode)

        // 201
        val createdTest: ResponseEntity<Book> = controller.create(bookToCreate)
        Assert.assertEquals(HttpStatus.CREATED, createdTest.statusCode)
    }

    @Test
    fun shouldUpdateBookTest() {

        val mock: BookService = mock()
        val controller: BookController = BookController(mock)
        val book =  Book(
            id = 1,
            title = "Test Book",
            description = "Descricao do Livro",
            author = "Erasmo",
            category = Category(1, "Categoria do Livro"))

        // Mock Service
        whenever(mock.existsById(1)).thenReturn(true)
        whenever(mock.existsById(2)).thenReturn(false)
        whenever(mock.update(1, book)).thenReturn(book)

        // 200
        val bookSuccess: ResponseEntity<Book> = controller.update(1, book)
        Assert.assertEquals(HttpStatus.OK, bookSuccess.statusCode)

        // Not Found
        val bookFail: ResponseEntity<Book> = controller.update(2, book)
        Assert.assertEquals(HttpStatus.NOT_FOUND, bookFail.statusCode)
    }

    @Test
    fun shouldDeleteCategoryByIdTest(){

        // Mock the Service
        val mock: BookService = mock()
        whenever(mock.existsById(1)).thenReturn(true)
        whenever(mock.existsById(2)).thenReturn(false)
        doNothing().`when`(mock).deleteById(1)

        // 200
        val controller: BookController = BookController(mock)
        val bookSuccess: ResponseEntity<Void> = controller.deleteById(1)
        Assert.assertEquals(HttpStatus.OK, bookSuccess.statusCode)

        // Not Found
        val bookFail: ResponseEntity<Void> = controller.deleteById(2)
        Assert.assertEquals(HttpStatus.NOT_FOUND, bookFail.statusCode)
    }
}