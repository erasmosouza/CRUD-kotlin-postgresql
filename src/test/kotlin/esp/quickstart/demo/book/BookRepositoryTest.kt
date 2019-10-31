package esp.quickstart.demo.book

import esp.quickstart.demo.category.Category
import esp.quickstart.demo.category.CategoryRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private var repository: BookRepository? = null

    @Autowired
    private var repositoryCategory: CategoryRepository? = null

    @Test
    fun saveTest(){


        val cat = repositoryCategory!!.save(Category(categoryName = "Minha Categoria"))
        val cat2 = repositoryCategory!!.save(Category(categoryName = "Minha Categoria 2"))
        val book =  Book(
            title = "Test Book",
            description = "Descricao do Livro",
            author = "Erasmo",
            category =  cat
        )

        val newBook = repository?.save(book)

        print("I:====>")
        newBook!!.category = cat2
        repository?.save(newBook)
        print("F:====>")

        println(newBook)
        println("Da base: ${repository?.findById(1)}")

        println("All Category: ${repositoryCategory?.findAll()}")
    }

}